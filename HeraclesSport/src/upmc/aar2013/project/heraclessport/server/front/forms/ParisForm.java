package upmc.aar2013.project.heraclessport.server.front.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import upmc.aar2013.project.heraclessport.server.configs.Teams;
import upmc.aar2013.project.heraclessport.server.datamodel.api.DataStore;
import upmc.aar2013.project.heraclessport.server.datamodel.paris.ParisScoreModel;
import upmc.aar2013.project.heraclessport.server.datamodel.paris.ParisVictoryModel;
import upmc.aar2013.project.heraclessport.server.datamodel.schedules.ScheduleModel;
import upmc.aar2013.project.heraclessport.server.datamodel.users.UserModel;

public class ParisForm extends GeneralForm {
	private static final String FIELD_SHED_ID  = "sched_id";
	private static final String FIELD_PARIS_TYPE  = "paris_type";
	private static final String FIELD_PARIS_BET  = "paris_bet";
	private static final String FIELD_PARIS_VICT  = "paris_vict";
	private static final String FIELD_PARIS_SCORE_EQP  = "paris_scor_eqp";
	private static final String FIELD_PARIS_SCORE_TEAM_HOME  = "paris_scor_teamhome";
	private static final String FIELD_PARIS_SCORE_TEAM_AWAY  = "paris_scor_teamaway";
	
    public void createParis(HttpServletRequest request, String userId) {
		String sched_id = request.getParameter(FIELD_SHED_ID);
		String paris_type = request.getParameter(FIELD_PARIS_TYPE);
		String paris_vict = request.getParameter(FIELD_PARIS_VICT);
		String paris_scor_eqp = request.getParameter(FIELD_PARIS_SCORE_EQP);
		String paris_scor_teamhome = getFieldValue(request, FIELD_PARIS_SCORE_TEAM_HOME);
		String paris_scor_teamaway = getFieldValue(request, FIELD_PARIS_SCORE_TEAM_AWAY);
		String paris_bet = request.getParameter(FIELD_PARIS_BET);
		boolean noError = true;
		try {
			checkExistingSchedule(sched_id);
			int paris_bet_int = checkInteger(paris_bet);
			checkUserCanParis(userId, sched_id,paris_bet_int);
			if (paris_type.equals("vict")) {
				Teams team = Teams.ALL;
				if (paris_vict.equals("teamhome")) team = Teams.HOME;
				if (paris_vict.equals("teamaway")) team = Teams.AWAY;
				ParisVictoryModel parisVictoryModel = new ParisVictoryModel(userId, sched_id, paris_bet_int, team);
				DataStore.storeParis(parisVictoryModel);
				setResult("Paris sur la victoire effectué.");
			}
			else if (paris_type.equals("scor")) {
				Teams team = Teams.ALL;
				if (paris_scor_eqp.equals("home")) team = Teams.HOME;
				if (paris_scor_eqp.equals("away")) team = Teams.AWAY;
				try {
					int paris_scor_teamhome_int = checkInteger(paris_scor_teamhome);
					int paris_scor_teamaway_int = checkInteger(paris_scor_teamaway);
					ParisScoreModel parisScoreModel = new ParisScoreModel(userId, sched_id, paris_bet_int, team);
					parisScoreModel.setScore_team_home(paris_scor_teamhome_int);
					parisScoreModel.setScore_team_away(paris_scor_teamaway_int);
					DataStore.storeParis(parisScoreModel);
					setResult("Paris sur les scores effectué.");
				} catch (NumberFormatException e) {} // Integer.parseInt
			}  
			else {
				throw new Exception("Type de paris non permis.");
			}
		} catch(Exception e) {
        	noError = false;
        	setError("general",e.getMessage());
        	setResult(e.getMessage());
        }
    }
    
    private int checkInteger(String integer) throws Exception {
    	try {
    		int res = Integer.parseInt(integer);
    		if(res<0)
    			throw new Exception("Valeur négative interdite");
    		return res;
    	} catch (NumberFormatException e) {
    		throw new Exception("Champ numerique non valide");
    	} // Integer.parseInt
	}

	private void checkExistingSchedule(String sched_id) throws Exception {
    	ScheduleModel sched = DataStore.getSchedule(sched_id);
		if(sched==null)
			throw new Exception("Cette rencontre n'existe pas.");
		else
			if(sched.isSched_isStart())
				throw new Exception("Cette rencontre a déjà commencé.");
		
	}

	public void checkUserCanParis(String userId, String schedId, int paris_bet) throws Exception {
    	if(DataStore.hasParisForUserAndSchedule(userId, schedId))
    		throw new Exception("Vous avez déjà parié sur cette rencontre.");
    	UserModel user = DataStore.getUser(userId);
    	if(paris_bet<=0)
    		throw new Exception("Vous devez au moins miser 1 point");
    	if(paris_bet>user.getUser_point())
    		throw new Exception("Vous ne pouvez miser "+paris_bet+" s'il vous reste que "+user.getUser_point()+" !");
    }
}
