package upmc.aar2013.project.heraclessport.server.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class DisconnectServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6588208742737437L;

	@Override
    public void doGet(HttpServletRequest request, HttpServletResponse response ) 
    		throws ServletException, IOException {
		
        HttpSession session = request.getSession();
        session.invalidate();
		RequestDispatcher dispatch = request.getRequestDispatcher("/jsp/pages/HomePage.jsp");  
        dispatch.forward(request, response);
    }	
	
}
