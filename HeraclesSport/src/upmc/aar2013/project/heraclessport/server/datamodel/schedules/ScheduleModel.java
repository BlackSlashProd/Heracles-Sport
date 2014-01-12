package upmc.aar2013.project.heraclessport.server.datamodel.schedules;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import upmc.aar2013.project.heraclessport.server.configs.Sport;
import upmc.aar2013.project.heraclessport.server.datamodel.api.DataStore;
import upmc.aar2013.project.heraclessport.server.datamodel.paris.ParisModel;

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
	public ScheduleModel(Sport sched_sport, String sched_id, Date sched_date, boolean sched_isFinish) {
		this.sched_id = sched_id;
		this.sched_sport = sched_sport.getName();
		this.sched_date = sched_date;
		this.sched_isFinish = sched_isFinish;
		this.sched_isStart = false;
	}
	
	@OnLoad 
	public void onLoad() {
		if(sched_date.before(new Date()))
			this.sched_isStart = true;
	}
	
	public int getTotalBets() {
		List<ParisModel> paris = DataStore.getAllTeamParisBySchedule(this.sched_id);
		int total = 0;
		for(ParisModel par : paris){
			total += par.getBet();
		}
		return total;
	}
	
	public String computeTimeLeft() {
		long diff = Math.abs(sched_date.getTime()-(new Date()).getTime());
		String res = "";
		res += (diff/(1000*60*60*24))+" <b>J</b> ";
		diff = diff%(1000*60*60*24);
		res += (diff/(1000*60*60))+" <b>H</b> ";
		diff = diff%(1000*60*60);
		res += (diff/(1000*60))+" <b>M</b>.";
		return res;
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
	
	/**
	 * @return the real sched_sport name
	 */
	public String getSched_sportName() {
		return Sport.getClean(sched_sport);
	}
	
	/**
	 * @return the sched_date
	 */
	public Date getSched_date() {
		return sched_date;
	}
	
	public String getSched_dateClean() {
		DateFormat dfl = DateFormat.getDateInstance(DateFormat.FULL);
        return dfl.format(getSched_date());
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
