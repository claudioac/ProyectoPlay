package models.error;

import org.apache.commons.lang.StringUtils;
import play.data.validation.*;
import play.data.validation.Error;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Alonso Rivera.
 */
public class ErrorJSON {
    public List<String> globalErrors = new ArrayList<String>();
    public Map<String, List<String>> fieldErrors = new HashMap<String, List<String>>();

    public static ErrorJSON fromValidation() {
        ErrorJSON errorJSON = new ErrorJSON();
        for (Error e : Validation.errors()) {
            if (StringUtils.isBlank(e.getKey())) {
                errorJSON.globalErrors.add(e.message());
            } else {
                List<String> errors = errorJSON.fieldErrors.get(e.getKey());
                if (errors == null) {
                    errors = new ArrayList<String>();
                    errorJSON.fieldErrors.put(e.getKey(), errors);
                }
                errors.add(e.message());
            }
        }
        return errorJSON;
    }

}
