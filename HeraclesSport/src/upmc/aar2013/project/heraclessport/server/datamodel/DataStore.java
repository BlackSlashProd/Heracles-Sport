package upmc.aar2013.project.heraclessport.server.datamodel;

import static com.googlecode.objectify.ObjectifyService.ofy;

import com.googlecode.objectify.ObjectifyService;

public class DataStore {
    static {
        ObjectifyService.register(UserModel.class);
    }
    
	public static UserModel getUser(String userId) {
		UserModel um = ofy().load().type(UserModel.class).filter("user_id", userId).first().now();
		return um;
	}
	
	public static void storeUser(UserModel um) {
		ofy().save().entity(um).now();
	}
}
