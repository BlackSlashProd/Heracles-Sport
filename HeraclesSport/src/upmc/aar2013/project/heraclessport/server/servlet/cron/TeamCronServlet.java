package upmc.aar2013.project.heraclessport.server.servlet.cron;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import upmc.aar2013.project.heraclessport.server.tools.APIRequest;
import upmc.aar2013.project.heraclessport.server.configs.Sport;

/**
 * Servlet Cron : Récupération des informations sur les équipes.
 */
public class TeamCronServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private final int tries = 3;

	/**
	 * @throws ServletException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int n = 0;
		do {
		} while (!APIRequest.getInstance().updateLeagueHierarchyRequest(Sport.NBA) && ++n < tries);
	}
}