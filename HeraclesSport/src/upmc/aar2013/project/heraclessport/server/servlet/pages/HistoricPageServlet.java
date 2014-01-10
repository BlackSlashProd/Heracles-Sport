package upmc.aar2013.project.heraclessport.server.servlet.pages;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import upmc.aar2013.project.heraclessport.server.datamodel.api.DataStore;
import upmc.aar2013.project.heraclessport.server.datamodel.paris.ParisModel;
import upmc.aar2013.project.heraclessport.server.datamodel.paris.ParisScoreModel;
import upmc.aar2013.project.heraclessport.server.datamodel.paris.ParisVictoryModel;
import upmc.aar2013.project.heraclessport.server.datamodel.schedules.ScheduleModel;
import upmc.aar2013.project.heraclessport.server.datamodel.schedules.ScheduleTeamModel;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class HistoricPageServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4541267725137249836L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		if(user != null) {
			System.out.println(user.getUserId()); // à supprimer
			List<ParisModel> paris = DataStore.getAllTeamParisByUser(user.getUserId(), false); // devrait etre true mais pour essaie, car mis a faux par defaut
			System.out.println("nombre de paris : " + paris.size()); // à supprimer
			request.setAttribute("user", DataStore.getUser(user.getUserId()));
			request.setAttribute("paris", paris);
		}
		RequestDispatcher dispatch = request.getRequestDispatcher("jsp/pages/HistoricPage.jsp");  
        dispatch.forward(request, response);
	}
	
}
