package controllers;

import models.Persona;
import play.mvc.Controller;
import play.mvc.With;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Claudio Acuña
 */
@With(Autenticar.class)
public class InicioAdmin extends Controller {

    /**
     * Funcion que renderisa el escritorio del cliente
     *
     */
    public static void index(String altKey){
        Persona persona = Persona.findPersonabyAltKey(altKey);
        render(persona);
    }
}
