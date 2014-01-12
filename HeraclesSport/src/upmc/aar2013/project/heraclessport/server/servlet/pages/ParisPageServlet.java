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
import upmc.aar2013.project.heraclessport.server.front.forms.ParisForm;

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
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		if(user != null) {
			UserModel usermod = DataStore.getUser(user.getUserId());
			if(usermod==null) response.sendRedirect(userService.createLogoutURL("/"));
			request.setAttribute("user",usermod);
		}		
		List<ScheduleTeamModel> schedules = DataStore.getAllTeamSchedulesNotStarted("sched_date");
		request.setAttribute("schedules", schedules);
		RequestDispatcher dispatch = request.getRequestDispatcher("jsp/pages/ParisPage.jsp");  
        dispatch.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		if(user != null) {
			UserModel usermod = DataStore.getUser(user.getUserId());
			if(usermod==null) response.sendRedirect(userService.createLogoutURL("/"));
			request.setAttribute("user",usermod);
		}
		List<ScheduleTeamModel> schedules = DataStore.getAllTeamSchedulesByFinish("sched_date", false);
		String active = request.getParameter("sched_id");
		request.setAttribute("active", active);
		request.setAttribute("schedules", schedules);
		if(user != null) {
			ParisForm parisform = new ParisForm();
			parisform.createParis(request, user.getUserId());
			request.setAttribute(JSP_VAR_FORM, parisform);
		}

		RequestDispatcher dispatch = request.getRequestDispatcher("jsp/pages/ParisPage.jsp");  
        dispatch.forward(request, response);	
	}
	
}
