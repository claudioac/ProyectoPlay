package controllers;

import controllers.variblesEstaticas.TipoUsuariosDTO;
import models.Cita;
import models.ClasesDTO.CitaEstadoDTO;
import models.EstadoCita;
import models.Persona;
import play.mvc.Controller;

import java.util.List;

/**
 * @author Claudio Acuña
 */
public class AsesoriaController extends Controller {

    public static void cancelarCitaReservadaParaAsesoria(String altKeyCita){
        Cita cita = Cita.find("altKey",altKeyCita).first();
        cita.delete();
    }

    public static void asesorarCliente(String altKeyCita, String altKeyCliente){
        Persona cliente = Persona.findPersonabyAltKey(altKeyCliente);
        Cita cita = Cita.find("altKey",altKeyCita).first();
        cita.estado = EstadoCita.findById(CitaEstadoDTO.EN_PROCESO);
        cita.save();
        if (session.get("tipo").equals(TipoUsuariosDTO.PROFESOR)){
            renderTemplate("InicioProfesor/asesorarClienteProfesor.html",cliente);
        }
    }
}
