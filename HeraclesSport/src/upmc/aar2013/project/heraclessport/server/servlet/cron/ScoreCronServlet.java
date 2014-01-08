package upmc.aar2013.project.heraclessport.server.servlet.cron;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import upmc.aar2013.project.heraclessport.server.tools.APIRequest;
import upmc.aar2013.project.heraclessport.server.configs.Sport;

/**
 * Servlet implementation class ScoreCronServlet
 */
public class ScoreCronServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private final int tries = 3;

	/**
	 * @throws ServletException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		/*
		une fois le modele en version final, l'idée est la suivante :
		mettre à jour les rencontre de la journée (nouvelle apirequest).
		pour chaque rencontre de la journée, 
		si elle est terminé et qu'on a pas de resultat correspondant à cette rencontre, récupérer le resultat.
		voir si on le fait pas pour les 2 dernier jours, au cas ou une rencontre fini après minuit !?
		*/
	}
}