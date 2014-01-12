package upmc.aar2013.project.heraclessport.server.servlet.cron;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import upmc.aar2013.project.heraclessport.server.configs.Configs;
import upmc.aar2013.project.heraclessport.server.datamodel.api.DataStore;
import upmc.aar2013.project.heraclessport.server.datamodel.users.UserModel;

/**
 * Servlet Cron : ajout journalier de point.
 */
public class DailyBonusCronServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/**
	 * @throws ServletException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		System.out.println("appel de la servlet cron DailyBonus");
		List<UserModel> users = DataStore.getAllUsers();
		for (UserModel user : users) {
			user.addUserPoint(Configs.getDailyPoint());
			DataStore.storeUser(user);
		}
	}
}