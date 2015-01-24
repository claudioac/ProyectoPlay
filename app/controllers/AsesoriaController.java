package controllers;

import controllers.variblesEstaticas.TipoUsuariosDTO;
import models.Cita;
import models.ClasesDTO.CitaEstadoDTO;
import models.EstadoCita;
import models.Persona;
import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.Years;
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

    public static void nuevaFichaDeSalud(String altKeyCliente){
        Persona cliente = Persona.findPersonabyAltKey(altKeyCliente);
        LocalDate fechaNacimiento = new LocalDate(cliente.fechaNacimiento);
        LocalDate fechaActual = new LocalDate();
        Period edad = new Period(fechaNacimiento,fechaActual, PeriodType.yearMonthDay());
        if (session.get("tipo").equals(TipoUsuariosDTO.PROFESOR)){
            renderTemplate("InicioProfesor/nuevaFichaDeSalud.html",cliente,edad);
        }
        if (session.get("tipo").equals(TipoUsuariosDTO.ADMINISTRATIVO)){
            renderTemplate("InicioAdministrativo/nuevaFichaDeSalud.html",cliente,edad);
        }
        if (session.get("tipo").equals(TipoUsuariosDTO.ADMIN)){
            renderTemplate("InicioAdmin/nuevaFichaDeSalud.html",cliente,edad);
        }
    }
}
