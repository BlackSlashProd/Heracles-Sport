package upmc.aar2013.project.heraclessport.server;

import java.io.IOException;

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
		int randInt = 42;
		request.setAttribute("randInt", randInt);
		RequestDispatcher dispatch = request.getRequestDispatcher("jsp/TestJSP.jsp");  
        dispatch.forward(request, response);
	}
}