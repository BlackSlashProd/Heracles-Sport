package upmc.aar2013.project.heraclessport.server.front.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.*;

import upmc.aar2013.project.heraclessport.server.beans.BeanUser;

public class ConnexionForm {
	
    private static final String FIELD_MAIL_ID  = "mail";
    private static final String FIELD_PWD_ID   = "psswd";

    private String result;
    private Map<String,String> errors = new HashMap<String,String>();

    public String getResult() {
        return result;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public BeanUser userConnect(HttpServletRequest request) {
        String mail = getFieldValue(request, FIELD_MAIL_ID);
        String password = getFieldValue(request, FIELD_PWD_ID);
        BeanUser user = new BeanUser();
        try {
            checkMail(mail);
        } catch(Exception e) {
            setError(FIELD_MAIL_ID, e.getMessage());
        }
        user.setMail(mail);
        try {
            checkPassword(password);
        } catch(Exception e) {
            setError(FIELD_PWD_ID, e.getMessage());
        }
        user.setPassword(password);
        if(errors.isEmpty()) {
            result = "Connexion réussite.";
        } else {
            result = "Connexion échouée.";
        }

        return user;
    }

    private void checkMail(String mail) throws Exception {
        if(mail != null && !mail.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
            throw new Exception("Adresse mail non valide.");
        }
        else if(mail==null) {
        	throw new Exception("Veuillez saisir une adresse mail.");
        }
    }

    private void checkPassword(String password) throws Exception {
        if(password != null) {
            if(password.length() < 3) {
                throw new Exception("Le mot de passe doit contenir au moins 3 caractères.");
            }
        } else {
            throw new Exception("Veuillez saisir votre mot de passe.");
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
