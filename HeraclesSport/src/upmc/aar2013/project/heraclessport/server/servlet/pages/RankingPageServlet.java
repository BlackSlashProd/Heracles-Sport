package upmc.aar2013.project.heraclessport.server.servlet.pages;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class RankingPageServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4541267725137249836L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		/*
		 * TO DO : Cette classe va chercher en BDD le classement général des joueurs, vérifie si l'utilisateur est connecté,
		 * si oui on permet au joueur d'afficher la page de classement dans laquelle il se trouve.
		 * On pourra penser à éventuellement d'autres calculs intéressant. 
		 */
		RequestDispatcher dispatch = request.getRequestDispatcher("jsp/pages/RankingPage.jsp");  
        dispatch.forward(request, response);
	}
	
}
