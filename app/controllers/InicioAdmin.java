package controllers;


import models.*;
import models.ClasesDTO.PersonaDTO;
import models.ClasesDTO.SearchPersonalQuery;
import models.ClasesDTO.SearchPersonasQuery;
import models.ClasesDTO.UsuarioConectado;
import play.mvc.Controller;
import play.mvc.Scope;
import play.mvc.With;

import java.util.List;

/**
 * @author Claudio Acuña
 */
@With(AutenticarAdministracion.class)
public class InicioAdmin extends Controller {

    /**
     * Funcion que renderisa el escritorio del cliente
     */
    public static void index() {
        List<Region> regiones = Region.getAllRegiones();
        render(regiones);
    }

    public static void clientes() {
        List<Region> regiones = Region.getAllRegiones();
        render(regiones);
    }

    public static void profesores(){
        render();
    }

    public static void clases(){
      List<TipoDeClase> tipoDeClases = TipoDeClase.findAll();
      List<Persona> profesores = Persona.findAllProfesores();
      render(tipoDeClases,profesores);
    }

    public static void getPersonas(SearchPersonasQuery personas) {
        List<PersonaDTO> persona = Persona.getAllPersonas(personas);
        renderJSON(persona);
    }

    public static void getClientes() {
        List<PersonaDTO> clientes = Persona.getAllClientes();
        renderJSON(clientes);
    }

    public static void getProfesores(SearchPersonalQuery personal) {
        List<PersonaDTO> profesores = Persona.getAllProfesores(personal);
        renderJSON(profesores);
    }

    public static void getPersona(String rut) {
        Persona persona = Persona.findPersonaByRut(rut);
        renderJSON(persona);
    }

    public static void getProvincias(Long idRegion) {
        List<Provincia> provincias = Provincia.getAllProvincias(idRegion);
        renderJSON(provincias);
    }

    public static void getComunas(Long idProvincia){
        List<Comuna> comunas = Comuna.getAllComunas(idProvincia);
        renderJSON(comunas);
    }
}
