package controllers;

import models.Cita;
import models.Clase;
import models.ClasesDTO.CitasDTO;
import models.ClasesDTO.PersonaDTO;
import models.Persona;
import models.TipoDeClase;
import models.error.ErrorJSON;
import play.data.validation.Validation;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.With;

import java.util.Date;
import java.util.List;

/**
 * @author Claudio Acuña
 */
@With(Autenticar.class)
public class InicioUsuario extends Controller {

    /**
     * Funcion que renderisa la página principal del usuario.
     */
    public static void indexUsuario() {
        render();
    }

    public static void solicitarAsesoramiento() {
        List<PersonaDTO> profesores = Persona.findAllProfesoresParaCitas();
        render(profesores);
    }

    public static void clasesDisponibles(){
        List<TipoDeClase> tipoDeClases = TipoDeClase.findAll();
        render(tipoDeClases);
    }

    public static void reservarHoraParaAsesoria(String altKeyProfesor, String altKeyCliente, Date fechaDeAsesoria) {
        Validation.required("fechaDeAsesoria",fechaDeAsesoria);
        if (Validation.hasErrors()) {
            response.status = Http.StatusCode.BAD_REQUEST;
            renderJSON(ErrorJSON.fromValidation());
        }
        Persona profesor = Persona.findPersonabyAltKey(altKeyProfesor);
        Persona cliente = Persona.findPersonabyAltKey(altKeyCliente);
        Cita cita  = new Cita();
        cita.fecha = fechaDeAsesoria;
        cita.profesor = profesor;
        cita.cliente = cliente;
        cita.save();
    }

    public static void misCitasParaAsesoria(){
        String altKeyCliente = session.get("altKey");
        Persona usuarioConectado = Persona.findPersonabyAltKey(altKeyCliente);
        List<CitasDTO> citas = Cita.findAllCitasByPersona(usuarioConectado);
        render(citas);
    }

    public static void cancelarCitaReservadaParaAsesoria(String altKeyCita){
        String altKeyCliente = session.get("altKey");
        Persona usuarioConectado = Persona.findPersonabyAltKey(altKeyCliente);
        Cita cita = Cita.find("altKey",altKeyCita).first();
        cita.delete();
    }
}
