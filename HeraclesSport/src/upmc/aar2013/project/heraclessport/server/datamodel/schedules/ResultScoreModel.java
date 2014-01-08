package upmc.aar2013.project.heraclessport.server.datamodel.schedules;

import com.googlecode.objectify.annotation.*;

@Entity
public class ResultScoreModel extends ResultModel {
	int score_res_score_home;
	int score_res_score_away;
	
	public ResultScoreModel() {}
	
	public ResultScoreModel(String res_sched, int score_home, int score_away) {
		super(res_sched);
		this.score_res_score_home = score_home;
		this.score_res_score_away = score_away;
	}
	
	/**
	 * @return the score_res_score_home
	 */
	public int getScore_res_score_home() {
		return score_res_score_home;
	}

	/**
	 * @param score_res_score_home the score_res_score_home to set
	 */
	public void setScore_res_score_home(int score_res_score_home) {
		this.score_res_score_home = score_res_score_home;
	}

	/**
	 * @return the score_res_score_away
	 */
	public int getScore_res_score_away() {
		return score_res_score_away;
	}

	/**
	 * @param score_res_score_away the score_res_score_away to set
	 */
	public void setScore_res_score_away(int score_res_score_away) {
		this.score_res_score_away = score_res_score_away;
	}
	
	public TeamModel getWinner() {
		if(this.getScore_res_score_home()>this.getScore_res_score_away()) 
			return ((ScheduleTeamModel)getRes_sched()).getSched_home_team();
		else if(this.getScore_res_score_home()<this.getScore_res_score_away())
			return ((ScheduleTeamModel)getRes_sched()).getSched_home_team();
		return null;
	}
}
