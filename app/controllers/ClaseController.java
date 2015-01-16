package controllers;

import controllers.variblesEstaticas.TipoUsuariosDTO;
import models.Clase;
import models.ClasesDTO.ClaseDTO;
import models.ClasesDTO.HorarioClaseDTO;
import models.Horario;
import models.Persona;
import models.TipoDeClase;
import models.error.ErrorJSON;
import play.data.binding.As;
import play.data.validation.Validation;
import play.mvc.Controller;
import play.mvc.Http;

import java.util.*;

/**
 * @author Claudio Acuña
 */
public class ClaseController extends Controller {

    public static void nuevaClase(ClaseDTO clase,@As ("HH:mm")Date horaDeInicio, @As("HH:mm") Date horaDeTermino){
        Validation.required("clase.tipoDeClase",clase.tipoDeClase);
        Validation.required("clase.dias",clase.dias);
        Validation.required("clase.profesor",clase.profesor);
        Validation.required("clase.cuposDisponibles",clase.cuposDisponibles);
        Validation.required("horaDeInicio",horaDeInicio);
        Validation.required("horaDeTermino",horaDeTermino);
        if (Validation.hasErrors()) {
            response.status = Http.StatusCode.BAD_REQUEST;
            renderJSON(ErrorJSON.fromValidation());
        }
        Persona persona = Persona.findPersonabyAltKey(clase.profesor);
        TipoDeClase tipo = TipoDeClase.findById(clase.tipoDeClase);
        Clase claseARegistrar = new Clase();
        claseARegistrar.setCupos(clase.cuposDisponibles);
        claseARegistrar.setProfesor(persona);
        claseARegistrar.setTipoDeClase(tipo);
        claseARegistrar.save();
        List<Horario> horario = new ArrayList<Horario>();
        Calendar dia = Calendar.getInstance();
        Date date = new Date();
        for (Integer aLong : clase.dias) {
            dia.set(Calendar.DAY_OF_WEEK,aLong);
            date = dia.getTime();
            Horario periodo = new Horario();
            periodo.setHoraInicio(horaDeInicio);
            periodo.setHoraTermino(horaDeTermino);
            periodo.setFecha(date);
            periodo.setClase(claseARegistrar);
            periodo.save();
            horario.add(periodo);
        }
        claseARegistrar.setHorarios(horario);
        claseARegistrar.save();
    }

    public static void getClasesDisponibles(){
        List<ClaseDTO> clase = Clase.findAllClasesActivas();
        renderJSON(clase);
    }

    public static void fichaClaseDirigida(String altKey){
        Clase clase = Clase.find("altKey",altKey).first();
        List<Horario> horarios = Horario.findListHorariosByAltKeyClase(altKey);
        List<HorarioClaseDTO> horario = new ArrayList<HorarioClaseDTO>();
        Calendar dia = Calendar.getInstance();
        for (Horario h : horarios) {
            HorarioClaseDTO horarioClaseDTO = new HorarioClaseDTO();
            dia.setTime(h.fecha);
            if (dia.get(Calendar.DAY_OF_WEEK) == 1){
                horarioClaseDTO.dia = "LUNES";
            }
            if (dia.get(Calendar.DAY_OF_WEEK) == 2){
                horarioClaseDTO.dia = "MARTES";
            }
            if (dia.get(Calendar.DAY_OF_WEEK) == 3){
                horarioClaseDTO.dia = "MIERCOLES";
            }
            if (dia.get(Calendar.DAY_OF_WEEK) == 4){
                horarioClaseDTO.dia = "JUEVES";
            }
            if (dia.get(Calendar.DAY_OF_WEEK) == 5){
                horarioClaseDTO.dia = "VIERNES";
            }
            if (dia.get(Calendar.DAY_OF_WEEK) == 6){
                horarioClaseDTO.dia = "SÁBADO";
            }
            if (dia.get(Calendar.DAY_OF_WEEK) == 7){
                horarioClaseDTO.dia = "DOMINGO";
            }
            horarioClaseDTO.horaDeInicio = h.horaInicio;
            horarioClaseDTO.horaDeTermino = h.horaTermino;
            horario.add(horarioClaseDTO);
        }
        if (session.get("tipo").equals(TipoUsuariosDTO.ADMIN)){
            renderTemplate("InicioAdmin/fichaClaseDirigidaAdmin.html",clase,horario);
        }
        if (session.get("tipo").equals(TipoUsuariosDTO.ADMINISTRATIVO)){
            renderTemplate("InicioAdministrativo/fichaClaseDirigidaVendedor.html",clase,horario);

        }

    }
}
