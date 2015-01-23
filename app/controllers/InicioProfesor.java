package controllers;

import models.Cita;
import models.Persona;
import models.Region;
import models.TipoDeClase;
import play.mvc.Controller;
import play.mvc.With;

import java.util.List;

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
        String altKey = session.get("altKey");
        List<Cita> citas = Cita.findAllCitasByAltKeyProfesor(altKey);
        render(citas);
    }

    public static void clases(){
        List<TipoDeClase> tipoDeClases = TipoDeClase.findAll();
        List<Persona> profesores = Persona.findAllProfesores();
        render(tipoDeClases,profesores);
    }

    public static void clientes(){
        List<Region> regiones = Region.getAllRegiones();
        render(regiones);
    }
}
