package upmc.aar2013.project.heraclessport.server.front.forms;

import javax.servlet.http.HttpServletRequest;

import upmc.aar2013.project.heraclessport.server.datamodel.api.DataStore;
import upmc.aar2013.project.heraclessport.server.datamodel.users.UserModel;

/**
 * Formulaire de modification du profil.
 * AccountForm hérite des méthodes générales aux formulaires de GeneralForm.
 */
public class AccountForm extends GeneralForm {
    private static final String FIELD_PSEUDO  = "pseudo";
    
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
    		setResult("Modification réussite.");
    	}
		else {
			setResult("La modification a échoué.");
		}
        return user;
    }
    
    @SuppressWarnings("unused")
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
}
