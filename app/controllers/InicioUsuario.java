package controllers;

import models.Persona;
import play.mvc.Controller;
import play.mvc.With;

/**
 * Created by claudio on 05-09-14.
 */
@With(Autenticar.class)
public class InicioUsuario extends Controller{

    public static void indexUsuario(Persona persona){
        renderArgs.put("persona",persona);
        render();
    }
}
