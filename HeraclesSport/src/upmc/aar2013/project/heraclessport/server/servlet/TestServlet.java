package upmc.aar2013.project.heraclessport.server.servlet;

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
		rep.setContentType("text/html");
		rep.getWriter().println("<!DOCTYPE html>");
		rep.getWriter().println("<html lang=\"fr\">");
		rep.getWriter().println("<head>");
		rep.getWriter().println("<meta charset=\"utf-8\">");
		rep.getWriter().println("<title>Heracles Sport</title>");
		rep.getWriter().println("</head>");
		rep.getWriter().println("<body>");
		rep.getWriter().println("<h1>Test Servlet Works Fine !!!</h1>");
		rep.getWriter().println("<p>Back to <a href=\"/\">Home</a></p>");
		rep.getWriter().println("</body>");
		rep.getWriter().println("</html>");
		
	}
}
