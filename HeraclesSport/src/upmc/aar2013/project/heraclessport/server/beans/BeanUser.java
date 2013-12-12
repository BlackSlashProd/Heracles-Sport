package upmc.aar2013.project.heraclessport.server.beans;
/**
 *
 */
public class BeanUser {
	private String mail;
	private String password;
	private String name; 
	
	public BeanUser() {
		mail = "";
		password = "";
		name = "";
	}
	
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getMail() {
		return mail;
	}
	
	public void setPassword(String pwd) {
		this.password = pwd;
	}
	public String getPassword() {
		return password;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
}
