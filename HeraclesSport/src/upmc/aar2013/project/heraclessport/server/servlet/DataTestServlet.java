package upmc.aar2013.project.heraclessport.server.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.Key;

import upmc.aar2013.project.heraclessport.server.datamodel.DataStore;
import upmc.aar2013.project.heraclessport.server.datamodel.ParisModel;
import upmc.aar2013.project.heraclessport.server.datamodel.ResultModel;
import upmc.aar2013.project.heraclessport.server.datamodel.ResultModel.SCORE_TEAM;
import upmc.aar2013.project.heraclessport.server.datamodel.ScheduleModel;
import upmc.aar2013.project.heraclessport.server.datamodel.ScoreResultModel;
import upmc.aar2013.project.heraclessport.server.datamodel.TeamModel;
import upmc.aar2013.project.heraclessport.server.datamodel.UserModel;
import upmc.aar2013.project.heraclessport.server.tools.APIRequest;
import upmc.aar2013.project.heraclessport.server.tools.Sport;

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
			Key<TeamModel> team001 = Key.create(TeamModel.class, teammod001.getTeam_id());
			Key<TeamModel> team002 = Key.create(TeamModel.class, teammod002.getTeam_id());
			Key<TeamModel> team003 = Key.create(TeamModel.class, teammod003.getTeam_id());
			Key<TeamModel> team004 = Key.create(TeamModel.class, teammod004.getTeam_id());
			
			ScheduleModel schedmod001 = new ScheduleModel("sched001",team001,team004,new Date(2013,12,10,20,20,00));
			schedmod001.setSched_isFinish(true);
			ScheduleModel schedmod002 = new ScheduleModel("sched002",team002,team003,new Date(2013,12,10,20,20,00));
			schedmod002.setSched_isFinish(true);
			ScheduleModel schedmod003 = new ScheduleModel("sched003",team003,team004,new Date(2013,12,31,20,20,00));
			ScheduleModel schedmod004 = new ScheduleModel("sched004",team003,team001,new Date(2013,1,5,20,00,00));
			ScheduleModel schedmod005 = new ScheduleModel("sched005",team004,team001,new Date());
			DataStore.storeSchedule(schedmod001);
			DataStore.storeSchedule(schedmod002);
			DataStore.storeSchedule(schedmod003);
			DataStore.storeSchedule(schedmod004);
			DataStore.storeSchedule(schedmod005);
			
			// Results
			/*Key<ScheduleModel> sched001 = Key.create(ScheduleModel.class, schedmod001.getSched_id());
			ScoreResultModel score001 = new ScoreResultModel(sched001,SCORE_TEAM.HOME);
			score001.setScore_res_score_home(4);
			ScoreResultModel score002 = new ScoreResultModel(sched001,SCORE_TEAM.AWAY);
			score002.setScore_res_score_away(2);
			DataStore.storeResult(score001);
			DataStore.storeResult(score002);*/
			
			// Paris
			/*Key<ScheduleModel> sched003 = Key.create(ScheduleModel.class, schedmod003.getSched_id());
			Key<UserModel> user001 = Key.create(UserModel.class, usermod003.getUser_id());
			ScoreResultModel scoremod003 = new ScoreResultModel(sched003,SCORE_TEAM.HOME);
			scoremod003.setScore_res_score_home(4);
			DataStore.storeResult(scoremod003);
			Key<ResultModel> score003 = Key.create(ResultModel.class, scoremod003.getRes_id());
			ParisModel paris001 = new ParisModel(user001,score003, 2);
			DataStore.storeParis(paris001);*/
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
