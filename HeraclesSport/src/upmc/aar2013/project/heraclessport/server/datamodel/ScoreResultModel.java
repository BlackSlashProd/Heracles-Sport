package upmc.aar2013.project.heraclessport.server.datamodel;

import com.googlecode.objectify.annotation.*;

@Entity
public class ScoreResultModel extends ResultModel {
	int score_res_score_home;
	int score_res_score_away;
	
	public ScoreResultModel() {}
	
	public ScoreResultModel(String res_sched, RES_TEAM res_team) {
		super(res_sched, res_team);
		this.score_res_score_home = -1;
		this.score_res_score_away = -1;
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
}
