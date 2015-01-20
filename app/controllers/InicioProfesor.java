package controllers;

import models.Persona;
import play.mvc.Controller;
import play.mvc.With;

/**
 * @author Claudio Acuña
 *
 */
@With(Autenticar.class)
public class InicioProfesor extends Controller {

    /**
     * Funcion que renderisa la pagina principal del profesor
     */
    public static void index() {
        render();
    }
}
