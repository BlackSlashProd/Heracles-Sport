package upmc.aar2013.project.heraclessport.server.servlet.pages;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import upmc.aar2013.project.heraclessport.server.datamodel.api.DataStore;
import upmc.aar2013.project.heraclessport.server.datamodel.paris.ParisModel;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

/**
 * Servlet associée à la page Historique permettant d'afficher les informations sur 
 * les paris effectués.
 */
public class HistoricPageServlet extends HttpServlet {

	private static final long serialVersionUID = -4541267725137249836L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		if(user != null) {
			List<ParisModel> parisNotFinish = DataStore.getAllTeamParisByUser(user.getUserId(), false);
			List<ParisModel> parisFinish = DataStore.getAllTeamParisByUser(user.getUserId(), true);
			request.setAttribute("user", DataStore.getUser(user.getUserId()));
			request.setAttribute("parisNotFinish", parisNotFinish);
			request.setAttribute("parisFinish", parisFinish);
		}
		RequestDispatcher dispatch = request.getRequestDispatcher("jsp/pages/HistoricPage.jsp");  
        dispatch.forward(request, response);
	}
	
}
