package upmc.aar2013.project.heraclessport.server.servlet.pages;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import upmc.aar2013.project.heraclessport.server.datamodel.DataStore;
import upmc.aar2013.project.heraclessport.server.datamodel.ScheduleModel;
import upmc.aar2013.project.heraclessport.server.tools.APIRequest;
import upmc.aar2013.project.heraclessport.server.tools.Sport;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class ParisPageServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4541267725137249836L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		APIRequest.getInstance().getLeagueHierarchyRequest(Sport.NBA);
		APIRequest.getInstance().getTeamProfileRequest(Sport.NBA, "583ec8d4-fb46-11e1-82cb-f4ce4684ea4c");
		APIRequest.getInstance().getScheduleRequest(Sport.NBA);
		APIRequest.getInstance().getGameBoxscore(Sport.NBA, "0b3d21c7-c13f-4ee8-8d9d-4f334754c7e4");
		
		List<ScheduleModel> schedules = DataStore.getAllSchedulesNotFinish("sched_date");
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		if(user != null) {
			request.setAttribute("user",DataStore.getUser(user.getUserId()));
		}
		request.setAttribute("schedules", schedules);
		RequestDispatcher dispatch = request.getRequestDispatcher("jsp/pages/ParisPage.jsp");  
        dispatch.forward(request, response);
	}
	
}
