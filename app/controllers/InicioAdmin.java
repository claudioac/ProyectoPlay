package controllers;

import controllers.cruds.TipoUsuarios;
import controllers.variblesEstaticas.TipoUsuariosDTO;
import models.ClasesDTO.PersonaDTO;
import models.Comuna;
import models.Persona;
import models.Provincia;
import models.Region;
import play.db.jpa.JPA;
import play.mvc.Controller;
import play.mvc.With;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Claudio Acuña
 */
@With(AutenticarAdministracion.class)
public class InicioAdmin extends Controller {

    /**
     * Funcion que renderisa el escritorio del cliente
     */
    public static void index(String altKey) {
        List<Region> regiones = Region.getAllRegiones();
        Persona persona = Persona.findPersonabyAltKey(altKey);
        render(persona,regiones);
    }

    public static void clientes() {
        List<Region> regiones = Region.getAllRegiones();
        render(regiones);
    }

    public static void profesores(){
        render();
    }

    public static void getPersonas() {
        List<PersonaDTO> personas = Persona.getAllPersonas();
        renderJSON(personas);
    }

    public static void getClientes() {
        List<PersonaDTO> clientes = Persona.getAllClientes();
        renderJSON(clientes);
    }

    public static void getProfesores() {
        List<PersonaDTO> profesores = Persona.getAllProfesores();
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
