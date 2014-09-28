package controllers;

import models.Persona;
import play.mvc.Controller;
import play.mvc.With;

/**
 * @author Claudio Acuña
 */
@With(Autenticar.class)
public class InicioAdmin extends Controller {

    /**
     * Funcion que renderisa el escritorio del cliente
     * @param persona
     */
    public static void index(Persona persona){
        render(persona);
    }
}
