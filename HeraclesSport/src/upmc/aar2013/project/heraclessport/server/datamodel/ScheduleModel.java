package upmc.aar2013.project.heraclessport.server.datamodel;

import java.util.Date;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.*;

@Entity
public class ScheduleModel {
	/**
	 * @param sched_isStart the sched_isStart to set
	 */
	public void setSched_isStart(boolean sched_isStart) {
		this.sched_isStart = sched_isStart;
	}

	@Id String sched_id;
	Key<TeamModel> sched_home_team_id;
	Key<TeamModel> sched_away_team_id;
	@Index Date sched_date;
	@Index boolean sched_isFinish;
	@Ignore boolean sched_isStart;
	@Ignore String sched_home_team_name;
	@Ignore String sched_away_team_name;
	
	@SuppressWarnings("unused")
	private ScheduleModel() {}
	
	/**
	 * @param sched_id
	 * @param sched_home_team_id
	 * @param sched_away_team_id
	 * @param sched_date
	 */
	public ScheduleModel(String sched_id, Key<TeamModel> sched_home_team_id,
			Key<TeamModel> sched_away_team_id, Date sched_date) {
		this.sched_id = sched_id;
		this.sched_home_team_id = sched_home_team_id;
		this.sched_away_team_id = sched_away_team_id;
		this.sched_date = sched_date;
		if(sched_date.before(new Date()))
			this.sched_isStart = true;
		this.sched_home_team_name = sched_id+"teamhome";
		this.sched_away_team_name = sched_id+"teamaway";
	}
	
	@OnLoad 
	public void onLoad() {
		TeamModel teamHome = DataStore.getTeam(sched_home_team_id);
		if(teamHome!=null) this.sched_home_team_name = teamHome.getTeam_name();
		TeamModel teamAway = DataStore.getTeam(sched_away_team_id);
		if(teamAway!=null) this.sched_away_team_name = teamAway.getTeam_name();		
	}
	
	public Date computeTimeLeft() {
		if(sched_date.after(new Date())) {
			long diff = sched_date.getTime() - (new Date()).getTime();
			return new Date(diff);
		}
		return null;
	}
	/**
	 * @return the sched_home_team_name
	 */
	public String getSchedTeamHome() {
		return sched_home_team_name;
	}

	/**
	 * @param sched_home_team_name the sched_home_team_name to set
	 */
	public void setSchedTeamHome(String sched_home_team_name) {
		this.sched_home_team_name = sched_home_team_name;
	}

	/**
	 * @return the sched_away_team_name
	 */
	public String getSchedTeamAway() {
		return sched_away_team_name;
	}

	/**
	 * @param sched_away_team_name the sched_away_team_name to set
	 */
	public void setSchedTeamAway(String sched_away_team_name) {
		this.sched_away_team_name = sched_away_team_name;
	}	
	/**
	 * @return the sched_id
	 */
	public String getSched_id() {
		return sched_id;
	}

	/**
	 * @param sched_id the sched_id to set
	 */
	public void setSched_id(String sched_id) {
		this.sched_id = sched_id;
	}

	/**
	 * @return the sched_home_team_id
	 */
	public Key<TeamModel> getSched_home_team_id() {
		return sched_home_team_id;
	}

	/**
	 * @param sched_home_team_id the sched_home_team_id to set
	 */
	public void setSched_home_team_id(Key<TeamModel> sched_home_team_id) {
		this.sched_home_team_id = sched_home_team_id;
	}

	/**
	 * @return the sched_away_team_id
	 */
	public Key<TeamModel> getSched_away_team_id() {
		return sched_away_team_id;
	}

	/**
	 * @param sched_away_team_id the sched_away_team_id to set
	 */
	public void setSched_away_team_id(Key<TeamModel> sched_away_team_id) {
		this.sched_away_team_id = sched_away_team_id;
	}

	/**
	 * @return the sched_date
	 */
	public Date getSched_date() {
		return sched_date;
	}

	/**
	 * @param sched_date the sched_date to set
	 */
	public void setSched_date(Date sched_date) {
		this.sched_date = sched_date;
	}

	/**
	 * @return the sched_isFinish
	 */
	public boolean isSched_isFinish() {
		return sched_isFinish;
	}

	/**
	 * @param sched_isFinish the sched_isFinish to set
	 */
	public void setSched_isFinish(boolean sched_isFinish) {
		this.sched_isFinish = sched_isFinish;
	}

	/**
	 * @return the sched_isStart
	 */
	public boolean isSched_isStart() {
		return sched_isStart;
	}
}
