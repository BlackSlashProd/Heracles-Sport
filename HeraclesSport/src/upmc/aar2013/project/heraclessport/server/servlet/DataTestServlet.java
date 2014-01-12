package upmc.aar2013.project.heraclessport.server.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import upmc.aar2013.project.heraclessport.server.configs.Sport;
import upmc.aar2013.project.heraclessport.server.datamodel.*;
import upmc.aar2013.project.heraclessport.server.datamodel.api.DataStore;
import upmc.aar2013.project.heraclessport.server.datamodel.paris.ParisScoreModel;
import upmc.aar2013.project.heraclessport.server.configs.Teams;
import upmc.aar2013.project.heraclessport.server.datamodel.paris.ParisVictoryModel;
import upmc.aar2013.project.heraclessport.server.datamodel.schedules.ScheduleModel;
import upmc.aar2013.project.heraclessport.server.datamodel.schedules.ScheduleTeamModel;
import upmc.aar2013.project.heraclessport.server.datamodel.schedules.ResultScoreModel;
import upmc.aar2013.project.heraclessport.server.datamodel.schedules.TeamModel;
import upmc.aar2013.project.heraclessport.server.datamodel.users.UserModel;
import upmc.aar2013.project.heraclessport.server.tools.APIRequest;

/**
 * Servlet implementation class MaServletaar
 */
public class DataTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private final int tries = 3;

	/**
	 * @throws ServletException 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String fct = request.getParameter("fct");
		RequestDispatcher dispatch = request.getRequestDispatcher("jsp/pages/HomePage.jsp");
		// Remplir BDD
		if(fct.compareTo("create")==0) {
			UserModel user = DataStore.getUser("user001");
			if(user==null) {
			// Users
			UserModel usermod001 = new UserModel("user001","fake_bob","fake_bob@fake.com");
			UserModel usermod002 = new UserModel("user002","fake_john","fake_john@fake.com");
			UserModel usermod003 = new UserModel("user003","fake_emilie","fake_emilie@fake.com");
			UserModel usermod004 = new UserModel("user004","fake_lola","fake_lola@fake.com");
			UserModel usermod005 = new UserModel("user005","fake_tom","fake_tom@fake.com");
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
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			Date date001 = null;
			Date date002 = null;
			Date date003 = null;
			Date date004 = null;
			Date date005 = null;
			try {
				date001 = sdf.parse("2014-01-22T20:15:00");
				date002 = sdf.parse("2014-02-10T20:20:00");
				date003 = sdf.parse("2014-03-25T20:00:00");
				date004 = sdf.parse("2014-10-01T20:25:00");
				date005 = sdf.parse("2014-02-29T20:45:00");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			ScheduleTeamModel schedmod001 = new ScheduleTeamModel(Sport.NBA,"sched001",date001, false, teammod001.getTeam_id(),teammod004.getTeam_id());
			ScheduleTeamModel schedmod002 = new ScheduleTeamModel(Sport.NBA,"sched002",date002, false, teammod002.getTeam_id(),teammod003.getTeam_id());
			ScheduleTeamModel schedmod003 = new ScheduleTeamModel(Sport.NBA,"sched003",date003, false, teammod003.getTeam_id(),teammod004.getTeam_id());
			ScheduleTeamModel schedmod004 = new ScheduleTeamModel(Sport.NBA,"sched004",date004, false, teammod003.getTeam_id(),teammod001.getTeam_id());
			ScheduleTeamModel schedmod005 = new ScheduleTeamModel(Sport.NBA,"sched005",date005, false, teammod004.getTeam_id(),teammod001.getTeam_id());
			DataStore.storeSchedule(schedmod001);
			DataStore.storeSchedule(schedmod002);
			DataStore.storeSchedule(schedmod003);
			DataStore.storeSchedule(schedmod004);
			DataStore.storeSchedule(schedmod005);
			
			// Paris
			ParisVictoryModel paris001 = new ParisVictoryModel(usermod005.getUser_id(), schedmod001.getSched_id(), 5, Teams.HOME);
			ParisVictoryModel paris002 = new ParisVictoryModel(usermod001.getUser_id(), schedmod001.getSched_id(), 2, Teams.AWAY);
			ParisScoreModel paris003 = new ParisScoreModel(usermod004.getUser_id(), schedmod001.getSched_id(), 2, Teams.ALL);
			paris003.setScore_team_home(97);
			paris003.setScore_team_away(84);
			DataStore.storeNewParis(paris001);
			DataStore.storeNewParis(paris002);
			DataStore.storeNewParis(paris003);

			//APIRequest.getInstance().updateScheduleRequest(Sport.NBA);
			//APIRequest.getInstance().updateLeagueHierarchyRequest(Sport.NBA);
			}

		} else if(fct.compareTo("remove")==0) {
			DataStore.cleanAll();
			UserService userService = UserServiceFactory.getUserService();
			response.sendRedirect(userService.createLogoutURL("/")); 
		} else if(fct.compareTo("finish")==0) {
			ScheduleModel stm = DataStore.getSchedule("sched001");
			if(stm!=null) {
				ResultScoreModel result001 = new ResultScoreModel("sched001", 80, 60);
				ResultScoreModel result002 = new ResultScoreModel("sched002", 80, 80);
				ResultScoreModel result003 = new ResultScoreModel("sched003", 80, 90);
				ResultScoreModel result004 = new ResultScoreModel("sched004", 20, 90);
				ResultScoreModel result005 = new ResultScoreModel("sched005", 13, 26);
				DataStore.storeResult(result001);
				DataStore.storeResult(result002);
				DataStore.storeResult(result003);
				DataStore.storeResult(result004);
				DataStore.storeResult(result005);
				DataStore.updateSchedule("sched001", result001);
				DataStore.updateSchedule("sched002", result002);
				DataStore.updateSchedule("sched003", result003);
				DataStore.updateSchedule("sched004", result004);
				DataStore.updateSchedule("sched005", result005);
			}
		}   
		
		response.sendRedirect("/");
	}
	
	private boolean callUpdateScore(String scheduleID) {
		if (scheduleID==null) return false;
		
		boolean result = false;
		int m=0;
		do {
			m++;
			result = APIRequest.getInstance().updateGameBoxscore(Sport.NBA, scheduleID);
		} while (!result && m <= tries);
		return result;
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		RequestDispatcher dispatch = request.getRequestDispatcher("jsp/pages/HomePage.jsp");  
        dispatch.forward(request, response);
	}
}