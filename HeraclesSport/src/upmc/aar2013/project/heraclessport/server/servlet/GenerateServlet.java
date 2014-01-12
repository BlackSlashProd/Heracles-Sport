package upmc.aar2013.project.heraclessport.server.servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GenerateServlet extends HttpServlet {
	
	private static List<String> lst_rand_name = Arrays.asList(
			"MichaelJordan","AlainChabal","Olympius",
			"RobertPires","TonyParker","TigerWoods","MSchumacher",
			"YannickNoah","FootPower","NBYeaaaaah","NFoooooootL",
			"ChuckNorris","GiveMeYourBet","Goooooaaaaal"
	);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/plain");  
		response.setCharacterEncoding("UTF-8"); 
	    Random rand = new Random();
	    int randomName = rand.nextInt(lst_rand_name.size());
	    String name = lst_rand_name.get(randomName);
	    name += (rand.nextInt(900) + 100);
	    response.getWriter().write(name);		
	}
	
}
