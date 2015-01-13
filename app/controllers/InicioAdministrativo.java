package controllers;

import models.Persona;
import models.Region;
import play.mvc.Controller;
import play.mvc.With;

import java.util.List;

/**
 *@author Claudio Acuña
 */
@With(AutenticarAdministracion.class)
public class InicioAdministrativo extends Controller {

    /**
     * Función que renderisa el escritorio del administrativo.
     * @param altKey
     */
    public static void index(String altKey) {
        Persona persona = Persona.findPersonabyAltKey(altKey);
        render(persona);
    }

    public static void clientes(){
        List<Region> regiones = Region.getAllRegiones();
        render(regiones);
    }

    public static void profesores(){
        render();
    }

    public static void clases(){
        render();
    }
}
