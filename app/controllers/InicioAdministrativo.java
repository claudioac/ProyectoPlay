package controllers;

import models.Persona;
import play.mvc.Controller;

/**
 *@author Claudio Acuña
 */
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
