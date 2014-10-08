package controllers;

import models.Persona;
import play.mvc.Controller;
import play.mvc.With;

/**
 * @author Claudio Acuña
 */
@With(Autenticar.class)
public class InicioUsuario extends Controller{

    /**
     * Funcion que renderisa la página principal del usuario.
     * @param altKey
     */
    public static void indexUsuario(String altKey){
        Persona persona = Persona.findPersonabyAltKey(altKey);
        render(persona);
    }
}
