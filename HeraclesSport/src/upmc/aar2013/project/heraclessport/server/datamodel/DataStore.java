package upmc.aar2013.project.heraclessport.server.datamodel;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.List;

import upmc.aar2013.project.heraclessport.server.datamodel.ResultModel.RES_TEAM;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;

public class DataStore {
	
    static {
        ObjectifyService.register(UserModel.class);
        ObjectifyService.register(TeamModel.class);
        ObjectifyService.register(ScheduleModel.class);
        ObjectifyService.register(ScoreResultModel.class);
        ObjectifyService.register(ParisModel.class);
    }
    
    public static void cleanAll() {
    	// Users
    	ofy().delete().entities(getAllUsers());
    	// Teams
    	ofy().delete().entities(getAllTeams());
    	// Schedules
    	ofy().delete().entities(getAllSchedules());
    	// Results
    	ofy().delete().entities(getAllScoreResults());
    	// Paris
    	ofy().delete().entities(getAllParis());
    	// ...
    }

    /*
     * Users Methods
     */
    public static Key<UserModel> createUserKey(String userId) {
    	return Key.create(UserModel.class,userId);
    }
	public static UserModel getUser(String userId) {
		Key<UserModel> userKey = createUserKey(userId);
		return ofy().load().key(userKey).now();
	}
	
	public static UserModel getUserByPseudo(String userPsd) {
		return ofy().load().type(UserModel.class).filter("user_pseudo", userPsd).first().now();
	}
	
	public static void storeUser(UserModel um) {
		ofy().save().entity(um).now();
	}
	
	public static List<UserModel> getAllUsers() {
		return (List<UserModel>)ofy().load().type(UserModel.class).list();
	}
	
	public static List<UserModel> getAllUsersOrderBy(String order) {
		return ofy().load().type(UserModel.class).order("-"+order).list();
	}
	/*
	 * Teams Methods
	 */
    public static Key<TeamModel> createTeamKey(String teamId) {
    	return Key.create(TeamModel.class,teamId);
    }
	public static TeamModel getTeam(String teamId) {
		Key<TeamModel> teamKey = createTeamKey(teamId);
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
    public static Key<ScheduleModel> createScheduleKey(String schedId) {
    	return Key.create(ScheduleModel.class,schedId);
    }
	public static ScheduleModel getSchedule(String schedId) {
		Key<ScheduleModel> schedKey = createScheduleKey(schedId);
		return ofy().load().key(schedKey).now();
	}	
	public static List<ScheduleModel> getAllSchedules() {
		return ofy().load().type(ScheduleModel.class).list();
	}
	public static List<ScheduleModel> getAllSchedulesOrderBy(String order) {
		return ofy().load().type(ScheduleModel.class).order("-"+order).list();
	}
	public static List<ScheduleModel> getAllSchedulesByFinish(String order, boolean isFinish) {
		return (List<ScheduleModel>)ofy().load().type(ScheduleModel.class).filter("sched_isFinish", isFinish).order("-"+order).list();
	}
	public static void storeSchedule(ScheduleModel sched) {
		ofy().save().entity(sched).now();
	}
	/*
	 * Results Methods 
	 */
    public static Key<ResultModel> createResultKey(Long resId) {
    	return Key.create(ResultModel.class,resId);
    }
    public static ResultModel getResult(Long resId) {
    	Key<ResultModel> result = createResultKey(resId);
    	return ofy().load().key(result).now();
    }
	public static List<ScoreResultModel> getAllScoreResults() {
		return ofy().load().type(ScoreResultModel.class).list();
	}
	public static List<ScoreResultModel> getScoreResultsBySchedule(String schedId) {
		Key<ScheduleModel> schedKey = createScheduleKey(schedId);
		return (List<ScoreResultModel>)(ofy().load().type(ScoreResultModel.class).filter("res_sched_key", schedKey).filter("res_team", RES_TEAM.ALL).list());
	}
	public static void storeResult(ResultModel res) {
		ofy().save().entity(res).now();
	}
	/*
	 * Paris Methods
	 */
	public static void storeParis(ParisModel mod) {
		// ATTENTION : Vérifier clé unique (schedule+user).
		// TODO : Enlever des points au user.
		ofy().save().entity(mod).now();
	}
	public static List<ParisModel> getAllParis() {
		return ofy().load().type(ParisModel.class).list();
	}
}
