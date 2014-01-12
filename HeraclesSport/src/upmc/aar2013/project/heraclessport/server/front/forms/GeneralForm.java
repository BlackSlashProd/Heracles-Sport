package upmc.aar2013.project.heraclessport.server.front.forms;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

/**
 * GeneralForm regroupe les fonctions utiles à tout formulaire.
 */
public class GeneralForm {
    private String result;
    private Map<String,String> errors = new HashMap<String,String>();

    public String getResult() {
        return result;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
    
    protected void setResult(String res) {
    	result = res;
    }
    
    protected void setError(String field, String message) {
        errors.put(field,message);
    }

    protected static String getFieldValue(HttpServletRequest request, String fieldName) {
        String value = request.getParameter(fieldName);
        if(value == null || value.trim().length() == 0) {
            return null;
        } else {
            return value;
        }
    }
}
