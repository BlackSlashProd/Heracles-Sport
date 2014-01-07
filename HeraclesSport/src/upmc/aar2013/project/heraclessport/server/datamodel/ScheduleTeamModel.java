package upmc.aar2013.project.heraclessport.server.datamodel;

import java.util.Date;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.*;

@Entity
public class ScheduleTeamModel extends ScheduleModel {
	Key<TeamModel> sched_home_team_id;
	Key<TeamModel> sched_away_team_id;
	@Ignore TeamModel sched_home_team;
	@Ignore TeamModel sched_away_team;
	
	private ScheduleTeamModel() {}
	
	public ScheduleTeamModel(String sched_sport, String sched_id, Date sched_date, String home_team_id,
			String away_team_id) {
		super(sched_sport, sched_id, sched_date);
		this.sched_home_team_id = DataStore.createTeamKey(home_team_id);
		this.sched_away_team_id = DataStore.createTeamKey(away_team_id);
		this.sched_home_team = null;
		this.sched_away_team = null;
	}
	
	@OnLoad
	public void onLoad() {
		TeamModel teamHome = DataStore.getTeam(getSched_home_team_id());
		if(teamHome!=null) this.sched_home_team = teamHome;
		TeamModel teamAway = DataStore.getTeam(getSched_away_team_id());
		if(teamAway!=null) this.sched_away_team = teamAway;
	}
	
	/**
	 * @return the sched_home_team_id
	 */
	public String getSched_home_team_id() {
		return sched_home_team_id.getRaw().getName();
	}

	/**
	 * @return the sched_away_team_id
	 */
	public String getSched_away_team_id() {
		return sched_away_team_id.getRaw().getName();
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
