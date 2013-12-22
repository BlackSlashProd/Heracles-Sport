package upmc.aar2013.project.heraclessport.server.datamodel;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.*;

@Entity
public class ParisModel {
	@Id Long paris_id;
	@Index Key<UserModel> paris_user_key;
	@Index Key<ResultModel> paris_result_key;
	int bet;
	
	@Ignore UserModel paris_user;
	@Ignore ResultModel paris_result;
	
	private ParisModel() {}

	/**
	 * @param paris_user
	 * @param bet
	 */
	public ParisModel(String paris_user, Long paris_result, int bet) {
		this.paris_user_key = DataStore.createUserKey(paris_user);
		this.paris_result_key = DataStore.createResultKey(paris_result);
		this.bet = bet;
	}
	
	@OnLoad 
	public void onLoad() {
		UserModel user = DataStore.getUser(getParis_user());
		if(user!=null) this.paris_user = user;
		ResultModel result = DataStore.getResult(getParis_result());
		if(result!=null) this.paris_result = result;
	}
	
	/**
	 * @return the paris_id
	 */
	public Long getParis_id() {
		return paris_id;
	}
	
	/**
	 * @return the paris_result
	 */
	public Long getParis_result() {
		return paris_result_key.getRaw().getId();
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
