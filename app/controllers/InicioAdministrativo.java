package controllers;

import models.Persona;
import play.mvc.Controller;
import play.mvc.With;

/**
 *@author Claudio Acuña
 */
@With(AutenticarAdministracion.class)
public class InicioAdministrativo extends Controller {

    /**
     * Función que renderisa el escritorio del administrativo.
     * @param altKey
     */
    public static void index(String altKey) {
        Persona persona = Persona.findPersonabyAltKey(altKey);
        render(persona);
    }
}
