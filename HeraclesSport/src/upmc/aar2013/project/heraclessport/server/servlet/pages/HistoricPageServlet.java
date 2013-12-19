package upmc.aar2013.project.heraclessport.server.servlet.pages;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class HistoricPageServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4541267725137249836L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		/*
		 * TO DO : Cette classe va chercher en BDD la liste des paris effectués par le joueur.
		 */
		RequestDispatcher dispatch = request.getRequestDispatcher("jsp/pages/HistoricPage.jsp");  
        dispatch.forward(request, response);
	}
	
}
