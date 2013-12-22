package upmc.aar2013.project.heraclessport.server.datamodel;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.*;

@Entity
public abstract class ResultModel {
	@Id Long res_id;
	@Index Key<ScheduleModel> res_sched_key;
	@Index RES_TEAM res_team;
	
	@Ignore ScheduleModel res_sched;
	
	public enum RES_TEAM{ALL,HOME,AWAY,TOGETHER};
	
	protected ResultModel() {}
	
	/**
	 * @param res_sched
	 */
	public ResultModel(String res_sched, RES_TEAM res_team) {
		this.res_sched_key = DataStore.createScheduleKey(res_sched);
		this.res_team = res_team;
	}	
	@OnLoad 
	public void onLoad() {
		ScheduleModel sched = DataStore.getSchedule(getRes_schedId());
		if(sched!=null) this.res_sched = sched;	
	}
	/**
	 * @return the res_id
	 */
	public Long getRes_id() {
		return res_id;
	}

	/**
	 * @param res_id the res_id to set
	 */
	public void setRes_id(Long res_id) {
		this.res_id = res_id;
	}

	/**
	 * @return the res_team
	 */
	public RES_TEAM getRes_team() {
		return res_team;
	}

	/**
	 * @param res_team the res_team to set
	 */
	public void setRes_team(RES_TEAM res_team) {
		this.res_team = res_team;
	}
	
	/**
	 * @return the res_sched
	 */
	public String getRes_schedId() {
		return res_sched_key.getRaw().getName();
	}

	/**
	 * @return the res_sched
	 */
	public ScheduleModel getRes_sched() {
		return res_sched;
	}
	
}
