package upmc.aar2013.project.heraclessport.server.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import upmc.aar2013.project.heraclessport.server.datamodel.DataStore;
import upmc.aar2013.project.heraclessport.server.datamodel.UserModel;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

/**
 * Servlet implementation class MaServletaar
 */
public class ConnectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/**
	 * @throws ServletException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		UserModel usermod = null;
		if(user!=null) {
			usermod = DataStore.getUser(user.getUserId());
			if(usermod==null) {
				usermod = new UserModel(user.getUserId(),user.getNickname(),user.getEmail());
				DataStore.storeUser(usermod);
			}
		}		
		RequestDispatcher dispatch = request.getRequestDispatcher("jsp/pages/HomePage.jsp");  
        dispatch.forward(request, response);
	}
}