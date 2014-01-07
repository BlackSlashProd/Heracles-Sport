package upmc.aar2013.project.heraclessport.server.servlet.pages;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import upmc.aar2013.project.heraclessport.server.datamodel.DataStore;
import upmc.aar2013.project.heraclessport.server.datamodel.ScheduleModel;
import upmc.aar2013.project.heraclessport.server.datamodel.ScheduleTeamModel;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class ParisPageServlet extends HttpServlet {

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
	
}
