package controllers;

import models.Persona;
import play.mvc.Controller;

/**
 *@author Claudio Acuña
 */
public class InicioAdministrativo extends Controller {

    /**
     * Función que renderisa el escritorio del administrativo.
     */
    public static void index(Persona persona) {
        render(persona);
    }
}
