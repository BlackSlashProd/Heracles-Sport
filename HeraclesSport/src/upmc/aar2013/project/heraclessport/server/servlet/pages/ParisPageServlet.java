package upmc.aar2013.project.heraclessport.server.servlet.pages;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class ParisPageServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4541267725137249836L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		/*
		 * TO DO : Cette classe va chercher en BDD la liste des rencontres, vérifie si l'utilisateur est connecté,
		 * si oui on permet au joueur de parier sur les paris sur lesquels il n'a pas encore parié, sinon (utilisateur non connecté)
		 * on liste simplement toutes les rencontres.
		 */
		RequestDispatcher dispatch = request.getRequestDispatcher("jsp/pages/ParisPage.jsp");  
        dispatch.forward(request, response);
	}
	
}
