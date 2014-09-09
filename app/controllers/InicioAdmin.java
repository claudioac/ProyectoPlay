package controllers;

import play.mvc.Controller;
import play.mvc.With;

/**
 * Created by claudio on 07-09-14.
 */
@With(Autenticar.class)
public class InicioAdmin extends Controller {

    public static void index(){
        render();
    }
}
