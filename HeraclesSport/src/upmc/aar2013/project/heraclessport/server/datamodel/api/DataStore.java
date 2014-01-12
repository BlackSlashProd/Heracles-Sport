package upmc.aar2013.project.heraclessport.server.datamodel.api;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import upmc.aar2013.project.heraclessport.server.configs.Configs;
import upmc.aar2013.project.heraclessport.server.configs.Teams;
import upmc.aar2013.project.heraclessport.server.datamodel.paris.ParisModel;
import upmc.aar2013.project.heraclessport.server.datamodel.paris.ParisScoreModel;
import upmc.aar2013.project.heraclessport.server.datamodel.paris.ParisVictoryModel;
import upmc.aar2013.project.heraclessport.server.datamodel.schedules.ResultModel;
import upmc.aar2013.project.heraclessport.server.datamodel.schedules.ScheduleModel;
import upmc.aar2013.project.heraclessport.server.datamodel.schedules.ScheduleTeamModel;
import upmc.aar2013.project.heraclessport.server.datamodel.schedules.ResultScoreModel;
import upmc.aar2013.project.heraclessport.server.datamodel.schedules.TeamModel;
import upmc.aar2013.project.heraclessport.server.datamodel.users.UserModel;
import upmc.aar2013.project.heraclessport.server.tools.ServerMail;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;

public class DataStore {
	
    static {
        ObjectifyService.register(UserModel.class);
        ObjectifyService.register(TeamModel.class);
        ObjectifyService.register(ScheduleModel.class);
        ObjectifyService.register(ScheduleTeamModel.class);
        ObjectifyService.register(ResultScoreModel.class);
        ObjectifyService.register(ParisModel.class);
        ObjectifyService.register(ParisScoreModel.class);
        ObjectifyService.register(ParisVictoryModel.class);
    }
    
    public static void cleanAll() {
    	// Users
    	ofy().delete().entities(getAllUsers());
    	// Teams
    	ofy().delete().entities(getAllTeams());
    	// Schedules
    	ofy().delete().entities(getAllTeamSchedules());
    	// Results
    	ofy().delete().entities(getAllScoreResults());
    	// Paris
    	ofy().delete().entities(getAllScoreParis());
    	ofy().delete().entities(getAllVictoryParis());
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
    public static Key<ScheduleTeamModel> createScheduleTeamKey(String schedId) {
    	return Key.create(ScheduleTeamModel.class,schedId);
    }
	public static ScheduleModel getSchedule(String schedId) {
		Key<ScheduleTeamModel> schedKey = createScheduleTeamKey(schedId);
		return ofy().load().key(schedKey).now();
	}	
	public static List<ScheduleTeamModel> getAllTeamSchedules() {
		return ofy().load().type(ScheduleTeamModel.class).list();
	}
	public static List<ScheduleTeamModel> getAllTeamSchedules(String sport) {
		return ofy().load().type(ScheduleTeamModel.class).filter("sched_sport",sport).list();
	}
	public static List<ScheduleTeamModel> getAllTeamSchedulesOrderBy(String order) {
		return ofy().load().type(ScheduleTeamModel.class).order("-"+order).list();
	}
	public static List<ScheduleTeamModel> getAllTeamSchedulesByFinish(String order, boolean isFinish) {
		List<ScheduleTeamModel> res = (List<ScheduleTeamModel>)ofy().load().type(ScheduleTeamModel.class).filter("sched_isFinish", isFinish).order("-"+order).list();
		Collections.reverse(res);
		return res;
	}
	public static List<ScheduleTeamModel> getAllTeamSchedulesNotStarted(String order) {
		List<ScheduleTeamModel> res = new ArrayList<ScheduleTeamModel>();
		for(ScheduleTeamModel stm : getAllTeamSchedulesByFinish(order,false)) {
			if(!stm.isSched_isStart())
				res.add(stm);
		}
		return res;
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
	public static List<ResultScoreModel> getAllScoreResults() {
		return ofy().load().type(ResultScoreModel.class).list();
	}
	public static ResultScoreModel getScoreResultsBySchedule(String schedId) {
		Key<ScheduleModel> schedKey = createScheduleKey(schedId);
		return ofy().load().type(ResultScoreModel.class).filter("res_sched_key", schedKey).first().now();
	}
	public static void storeResult(ResultModel res) {
		ofy().save().entity(res).now();
	}
	/*
	 * Paris Methods
	 */
	public static void storeParis(ParisModel mod) {
		ofy().save().entity(mod).now();
	}
	public static void storeNewParis(ParisModel paris) {
		if(!hasParisForUserAndSchedule(paris.getParis_userId(),paris.getParis_schedId())){
			UserModel user = getUser(paris.getParis_userId());
			user.addUserPoint(-paris.getBet());
			storeUser(user);
			storeParis(paris);
		}
	}
	private static List<ParisScoreModel> getAllScoreParis() {
		return ofy().load().type(ParisScoreModel.class).list();
	}
	private static List<ParisVictoryModel> getAllVictoryParis() {
		return ofy().load().type(ParisVictoryModel.class).list();
	}
	public static List<ParisScoreModel> getAllScoreParisBySchedule(String schedId) {
		Key<ScheduleModel> schedKey = createScheduleKey(schedId);
		return ofy().load().type(ParisScoreModel.class).filter("paris_sched_key", schedKey).list();
	}
	public static List<ParisVictoryModel> getAllVictoryParisBySchedule(String schedId) {
		Key<ScheduleModel> schedKey = createScheduleKey(schedId);
		return ofy().load().type(ParisVictoryModel.class).filter("paris_sched_key", schedKey).list();
	}
	public static List<ParisScoreModel> getAllScoreParisByUser(boolean finish) {
		return ofy().load().type(ParisScoreModel.class).filter("finish", finish).list();
	}	
	public static List<ParisVictoryModel> getAllVictoryParisByUser(boolean finish) {
		return ofy().load().type(ParisVictoryModel.class).filter("finish", finish).list();
	}
	public static List<ParisScoreModel> getAllScoreParisByUser(String userId, boolean finish) {
		Key<UserModel> userKey = createUserKey(userId);
		return ofy().load().type(ParisScoreModel.class).filter("paris_user_key", userKey).filter("finish", finish).list();
	}	
	public static List<ParisVictoryModel> getAllVictoryParisByUser(String userId, boolean finish) {
		Key<UserModel> userKey = createUserKey(userId);
		return ofy().load().type(ParisVictoryModel.class).filter("paris_user_key", userKey).filter("finish", finish).list();
	}
	public static boolean hasParisForUserAndSchedule(String userId, String schedId) {
		Key<UserModel> userKey = createUserKey(userId);
		Key<ScheduleModel> schedKey = createScheduleKey(schedId);
		int nbscore = ofy().load().type(ParisScoreModel.class).filter("paris_user_key", userKey).filter("paris_sched_key", schedKey).count();
		if(nbscore==0) nbscore += ofy().load().type(ParisVictoryModel.class).filter("paris_user_key", userKey).filter("paris_sched_key", schedKey).count();
		if(nbscore>0) return true;
		return false;
	}
	public static List<ParisModel> getAllTeamParisByFinish(boolean finish) {
		List<ParisModel> result = new ArrayList<ParisModel>();
		List<ParisVictoryModel> parisVict = getAllVictoryParisByUser(finish);
		for(ParisVictoryModel paris : parisVict) {
			result.add(paris);
		}
		List<ParisScoreModel> parisScor = getAllScoreParisByUser(finish);
		for(ParisScoreModel paris : parisScor) {
			result.add(paris);
		}
		return result;
	}
	public static List<ParisModel> getAllTeamParisByUser(String userId, boolean finish) {
		List<ParisModel> result = new ArrayList<ParisModel>();
		List<ParisVictoryModel> parisVict = getAllVictoryParisByUser(userId,finish);
		for(ParisVictoryModel paris : parisVict) {
			result.add(paris);
		}
		List<ParisScoreModel> parisScor = getAllScoreParisByUser(userId,finish);
		for(ParisScoreModel paris : parisScor) {
			result.add(paris);
		}
		return result;
	}
	public static List<ParisModel> getAllTeamParisBySchedule(String schedId) {
		List<ParisModel> result = new ArrayList<ParisModel>();
		List<ParisVictoryModel> parisVict = getAllVictoryParisBySchedule(schedId);
		for(ParisVictoryModel paris : parisVict) {
			result.add(paris);
		}
		List<ParisScoreModel> parisScor = getAllScoreParisBySchedule(schedId);
		for(ParisScoreModel paris : parisScor) {
			result.add(paris);
		}
		return result;
	}
	/*
	 * Tools Methods
	 */
	public static void updateSchedule(String schedId, ResultScoreModel score) {
		ScheduleModel schedule = DataStore.getSchedule(schedId);
		if(!schedule.isSched_isFinish()) {
			if(schedule instanceof ScheduleTeamModel) {
				List<ParisModel> teamParis = getAllTeamParisBySchedule(schedId);
				List<ParisModel> winnersParis = new ArrayList<ParisModel>();
				List<ParisModel> loosersParis = new ArrayList<ParisModel>();
				int cumulLoosers = 0, cumulWinners = 0, benefitMult = 0;
				
				for(ParisModel paris : teamParis) {
					benefitMult = checkParis(paris, score);
					int bet = paris.getBet();
					if(benefitMult!=0) {
						winnersParis.add(paris);
						cumulWinners += bet;
					} else {
						loosersParis.add(paris);
						cumulLoosers += bet;
					}
				}
				ServerMail mail = new ServerMail();
				for(ParisModel paris : winnersParis) {
					int bet = paris.getBet();
					int winnerBetsPartPercent = bet/cumulWinners;
					int winnerPart = winnerBetsPartPercent*cumulLoosers;
					int res = (benefitMult*(bet + winnerPart));
					paris.addResult(res);
					DataStore.storeParis(paris);
					mail.sendResult(paris);
				}
				
				for(ParisModel paris : loosersParis) {
					paris.addResult(0);
					DataStore.storeParis(paris);
					mail.sendResult(paris);
				}
				
				schedule.setSched_isFinish(true);
				DataStore.storeSchedule(schedule);
			} else {
				System.out.println("ERROR updateSchedule : Type of schedule unknow.");
			}
		}
		else {
			System.out.println("ERROR updateSchedule : Schedule is up to date.");
		}
	}
	
	public static int checkParis(ParisModel paris, ResultScoreModel score) {
		if(paris instanceof ParisVictoryModel) {
			Teams winner = score.getWinner();
			Teams bet = ((ParisVictoryModel)paris).getTeam();
			if (winner==bet)
				return Configs.getMultTeamVict();
		} else if(paris instanceof ParisScoreModel) {
			ParisScoreModel parisScore = ((ParisScoreModel) paris);
			int paris_home = parisScore.getScore_team_home();
			int paris_away = parisScore.getScore_team_away();
			int score_home = score.getScore_res_score_home();
			int score_away = score.getScore_res_score_away();
			if (paris_home == score_home && paris_away == score_away)
				return Configs.getMultTeamScorBoth();
			if (paris_home == score_home || paris_away == score_away)
				return Configs.getMultTeamScorOne();
		} else { // pour debug, à supprimer
			System.out.println("ERROR checkParis !!!");
		}
		return 0;
	}
	/*
	public static void updateSchedule(String schedId, ResultScoreModel score) {
		ScheduleModel schedule = DataStore.getSchedule(schedId);

		if(schedule instanceof ScheduleTeamModel) {
			List<ParisModel> teamParis = getAllTeamParisBySchedule(schedId);
			List<UserModel> winnersParis = new ArrayList<UserModel>();
			Map<String, Integer> winnerBetValues = new HashMap<String, Integer>();
			int cumulLoosers = 0, cumulWinners = 0;
			for(ParisModel paris : teamParis) {
				if(checkParis(paris, score)) {
					UserModel user = paris.getParis_user();
					int bet = paris.getBet();
					winnersParis.add(user);
					winnerBetValues.put(user.getUser_id(), bet);
					cumulWinners += bet;
				} else
					cumulLoosers += paris.getBet();
				
				paris.setFinish(true);
				DataStore.storeParis(paris);
			}

			for(UserModel user : winnersParis) {
				int bet = winnerBetValues.get(user.getUser_id());
				int winnerBetsPartPercent = bet/cumulWinners;
				int winnerPart = winnerBetsPartPercent*cumulLoosers;
				user.addUser_point(bet + winnerPart);
				DataStore.storeUser(user);
			}
			
			schedule.setSched_isFinish(true);
			DataStore.storeSchedule(schedule);
		} else { // pour debug, à supprimer
			System.out.println("ERROR !!!");
		}
	}
	
	public static boolean checkParis(ParisModel paris, ResultScoreModel score) {
		boolean win = false;
		if(paris instanceof ParisVictoryModel) {
			//ResultScoreModel score = ((ScheduleTeamModel)paris.getParis_sched()).getSched_res_score();
			//ResultScoreModel score = DataStore.getScoreResultsBySchedule(scheduleID); // verif si equivalent a au dessus mais normalement oui
			TeamModel vict = score.getWinner();
			if(((ParisVictoryModel)paris).getParis_team()==vict) {
				win = true;
				paris.getParis_user().addUser_point(paris.getBet()*Configs.getMultTeamVict());
			}
		} else if(paris instanceof ParisScoreModel) {
			//ResultScoreModel score = ((ScheduleTeamModel)paris.getParis_sched()).getSched_res_score();
			//ResultScoreModel score = DataStore.getScoreResultsBySchedule(scheduleID); // verif si equivalent a au dessus mais normalement oui
			int paris_home = ((ParisScoreModel) paris).getScore_team_home();
			int paris_away = ((ParisScoreModel) paris).getScore_team_away();
			if((((ParisScoreModel)paris).getParis_team()==Teams.HOME)
					&& paris_home==score.getScore_res_score_home()) {
				win = true;
				paris.getParis_user().addUser_point(paris.getBet()*Configs.getMultTeamScorOne());
			} else if((((ParisScoreModel)paris).getParis_team()==Teams.AWAY)
					&& paris_away==score.getScore_res_score_away()) {
				win = true;
				paris.getParis_user().addUser_point(paris.getBet()*Configs.getMultTeamScorOne());
			} else if((((ParisScoreModel)paris).getParis_team()==Teams.ALL)
					&& paris_home==score.getScore_res_score_home()
					&& paris_away==score.getScore_res_score_away())
				win = true;
			paris.getParis_user().addUser_point(paris.getBet()*Configs.getMultTeamScorAll());
		} else { // pour debug, à supprimer
			System.out.println("ERROR !!!");
		}
		return win;
	}
	*/
}
