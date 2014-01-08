package upmc.aar2013.project.heraclessport.server.front.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import upmc.aar2013.project.heraclessport.server.datamodel.api.DataStore;
import upmc.aar2013.project.heraclessport.server.datamodel.users.UserModel;

public class AccountForm {
    private static final String FIELD_PSEUDO  = "pseudo";

    private String result;
    private Map<String,String> errors = new HashMap<String,String>();

    public String getResult() {
        return result;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
    
    public UserModel modifyAccount(HttpServletRequest request, String userId) {
        String pseudo = getFieldValue(request, FIELD_PSEUDO);
        UserModel user = DataStore.getUser(userId);
        boolean noError = true;
        try {
        	pseudo = pseudo.replaceAll("\\W","");
            checkPseudo(pseudo);
            checkExistsPseudo(pseudo);
        } catch(Exception e) {
        	noError = false;
            setError(FIELD_PSEUDO, e.getMessage());
        }
        if(noError && user!=null) {
    		user.setUser_pseudo(pseudo);
    		DataStore.storeUser(user);
    		result = "Modification réussite.";
    	}
		else {
			result = "La modification a échoué.";
		}
        return user;
    }
    
    private void checkMail(String mail) throws Exception {
        if(mail != null && !mail.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
            throw new Exception("Adresse mail non valide.");
        }
    }
    
    private void checkPseudo(String psd) throws Exception {
        if ( psd != null && psd.length() < 3 ) {
            throw new Exception( "Le pseudo doit contenir au moins 3 caractères et aucun caractère spécial." );
        }
    }
    
    private void checkExistsPseudo(String psd) throws Exception {
    	UserModel user = DataStore.getUserByPseudo(psd);
    	if(user !=null) {
    		throw new Exception( "Ce pseudo n'est pas disponible." );
    	}
    }
    
    private void setError(String field, String message) {
        errors.put(field,message);
    }

    private static String getFieldValue(HttpServletRequest request, String fieldName) {
        String value = request.getParameter(fieldName);
        if(value == null || value.trim().length() == 0) {
            return null;
        } else {
            return value;
        }
    }
}
