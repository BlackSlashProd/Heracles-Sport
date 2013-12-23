package upmc.aar2013.project.heraclessport.server.datamodel;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.*;

@Entity
public class ParisModel {
	@Id Long paris_id;
	@Index Key<UserModel> paris_user;
	@Index Key<ResultModel> paris_result;
	int bet;
	
	@SuppressWarnings("unused")
	private ParisModel() {}

	/**
	 * @param paris_user
	 * @param bet
	 */
	public ParisModel(Key<UserModel> paris_user, Key<ResultModel> paris_result, int bet) {
		super();
		this.paris_user = paris_user;
		this.paris_result = paris_result;
		this.bet = bet;
	}
	
	/**
	 * @return the paris_result
	 */
	public Key<ResultModel> getParis_result() {
		return paris_result;
	}

	/**
	 * @param paris_result the paris_result to set
	 */
	public void setParis_result(Key<ResultModel> paris_result) {
		this.paris_result = paris_result;
	}
	/**
	 * @return the paris_user
	 */
	public Key<UserModel> getParis_user() {
		return paris_user;
	}

	/**
	 * @param paris_user the paris_user to set
	 */
	public void setParis_user(Key<UserModel> paris_user) {
		this.paris_user = paris_user;
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

	/**
	 * @return the paris_id
	 */
	public Long getParis_id() {
		return paris_id;
	}

	
}
