package upmc.aar2013.project.heraclessport.server.servlet.cron;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import upmc.aar2013.project.heraclessport.server.datamodel.DataStore;
import upmc.aar2013.project.heraclessport.server.datamodel.ParisModel;
import upmc.aar2013.project.heraclessport.server.datamodel.ScheduleModel;
import upmc.aar2013.project.heraclessport.server.datamodel.ScoreResultModel;
import upmc.aar2013.project.heraclessport.server.datamodel.TeamModel;
import upmc.aar2013.project.heraclessport.server.datamodel.UserModel;
import upmc.aar2013.project.heraclessport.server.tools.APIRequest;
import upmc.aar2013.project.heraclessport.server.configs.Sport;

/**
 * Servlet implementation class TeamCronServlet
 */
public class TeamCronServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private final int tries = 3;

	/**
	 * @throws ServletException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int n = 0;
		do {
			n++;
		} while (!APIRequest.getInstance().updateLeagueHierarchyRequest(Sport.NBA) || n <= tries);
	}
}