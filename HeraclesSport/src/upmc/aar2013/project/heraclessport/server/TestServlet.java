package upmc.aar2013.project.heraclessport.server;

import java.io.IOException;
import javax.servlet.http.*;

public class TestServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5196999564731776219L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse rep) 
			throws IOException {
		rep.setContentType("text/plain");
		rep.getWriter().println("Test Servlet Works Fine !!!");
		
	}
}
