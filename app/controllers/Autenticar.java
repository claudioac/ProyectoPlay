package controllers;

import play.mvc.Before;
import play.mvc.Controller;

/**
 * Created by claudio on 07-09-14.
 */
public class Autenticar extends Controller {

    @Before
    public static void autenticar(){

        if(session.isEmpty()){
            flash.error("Please log in first");
            Inicio.index();
        }

    }
}
