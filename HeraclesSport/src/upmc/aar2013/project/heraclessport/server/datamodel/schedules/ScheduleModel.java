package upmc.aar2013.project.heraclessport.server.datamodel.schedules;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import upmc.aar2013.project.heraclessport.server.configs.Sport;
import upmc.aar2013.project.heraclessport.server.datamodel.api.DataStore;

import com.googlecode.objectify.annotation.*;

@Entity
public abstract class ScheduleModel {
	@Id String sched_id;
	@Index String sched_sport; 

	@Index Date sched_date;
	@Index boolean sched_isFinish;
	@Ignore boolean sched_isStart;
	
	protected ScheduleModel() {}
	
	/**
	 * @param sched_id
	 * @param sched_date
	 */
	public ScheduleModel(String sched_id, String sched_sport, Date sched_date) {
		this.sched_id = sched_id;
		this.sched_sport = sched_sport;
		this.sched_date = sched_date;
		if(sched_date.before(new Date()))
			this.sched_isStart = true;		
	}
	
	@OnLoad 
	public void onLoad() {
		/*DataStore.getScoreResultsBySchedule(sched_id);
		List<ResultScoreModel> results = (List<ResultScoreModel>)DataStore.getScoreResultsBySchedule(sched_id);
		this.sched_res = new ArrayList<ResultModel>();
		if(results!=null) {
			for(ResultScoreModel res : results) {
				this.sched_res.add(res);
			}
		}*/
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
	 * @return the sched_sport
	 */
	public String getSched_sport() {
		return sched_sport;
	}
	
	public String getSched_sportName() {
		return Sport.getClean(sched_sport);
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
