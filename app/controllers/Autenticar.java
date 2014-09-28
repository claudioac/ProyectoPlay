package controllers;

import play.mvc.Before;
import play.mvc.Controller;

/**
 * @author Claudio Acuña
 */
public class Autenticar extends Controller {

    @Before
    public static void autenticar(){

        /**
         * Modulo de Seguridad
         */
        if(session.isEmpty()){
            flash.error("Please log in first");
            Inicio.index();
        }

    }
}
