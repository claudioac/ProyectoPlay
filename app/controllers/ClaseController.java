package controllers;

import models.Clase;
import models.ClasesDTO.ClaseDTO;
import models.Horario;
import models.Persona;
import models.TipoDeClase;
import models.error.ErrorJSON;
import play.data.binding.As;
import play.data.validation.Validation;
import play.mvc.Controller;
import play.mvc.Http;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

}
