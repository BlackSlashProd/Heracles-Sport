package upmc.aar2013.project.heraclessport.server.servlet.pages;

import java.io.IOException;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

/**
 * Servlet implementation class MaServletaar
 */
public class TestJspServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @throws ServletException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	    Random rand = new Random();
		int randInt = rand.nextInt(101);
		request.setAttribute("randInt", randInt);
		RequestDispatcher dispatch = request.getRequestDispatcher("jsp/pages/TestJSP.jsp");  
        dispatch.forward(request, response);
	}
}