package upmc.aar2013.project.heraclessport.server.datamodel.paris;

import upmc.aar2013.project.heraclessport.server.configs.Teams;
import upmc.aar2013.project.heraclessport.server.datamodel.api.DataStore;
import upmc.aar2013.project.heraclessport.server.datamodel.schedules.ScheduleTeamModel;
import upmc.aar2013.project.heraclessport.server.datamodel.schedules.TeamModel;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.*;

@Entity
public class ParisVictoryModel extends ParisModel {

	@Index Teams paris_team_select;
	
	@Ignore TeamModel paris_team;
	
	@SuppressWarnings("unused")
	private ParisVictoryModel() {
		super();
	}
	
	public ParisVictoryModel(String paris_user, String paris_sched, int bet, Teams paris_vict_team) {
		super(paris_user, paris_sched, bet);
		this.paris_team_select = paris_vict_team;
	}
	
	@OnLoad 
	public void onLoad() {
		super.onLoad();
		TeamModel team = null;
		if(getParis_sched()!=null) {
			if(paris_team_select == Teams.HOME)
				team = DataStore.getTeam(((ScheduleTeamModel)getParis_sched()).getSched_home_team_id());
			else if(paris_team_select == Teams.AWAY)
				team = DataStore.getTeam(((ScheduleTeamModel)getParis_sched()).getSched_away_team_id());
		}
		if(team!=null) this.paris_team = team;
	}

	/**
	 * @return the paris_team_select
	 */
	public Teams getTeam() {
		return paris_team_select;
	}
	
	/**
	 * @return the paris_team
	 */
	public TeamModel getParis_team() {
		return paris_team;
	}

}
