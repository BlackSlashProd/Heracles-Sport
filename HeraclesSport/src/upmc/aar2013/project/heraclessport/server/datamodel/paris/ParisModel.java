package upmc.aar2013.project.heraclessport.server.datamodel.paris;

import upmc.aar2013.project.heraclessport.server.datamodel.api.DataStore;
import upmc.aar2013.project.heraclessport.server.datamodel.schedules.ScheduleModel;
import upmc.aar2013.project.heraclessport.server.datamodel.users.UserModel;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.*;

@Entity
public abstract class ParisModel {
	@Id Long paris_id;
	@Index Key<UserModel> paris_user_key;
	@Index Key<ScheduleModel> paris_sched_key;
	@Index boolean finish;
	@Index int result;
	int bet;
	
	@Ignore UserModel paris_user;
	@Ignore ScheduleModel paris_sched;
	
	@SuppressWarnings("unused")
	protected ParisModel() {}

	/**
	 * @param paris_user
	 * @param bet
	 */
	public ParisModel(String paris_user, String paris_sched, int bet) {
		this.paris_user_key = DataStore.createUserKey(paris_user);
		this.paris_sched_key = DataStore.createScheduleKey(paris_sched);
		this.bet = bet;
		this.finish = false;
		this.result = 0;
	}
	
	@OnLoad 
	public void onLoad() {
		UserModel user = DataStore.getUser(getParis_userId());
		if(user!=null) this.paris_user = user;
		ScheduleModel sched = DataStore.getSchedule(getParis_schedId());
		if(sched!=null) this.paris_sched = sched;
	}
	
	/**
	 * @return the finish
	 */
	public boolean isFinish() {
		return finish;
	}

	/**
	 * @return the result
	 */
	public int getResult() {
		return result;
	}

	/**
	 * @param iswin the iswin to set
	 * @throws Exception 
	 */
	public void addResult(int res) {
		if(!isFinish()) {
			this.result -= this.bet;
			if(res>0) this.result = res;
			UserModel user = DataStore.getUser(getParis_userId());
			if(res>0) user.addUserPoint(res+this.bet);
			user.addUserPointsResult(this.result);
			DataStore.storeUser(user);
			setFinish(true);
		}
	}

	/**
	 * @param finish the finish to set
	 */
	private void setFinish(boolean finish) {
		this.finish = finish;
	}

	public String getParis_schedId() {
		return paris_sched_key.getRaw().getName();
	}

	/**
	 * @return the paris_sched
	 */
	public ScheduleModel getParis_sched() {
		return paris_sched;
	}

	/**
	 * @param paris_sched the paris_sched to set
	 */
	public void setParis_sched(ScheduleModel paris_sched) {
		this.paris_sched = paris_sched;
	}

	/**
	 * @return the paris_id
	 */
	public Long getParis_id() {
		return paris_id;
	}

	/**
	 * @return the paris_user
	 */
	public String getParis_userId() {
		return paris_user_key.getRaw().getName();
	}
	/**
	 * @return the paris_user
	 */
	public UserModel getParis_user() {
		return paris_user;
	}

	/**
	 * @return the bet
	 */
	public int getBet() {
		return bet;
	}

	/**
	 * @param bet the bet to set
	 */
	public void setBet(int bet) {
		this.bet = bet;
	}
}
