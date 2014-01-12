package upmc.aar2013.project.heraclessport.server.datamodel.schedules;

import java.util.Date;

import upmc.aar2013.project.heraclessport.server.configs.Sport;
import upmc.aar2013.project.heraclessport.server.datamodel.api.DataStore;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.*;

/**
 * Objet persistant dans le DataStore.
 * ScheduleTeamModel stocke les informations d'une rencontre par équipe.
 * ScheduleTeamModel constitue un type de rencontre et implémente ScheduleModel.
 * Au chargement ScheduleTeamModel instancie les deux équipes concernées 
 * par le paris ainsi que le résultat (scores) si il est connu.
 */
@Entity
public class ScheduleTeamModel extends ScheduleModel {
	Key<TeamModel> sched_home_team_id;			// Equipe Domicile Clé
	Key<TeamModel> sched_away_team_id;			// Equipe Exterieur Clé
	@Ignore TeamModel sched_home_team;			// Equipe Domicile
	@Ignore TeamModel sched_away_team;			// Equipe Exterieur
	@Ignore ResultScoreModel sched_res_score;	// Résultat de la rencontre (si connu)
	
	@SuppressWarnings("unused")
	private ScheduleTeamModel() {}
	
	public ScheduleTeamModel(Sport sched_sport, String sched_id, Date sched_date, boolean sched_isFinish,
			String home_team_id, String away_team_id) {
		super(sched_sport, sched_id, sched_date, sched_isFinish);
		this.sched_home_team_id = DataStore.createTeamKey(home_team_id);
		this.sched_away_team_id = DataStore.createTeamKey(away_team_id);
		this.sched_home_team = null;
		this.sched_away_team = null;
	}
	
	@OnLoad
	public void onLoad() {
		super.onLoad();
		TeamModel teamHome = DataStore.getTeam(getSched_home_team_id());
		if(teamHome!=null) this.sched_home_team = teamHome;
		TeamModel teamAway = DataStore.getTeam(getSched_away_team_id());
		if(teamAway!=null) this.sched_away_team = teamAway;
		ResultScoreModel resultScor = DataStore.getScoreResultsBySchedule(sched_id);
		if(resultScor!=null) this.sched_res_score = resultScor; 
	}
	
	/**
	 * @return the sched_res_score
	 */
	public ResultScoreModel getSched_res_score() {
		return sched_res_score;
	}

	/**
	 * @return the sched_home_team_id
	 */
	public String getSched_home_team_id() {
		return sched_home_team_id.getRaw().getName();
	}

	/**
	 * @param sched_home_team_id the sched_home_team_id to set
	 */
	public void setSched_home_team_id(String sched_home_team_id) {
		this.sched_home_team_id = DataStore.createTeamKey(sched_home_team_id);
	}
	
	/**
	 * @return the sched_away_team_id
	 */
	public String getSched_away_team_id() {
		return sched_away_team_id.getRaw().getName();
	}
	
	/**
	 * @param sched_away_team_id the sched_away_team_id to set
	 */
	public void setSched_away_team_id(String sched_away_team_id) {
		this.sched_away_team_id = DataStore.createTeamKey(sched_away_team_id);
	}
	
	/**
	 * @return the sched_home_team
	 */
	public TeamModel getSched_home_team() {
		return sched_home_team;
	}

	/**
	 * @return the sched_away_team
	 */
	public TeamModel getSched_away_team() {
		return sched_away_team;
	}
}
