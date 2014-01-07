package upmc.aar2013.project.heraclessport.server.datamodel;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.*;

@Entity
public abstract class ParisModel {
	@Id Long paris_id;
	@Index Key<UserModel> paris_user_key;
	@Index Key<ScheduleModel> paris_sched_key;
	@Index boolean finish;
	int bet;
	
	@Ignore UserModel paris_user;
	@Ignore ScheduleModel paris_sched;
	
	@SuppressWarnings("unused")
	private ParisModel() {}

	/**
	 * @param paris_user
	 * @param bet
	 */
	public ParisModel(String paris_user, String paris_sched, int bet) {
		this.paris_user_key = DataStore.createUserKey(paris_user);
		this.paris_sched_key = DataStore.createScheduleKey(paris_sched);
		this.bet = bet;
		this.finish = false;
	}
	
	@OnLoad 
	public void onLoad() {
		UserModel user = DataStore.getUser(getParis_user());
		if(user!=null) this.paris_user = user;
		ScheduleModel sched = DataStore.getSchedule(getParis_sched());
		if(sched!=null) this.paris_sched = sched;
	}
	
	/**
	 * @return the finish
	 */
	public boolean isFinish() {
		return finish;
	}

	/**
	 * @param finish the finish to set
	 */
	public void setFinish(boolean finish) {
		this.finish = finish;
	}

	private String getParis_sched() {
		return paris_sched_key.getRaw().getName();
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
	public String getParis_user() {
		return paris_user_key.getRaw().getName();
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
