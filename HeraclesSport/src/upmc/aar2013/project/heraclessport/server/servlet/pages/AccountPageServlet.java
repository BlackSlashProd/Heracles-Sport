package upmc.aar2013.project.heraclessport.server.servlet.pages;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import upmc.aar2013.project.heraclessport.server.datamodel.api.DataStore;
import upmc.aar2013.project.heraclessport.server.datamodel.users.UserModel;
import upmc.aar2013.project.heraclessport.server.front.forms.AccountForm;

public class AccountPageServlet extends HttpServlet {

	public static final String JSP_VAR_FORM = "form";
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4541267725137249836L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		UserModel usermod = DataStore.getUser(user.getUserId());
		request.setAttribute("user", usermod);
		RequestDispatcher dispatch = request.getRequestDispatcher("/jsp/pages/AccountPage.jsp");  
        dispatch.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();	
		
		AccountForm accform = new AccountForm();
		UserModel usermod = accform.modifyAccount(request, user.getUserId());
		
		request.setAttribute("user", usermod);
		request.setAttribute(JSP_VAR_FORM, accform);
		
		RequestDispatcher dispatch = request.getRequestDispatcher("/jsp/pages/AccountPage.jsp");  
        dispatch.forward(request, response);		
	}
	
}
