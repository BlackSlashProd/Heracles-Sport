package upmc.aar2013.project.heraclessport.server.servlet.pages;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import upmc.aar2013.project.heraclessport.server.datamodel.api.DataStore;
import upmc.aar2013.project.heraclessport.server.datamodel.users.UserModel;

public class RankingPageServlet extends HttpServlet {

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
		List<UserModel> players = DataStore.getAllUsersOrderBy("user_point");
		request.setAttribute("players", players);
		RequestDispatcher dispatch = request.getRequestDispatcher("jsp/pages/RankingPage.jsp");  
        dispatch.forward(request, response);
	}
	
}
