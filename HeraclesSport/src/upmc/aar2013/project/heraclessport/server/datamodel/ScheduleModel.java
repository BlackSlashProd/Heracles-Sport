package upmc.aar2013.project.heraclessport.server.datamodel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.*;

@Entity
public class ScheduleModel {
	@Id String sched_id;
	Key<TeamModel> sched_home_team_id;
	Key<TeamModel> sched_away_team_id;
	@Index Date sched_date;
	@Index boolean sched_isFinish;
	
	@Ignore boolean sched_isStart;
	@Ignore TeamModel sched_home_team;
	@Ignore TeamModel sched_away_team;
	@Ignore List<ResultModel> sched_res;
	
	public ScheduleModel() {}
	
	/**
	 * @param sched_id
	 * @param sched_home_team_id
	 * @param sched_away_team_id
	 * @param sched_date
	 */
	public ScheduleModel(String sched_id, String home_team_id,
			String away_team_id, Date sched_date) {
		this.sched_id = sched_id;
		this.sched_home_team_id = DataStore.createTeamKey(home_team_id);
		this.sched_away_team_id = DataStore.createTeamKey(away_team_id);
		this.sched_date = sched_date;
		
		if(sched_date.before(new Date()))
			this.sched_isStart = true;
		this.sched_home_team = null;
		this.sched_away_team = null;		
	}
	
	@OnLoad 
	public void onLoad() {
		TeamModel teamHome = DataStore.getTeam(getSched_home_team_id());
		if(teamHome!=null) this.sched_home_team = teamHome;
		TeamModel teamAway = DataStore.getTeam(getSched_away_team_id());
		if(teamAway!=null) this.sched_away_team = teamAway;	
		List<ScoreResultModel> results = (List<ScoreResultModel>)DataStore.getScoreResultsBySchedule(sched_id);
		this.sched_res = new ArrayList<ResultModel>();
		if(results!=null) {
			for(ScoreResultModel res : results) {
				this.sched_res.add(res);
			}
		}
	}
	
	public Date computeTimeLeft() {
		if(sched_date.after(new Date())) {
			long diff = sched_date.getTime() - (new Date()).getTime();
			return new Date(diff);
		}
		return null;
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

	/**
	 * @return the sched_res
	 */
	public List<ResultModel> getSched_res() {
		return sched_res;
	}
}
