package controllers;

import controllers.variblesEstaticas.TipoUsuariosDTO;
import play.mvc.Before;
import play.mvc.Controller;

/**
 * @author Claudio Acuña
 */

public class AutenticarAdministracion extends Controller{

    /**
     * Modulo de Seguridad Para la Administración
     */
    @Before
    public static void autenticar (){

        if (session.isEmpty()){
            flash.error("Please log in first");
            Inicio.index();
        }
        if (!session.get("tipo").equals(TipoUsuariosDTO.ADMIN) && !session.get("tipo").equals(TipoUsuariosDTO.ADMINISTRATIVO) && !session.get("tipo").equals(TipoUsuariosDTO.PROFESOR)){
            Inicio.index();
        }
    }

}
