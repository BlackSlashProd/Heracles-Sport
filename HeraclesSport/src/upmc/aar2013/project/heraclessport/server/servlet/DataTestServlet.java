package upmc.aar2013.project.heraclessport.server.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import upmc.aar2013.project.heraclessport.server.configs.Sport;
import upmc.aar2013.project.heraclessport.server.datamodel.*;
import upmc.aar2013.project.heraclessport.server.tools.APIRequest;

/**
 * Servlet implementation class MaServletaar
 */
public class DataTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/**
	 * @throws ServletException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String fct = request.getParameter("fct");
		RequestDispatcher dispatch = request.getRequestDispatcher("jsp/pages/HomePage.jsp");
		// Remplir BDD
		if(fct.compareTo("create")==0) {
			// Users
			UserModel usermod001 = new UserModel("user001","fake_bob","fake_bob@fake.com");
			UserModel usermod002 = new UserModel("user002","fake_john","fake_john@fake.com");
			usermod002.setUser_point(0);
			UserModel usermod003 = new UserModel("user003","fake_emilie","fake_emilie@fake.com");
			usermod003.setUser_point(7);
			UserModel usermod004 = new UserModel("user004","fake_lola","fake_lola@fake.com");
			usermod004.setUser_point(16);
			UserModel usermod005 = new UserModel("user005","fake_tom","fake_tom@fake.com");
			usermod005.setUser_point(42);
			DataStore.storeUser(usermod001);
			DataStore.storeUser(usermod002);
			DataStore.storeUser(usermod003);
			DataStore.storeUser(usermod004);
			DataStore.storeUser(usermod005);
			// Teams
			TeamModel teammod001 = new TeamModel("team001","fake_ChicagoBulls","Chicago","Central");
			TeamModel teammod002 = new TeamModel("team002","fake_BostonCeltics","Boston","Atlantic");
			TeamModel teammod003 = new TeamModel("team003","fake_NewYorkNicks","New-York","Atlantic");
			TeamModel teammod004 = new TeamModel("team004","fake_HoustonRockets","Houston","Southwest");
			DataStore.storeTeam(teammod001);
			DataStore.storeTeam(teammod002);
			DataStore.storeTeam(teammod003);
			DataStore.storeTeam(teammod004);
			// Schedules
			ScheduleModel schedmod001 = new ScheduleTeamModel("sched001",Sport.NBA.getName(),new Date(2013,12,10,20,20,00),teammod001.getTeam_id(),teammod004.getTeam_id());
			schedmod001.setSched_isFinish(true);
			ScheduleModel schedmod002 = new ScheduleTeamModel("sched002",Sport.NBA.getName(),new Date(2013,12,10,20,20,00),teammod002.getTeam_id(),teammod003.getTeam_id());
			schedmod002.setSched_isFinish(true);
			ScheduleModel schedmod003 = new ScheduleTeamModel("sched003",Sport.NBA.getName(),new Date(2013,12,31,20,20,00),teammod003.getTeam_id(),teammod004.getTeam_id());
			ScheduleModel schedmod004 = new ScheduleTeamModel("sched004",Sport.NBA.getName(),new Date(2013,1,5,20,00,00),teammod003.getTeam_id(),teammod001.getTeam_id());
			ScheduleModel schedmod005 = new ScheduleTeamModel("sched005",Sport.NBA.getName(),new Date(),teammod004.getTeam_id(),teammod001.getTeam_id());
			DataStore.storeSchedule(schedmod001);
			DataStore.storeSchedule(schedmod002);
			DataStore.storeSchedule(schedmod003);
			DataStore.storeSchedule(schedmod004);
			DataStore.storeSchedule(schedmod005);
			
			// Results
			ScoreResultModel score001 = new ScoreResultModel(schedmod001.getSched_id(), 95, 84);
			DataStore.storeResult(score001);
			ScoreResultModel score002 = new ScoreResultModel(schedmod002.getSched_id(), 97, 114);
			DataStore.storeResult(score002);
			
			// Paris
			//ScoreResultModel scoremod003 = new ScoreResultModel(schedmod003.getSched_id());
			//scoremod003.setScore_res_score_home(4);
			//DataStore.storeResult(scoremod003);

			// temp
			//APIRequest.getInstance().updateScheduleRequest(Sport.NBA);
			//APIRequest.getInstance().updateLeagueHierarchyRequest(Sport.NBA);
			//APIRequest.getInstance().updateTeamProfileRequest(Sport.NBA, "583ec7cd-fb46-11e1-82cb-f4ce4684ea4c");
			//APIRequest.getInstance().updateGameBoxscore(Sport.NBA, "0b3d21c7-c13f-4ee8-8d9d-4f334754c7e4");
			//APIRequest.getInstance().updateDailyScheduleRequest(Sport.NBA);
		} 
		else if(fct.compareTo("remove")==0) {
			DataStore.cleanAll();
			UserService userService = UserServiceFactory.getUserService();
			response.sendRedirect(userService.createLogoutURL("/")); 
		}   
		
        dispatch.forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher dispatch = request.getRequestDispatcher("jsp/pages/HomePage.jsp");  
        dispatch.forward(request, response);
	}
}