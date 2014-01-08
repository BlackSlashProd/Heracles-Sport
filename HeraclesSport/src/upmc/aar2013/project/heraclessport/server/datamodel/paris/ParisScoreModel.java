package upmc.aar2013.project.heraclessport.server.datamodel.paris;

import upmc.aar2013.project.heraclessport.server.configs.Teams;

import com.googlecode.objectify.annotation.*;

@Entity
public class ParisScoreModel extends ParisModel {
	@Index Teams paris_team;
	int score_team_home;
	int score_team_away;
	
	private ParisScoreModel() {
		super();
	}
	
	/**
	 * @param paris_user
	 * @param paris_sched
	 * @param bet
	 */
	public ParisScoreModel(String paris_user, String paris_sched, int bet, Teams paris_score_team) {
		super(paris_user, paris_sched, bet);
		score_team_home = -1;
		score_team_away = -1;
		this.paris_team = paris_score_team;
	}

	/**
	 * @return the paris_team
	 */
	public Teams getParis_team() {
		return paris_team;
	}

	/**
	 * @param score_team_home the score_team_home to set
	 */
	public void setScore_team_home(int score_team_home) {
		this.score_team_home = score_team_home;
	}

	/**
	 * @param score_team_away the score_team_away to set
	 */
	public void setScore_team_away(int score_team_away) {
		this.score_team_away = score_team_away;
	}

	/**
	 * @return the score_team_home
	 */
	public int getScore_team_home() {
		return score_team_home;
	}

	/**
	 * @return the score_team_away
	 */
	public int getScore_team_away() {
		return score_team_away;
	}
}
