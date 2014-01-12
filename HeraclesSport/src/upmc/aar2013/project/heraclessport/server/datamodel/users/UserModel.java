package upmc.aar2013.project.heraclessport.server.datamodel.users;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import upmc.aar2013.project.heraclessport.server.configs.Configs;
import upmc.aar2013.project.heraclessport.server.datamodel.api.DataStore;
import upmc.aar2013.project.heraclessport.server.datamodel.paris.ParisModel;

import com.googlecode.objectify.annotation.*;

@Entity
public class UserModel {
	@Id String user_id;
    String user_name;
    @Index String user_pseudo;
    String user_mail;
    Date user_creation;
    @Index int user_point;
    @Index int user_point_win;
	@Index int user_point_lost;
    
	@SuppressWarnings("unused")
	private UserModel() {}
    
    /**
	 * @param user_id
	 * @param user_name
	 * @param user_mail
	 */
	public UserModel(String user_id, String user_name, String user_mail) {
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_mail = user_mail;
		this.user_pseudo = user_name;
    	this.user_creation = new Date();
    	this.user_point = Configs.getStartPoint();	
    	this.user_point_win = 0;
    	this.user_point_lost = 0;
	}
	
	@OnLoad
	public void onLoad() {

	}
	
	/**
	 * @return the user_name
	 */
	public String getUser_name() {
		return user_name;
	}
	/**
	 * @param user_name the user_name to set
	 */
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
    /**
	 * @return the user_mail
	 */
	public String getUser_mail() {
		return user_mail;
	}
	/**
	 * @param user_mail the user_mail to set
	 */
	public void setUser_mail(String user_mail) {
		this.user_mail = user_mail;
	}
	/**
	 * @return the user_pseudo
	 */
	public String getUser_pseudo() {
		return user_pseudo;
	}
	/**
	 * @param user_pseudo the user_pseudo to set
	 */
	public void setUser_pseudo(String user_pseudo) {
		this.user_pseudo = user_pseudo;
	}
	/**
	 * @return the user_creation
	 */
	private Date getUser_creation() {
		return user_creation;
	}
	private int getUser_numberOfDay() {
		long diff = getUser_creation().getTime()-(new Date()).getTime();
		return (int)(diff/(1000*60*60*24));
	}
	public float getUser_dailyRatio() {
		float ratio = (float)getUser_point_win()-(float)getUser_point_lost();
		int nbdays = getUser_numberOfDay();
		if(nbdays>0) ratio /= (float)nbdays;
		return ratio;
	}
	public String getUser_creationDateClean() {
		DateFormat dfl = DateFormat.getDateInstance(DateFormat.FULL);
        return dfl.format(getUser_creation());
	}
	/**
	 * @param user_creation the user_creation to set
	 */
	public void setUser_creation(Date user_creation) {
		this.user_creation = user_creation;
	}
	/**
	 * @return the user_point
	 */
	public int getUser_point() {
		return user_point;
	}
	public void addUserPoint(int points) {
		if(points<0 && -points>this.user_point) points = -this.user_point;
		this.user_point += points;
	}
	
	public void addUserPointsResult(int points) {
		if(points<0) this.user_point_lost += -points;
		else this.user_point_win += points;
	}
    /**
	 * @return the user_point_win
	 */
	public int getUser_point_win() {
		return user_point_win;
	}
	/**
	 * @return the user_point_lost
	 */
	public int getUser_point_lost() {
		return user_point_lost;
	}
	/**
	 * @return the user_ingame_point
	 */
	public int getUser_ingame_point() {
		int user_ingame_point = 0;
		List<ParisModel> parisNotFinish =  DataStore.getAllTeamParisByUser(user_id, false);
		if(parisNotFinish!=null) {
			for(ParisModel paris : parisNotFinish) {
				user_ingame_point += paris.getBet();
			}
		}
		return user_ingame_point;
	}

	/**
	 * @return the user_id
	 */
	public String getUser_id() {
		return user_id;
	}
}
