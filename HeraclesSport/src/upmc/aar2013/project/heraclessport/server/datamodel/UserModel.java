package upmc.aar2013.project.heraclessport.server.datamodel;

import java.util.Date;

import com.googlecode.objectify.annotation.*;

@Entity
public class UserModel {
    @Id String user_id;
    String user_name;
    String user_pseudo;
    String user_mail;
    @Index Date user_creation;
    @Index int user_point;
    
    @SuppressWarnings("unused")
	private UserModel() {}
    
    public UserModel(String uid, String uname, String umail) {
    	user_id = uid;
    	user_name = uname;
    	user_pseudo = uname;
    	user_mail = umail;
    	user_creation = new Date();
    	user_point = Configs.getStartPoint();
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
	public Date getUser_creation() {
		return user_creation;
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
	/**
	 * @param user_point the user_point to set
	 */
	public void setUser_point(int user_point) {
		this.user_point = user_point;
	}
	/**
	 * @return the user_id
	 */
	public String getUser_id() {
		return user_id;
	}
    
    
}