package upmc.aar2013.project.heraclessport.server.datamodel;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;

public class DataStore {
    static {
        ObjectifyService.register(UserModel.class);
        ObjectifyService.register(TeamModel.class);
        ObjectifyService.register(ScheduleModel.class);
    }
    
    public static void cleanAll() {
    	// Users
    	ofy().delete().entities(getAllUsers());
    	// Teams
    	ofy().delete().entities(getAllTeams());
    	// Schedules
    	ofy().delete().entities(getAllSchedules());
    	// ...
    }
    /*
     * Users Methods
     */
	public static UserModel getUser(String userId) {
		return ofy().load().type(UserModel.class).filter("user_id", userId).first().now();
	}
	
	public static UserModel getUserByPseudo(String userPsd) {
		return ofy().load().type(UserModel.class).filter("user_pseudo", userPsd).first().now();
	}
	
	public static void storeUser(UserModel um) {
		ofy().save().entity(um).now();
	}
	
	public static List<UserModel> getAllUsers() {
		return ofy().load().type(UserModel.class).list();
	}
	
	public static List<UserModel> getAllUsersOrderBy(String order) {
		return ofy().load().type(UserModel.class).order("-"+order).list();
	}
	/*
	 * Teams Methods
	 */
	public static TeamModel getTeam(String teamId) {
		return ofy().load().type(TeamModel.class).filter("team_id", teamId).first().now();
	}	
	public static TeamModel getTeam(Key<TeamModel> teamKey) {
		return ofy().load().key(teamKey).now();
	}
	public static List<TeamModel> getAllTeams() {
		return ofy().load().type(TeamModel.class).list();
	}
	public static void storeTeam(TeamModel team) {
		ofy().save().entity(team).now();
	}
	/*
	 * Schedules Methods
	 */
	public static ScheduleModel getSchedule(String schedId) {
		return ofy().load().type(ScheduleModel.class).filter("sched_id", schedId).first().now();
	}	
	public static List<ScheduleModel> getAllSchedules() {
		return ofy().load().type(ScheduleModel.class).list();
	}
	public static List<ScheduleModel> getAllSchedulesOrderBy(String order) {
		return ofy().load().type(ScheduleModel.class).order("-"+order).list();
	}
	public static List<ScheduleModel> getAllSchedulesNotFinish(String order) {
		return ofy().load().type(ScheduleModel.class).filter("sched_isFinish", false).order("-"+order).list();
	}
	public static void storeSchedule(ScheduleModel sched) {
		ofy().save().entity(sched).now();
	}
}
