package upmc.aar2013.project.heraclessport.server.datamodel.schedules;

import com.googlecode.objectify.annotation.*;

@Entity
public class TeamModel {
	@Id String team_id;
	@Index String team_name;
	String team_town;
	String team_country;
	
	public TeamModel() {}
	
	/**
	 * @param team_id
	 * @param team_name
	 * @param team_town
	 * @param team_country
	 */
	public TeamModel(String team_id, String team_name, String team_town,
			String team_country) {
		this.team_id = team_id;
		this.team_name = team_name;
		this.team_town = team_town;
		this.team_country = team_country;
	}

	/**
	 * @return the team_id
	 */
	public String getTeam_id() {
		return team_id;
	}

	/**
	 * @param team_id the team_id to set
	 */
	public void setTeam_id(String team_id) {
		this.team_id = team_id;
	}

	/**
	 * @return the team_name
	 */
	public String getTeam_name() {
		return team_name;
	}

	/**
	 * @param team_name the team_name to set
	 */
	public void setTeam_name(String team_name) {
		this.team_name = team_name;
	}

	/**
	 * @return the team_town
	 */
	public String getTeam_town() {
		return team_town;
	}

	/**
	 * @param team_town the team_town to set
	 */
	public void setTeam_town(String team_town) {
		this.team_town = team_town;
	}

	/**
	 * @return the team_country
	 */
	public String getTeam_country() {
		return team_country;
	}

	/**
	 * @param team_country the team_country to set
	 */
	public void setTeam_country(String team_country) {
		this.team_country = team_country;
	}
}
