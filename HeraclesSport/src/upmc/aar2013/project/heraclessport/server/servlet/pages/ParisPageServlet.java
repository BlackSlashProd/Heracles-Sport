package upmc.aar2013.project.heraclessport.server.servlet.pages;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import upmc.aar2013.project.heraclessport.server.configs.Teams;
import upmc.aar2013.project.heraclessport.server.datamodel.api.DataStore;
import upmc.aar2013.project.heraclessport.server.datamodel.paris.ParisScoreModel;
import upmc.aar2013.project.heraclessport.server.datamodel.paris.ParisVictoryModel;
import upmc.aar2013.project.heraclessport.server.datamodel.schedules.ScheduleTeamModel;
import upmc.aar2013.project.heraclessport.server.datamodel.users.UserModel;
import upmc.aar2013.project.heraclessport.server.front.forms.AccountForm;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class ParisPageServlet extends HttpServlet {

	public static final String JSP_VAR_FORM = "form";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4541267725137249836L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		List<ScheduleTeamModel> schedules = DataStore.getAllTeamSchedulesByFinish("sched_date", false);
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		if(user != null) {
			request.setAttribute("user",DataStore.getUser(user.getUserId()));
		}
		request.setAttribute("schedules", schedules);
		RequestDispatcher dispatch = request.getRequestDispatcher("jsp/pages/ParisPage.jsp");  
        dispatch.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		
		List<ScheduleTeamModel> schedules = DataStore.getAllTeamSchedulesByFinish("sched_date", false);
		String active = request.getParameter("sched_id");
		request.setAttribute("active", active);
		request.setAttribute("schedules", schedules);
		
		if(user != null) {
			request.setAttribute("user",DataStore.getUser(user.getUserId()));
			
			String sched_id = request.getParameter("sched_id");
			String paris_type = request.getParameter("paris_type");
			String paris_vict = request.getParameter("paris_vict");
			String paris_scor_eqp = request.getParameter("paris_scor_eqp");
			String paris_scor_teamhome = request.getParameter("paris_scor_teamhome");
			String paris_scor_teamaway = request.getParameter("paris_scor_teamaway");
			String paris_bet = request.getParameter("paris_bet");
			
			if (paris_type.equals("vict")) {
				Teams team = Teams.ALL;
				if (paris_vict.equals("teamhome")) team = Teams.HOME;
				if (paris_vict.equals("teamaway")) team = Teams.AWAY;
				try {
					int paris_bet_int = Integer.parseInt(paris_bet);
					ParisVictoryModel parisVictoryModel = new ParisVictoryModel(user.getUserId(), sched_id, paris_bet_int, team);
					DataStore.storeParis(parisVictoryModel);
					System.out.println("parisVictoryModel stocké");
				} catch (NumberFormatException e) {} // Integer.parseInt
			}
			
			if (paris_type.equals("scor")) {
				Teams team = Teams.ALL;
				if (paris_scor_eqp.equals("home")) team = Teams.HOME;
				if (paris_scor_eqp.equals("away")) team = Teams.AWAY;
				try {
					int paris_bet_int = Integer.parseInt(paris_bet);
					int paris_scor_teamhome_int = Integer.parseInt(paris_scor_teamhome);
					int paris_scor_teamaway_int = Integer.parseInt(paris_scor_teamaway);
					ParisScoreModel parisScoreModel = new ParisScoreModel(user.getUserId(), active, paris_bet_int, team);
					parisScoreModel.setScore_team_home(paris_scor_teamhome_int);
					parisScoreModel.setScore_team_away(paris_scor_teamaway_int);
					DataStore.storeParis(parisScoreModel);
					System.out.println("ParisScoreModel stocké");
				} catch (NumberFormatException e) {} // Integer.parseInt
			}
		}

		RequestDispatcher dispatch = request.getRequestDispatcher("jsp/pages/ParisPage.jsp");  
        dispatch.forward(request, response);	
	}
	
}
