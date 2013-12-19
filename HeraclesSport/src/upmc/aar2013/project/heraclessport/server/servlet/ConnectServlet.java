package upmc.aar2013.project.heraclessport.server.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import upmc.aar2013.project.heraclessport.server.beans.BeanUser;
import upmc.aar2013.project.heraclessport.server.front.forms.ConnexionForm;

public class ConnectServlet extends HttpServlet {
	
    public static final String JSP_VAR_USER = "user";
    public static final String JSP_VAR_FORM = "form";
    public static final String JSP_VAR_SESSION = "session";

	/**
	 * 
	 */
	private static final long serialVersionUID = 7386318321956495417L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException {
		RequestDispatcher dispatch = request.getRequestDispatcher("/jsp/pages/HomePage.jsp");  
        dispatch.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		/*
		 * TO DO : 
		 */
		ConnexionForm form = new ConnexionForm();
		BeanUser user = form.userConnect(request);
        HttpSession session = request.getSession();
        if (form.getErrors().isEmpty()) {
        	session.setAttribute(JSP_VAR_SESSION, user);
        } else {
            session.setAttribute(JSP_VAR_SESSION, null);
        }
        request.setAttribute(JSP_VAR_FORM, form);
        request.setAttribute(JSP_VAR_USER, user);
		RequestDispatcher dispatch = request.getRequestDispatcher("jsp/pages/HomePage.jsp");  
        dispatch.forward(request, response);
	}
	
}
