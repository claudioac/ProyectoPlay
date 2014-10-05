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
     * @param persona
     */
    public static void indexUsuario(Persona persona){
        render(persona);
    }
}
