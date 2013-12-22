package upmc.aar2013.project.heraclessport.server.datamodel;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.*;

@Entity
public abstract class ResultModel {
	@Id Long res_id;
	Key<ScheduleModel> res_sched;
	public enum SCORE_TEAM{ALL,HOME,AWAY};

	protected ResultModel() {}
	
	/**
	 * @param res_sched
	 */
	public ResultModel(Key<ScheduleModel> res_sched) {
		this.res_sched = res_sched;
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
	 * @return the res_sched
	 */
	public Key<ScheduleModel> getRes_sched() {
		return res_sched;
	}

	/**
	 * @param res_sched the res_sched to set
	 */
	public void setRes_sched(Key<ScheduleModel> res_sched) {
		this.res_sched = res_sched;
	}
	
}
