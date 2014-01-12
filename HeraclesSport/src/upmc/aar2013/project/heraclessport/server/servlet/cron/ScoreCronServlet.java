package upmc.aar2013.project.heraclessport.server.servlet.cron;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import upmc.aar2013.project.heraclessport.server.tools.APIRequest;
import upmc.aar2013.project.heraclessport.server.configs.Sport;

/**
 * Servlet Cron : Récupération des scores.
 */
public class ScoreCronServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private final int tries = 3;

	/**
	 * @throws ServletException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {		
		// pour le jour meme
		Calendar calendar = Calendar.getInstance();
		String monthS = "", dayS = "";

		int year = calendar.get(Calendar.YEAR);
		
		int monthI = calendar.get(Calendar.MONTH);
		monthI++; // commence à zero
		if (monthI<10) monthS+="0";
		monthS+=monthI;
		
		int dayI = calendar.get(Calendar.DAY_OF_MONTH);
		if (dayI<10) dayS+="0";
		dayS+=dayI;
		
		// pour la veille
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		String monthS2 = "", dayS2 = "";

		int year2 = calendar.get(Calendar.YEAR);
		
		int monthI2 = calendar.get(Calendar.MONTH);
		monthI2++; // commence à zero
		if (monthI2<10) monthS2+="0";
		monthS2+=monthI2;
		
		int dayI2 = calendar.get(Calendar.DAY_OF_MONTH);
		if (dayI2<10) dayS2+="0";
		dayS2+=dayI2;
		
		String scheduleID1 = null, scheduleID2 = null;
		boolean day = false, previousDay = false;
		int n = 0;
		do {
			if (!day) {
				scheduleID1 = APIRequest.getInstance().updateDailyScheduleRequest(Sport.NBA, year, monthS, dayS);
				day = callUpdateScore(scheduleID1);
			}
			if (!previousDay) {
				scheduleID2 = APIRequest.getInstance().updateDailyScheduleRequest(Sport.NBA, year2, monthS2, dayS2);
				previousDay = callUpdateScore(scheduleID2);
			}
			System.out.println("n:"+n+",scheduleID1:"+scheduleID1+",scheduleID2:"+scheduleID2+",day:"+day+",previousDay:"+previousDay);
		} while ( (scheduleID1==null || scheduleID2==null) && ++n < tries);
	}
	
	private boolean callUpdateScore(String scheduleID) {
		if (scheduleID==null) return false;
		
		boolean result = false;
		int m=0;
		do {
			result = APIRequest.getInstance().updateGameBoxscore(Sport.NBA, scheduleID);
		} while (!result && ++m < tries);
		return result;
	}
}
