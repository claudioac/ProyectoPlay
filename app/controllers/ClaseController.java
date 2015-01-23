package controllers;

import controllers.variblesEstaticas.TipoUsuariosDTO;
import models.*;
import models.ClasesDTO.ClaseDTO;
import models.ClasesDTO.CursosDTO;
import models.ClasesDTO.HorarioClaseDTO;
import models.ClasesDTO.SearchClasesQuery;
import models.error.ErrorJSON;
import net.sf.jxls.transformer.XLSTransformer;
import play.data.binding.As;
import play.data.validation.Validation;
import play.db.jpa.JPA;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.With;

import java.io.*;
import java.util.*;

/**
 * @author Claudio Acuña
 */
@With(Autenticar.class)
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

    public static void getClasesDisponibles(SearchClasesQuery clase){
        List<ClaseDTO> clases = Clase.findAllClasesActivas(clase);
        renderJSON(clases);
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
        if (session.get("tipo").equals(TipoUsuariosDTO.USUARIO)){
            renderTemplate("InicioUsuario/fichaDeClaseDirigida.html",clase,horario);
        }

    }

    public static void habilitarCuposParaClase(Date fechaDeCurso,String altKeyClase){
        Validation.required("fechaDeCurso",fechaDeCurso);
        if (Validation.hasErrors()) {
            response.status = Http.StatusCode.BAD_REQUEST;
            renderJSON(ErrorJSON.fromValidation());
        }
        Clase clase = Clase.find("altKey",altKeyClase).first();
        Curso curso = new Curso();
        curso.fechaDeCurso = fechaDeCurso;
        curso.clase = clase;
        curso.save();
    }

    public static void buscarCursos(Date fechaDeCurso,String altKeyClase){
        List<CursosDTO> cursos = Curso.findAllCursosByAltKeyYFecha(fechaDeCurso,altKeyClase);
        renderJSON(cursos);
    }

    public static void verFichaDeCurso(String altKey){
        Curso curso = Curso.find("altKey",altKey).first();
        Long cuposDisponibles = Long.valueOf(0);
        if (curso != null){
            cuposDisponibles = Long.valueOf((curso.clase.cupos - curso.clientes.size()));
        }
        if (session.get("tipo").equals(TipoUsuariosDTO.ADMIN)){
            renderTemplate("InicioAdmin/fichaDeCursoAdmin.html",curso,cuposDisponibles);
        }
        if (session.get("tipo").equals(TipoUsuariosDTO.ADMINISTRATIVO)){
            renderTemplate("InicioAdministrativo/fichaDeCursoAdministrativo.html",curso,cuposDisponibles);

        }
        if (session.get("tipo").equals(TipoUsuariosDTO.USUARIO)){
            renderTemplate("InicioUsuario/fichaDeCursoUsuario.html",curso,cuposDisponibles);
        }
    }

    public static void verificarCliente(String rutCliente){
       Persona cliente = Persona.findPersonaByRut(rutCliente);
       if (cliente != null){
           if (cliente.tipoUsuario.id.equals(TipoUsuariosDTO.IdUsuario)){
               ok();
           }else {
               Validation.addError("rutCliente","El Rut no Pertenece a un Cliente.");
               if (Validation.hasErrors()) {
                   response.status = Http.StatusCode.BAD_REQUEST;
                   renderJSON(ErrorJSON.fromValidation());
               }
           }
       }else {
           Validation.addError("rutCliente","El Rut del cliente no registra en el sistema.");
           if (Validation.hasErrors()) {
               response.status = Http.StatusCode.BAD_REQUEST;
               renderJSON(ErrorJSON.fromValidation());
           }
       }

    }

    public static void inscribirClienteACurso(String rutCliente, String altKeyCurso){
        Persona cliente = Persona.findPersonaByRut(rutCliente);
        Curso curso = Curso.find("altKey",altKeyCurso).first();
        if (curso.clientes.size() <= curso.clase.cupos){
            curso.clientes.add(cliente);
            curso.save();
        }else {
            response.status = Http.StatusCode.BAD_REQUEST;
        }
    }

    public static void eliminarClienteInscritoEnCurso(String altKeyPersona, String altKeyCurso){
        Persona cliente = Persona.findPersonabyAltKey(altKeyPersona);
        Curso curso = Curso.find("altKey",altKeyCurso).first();
        curso.clientes.remove(cliente);
        curso.save();
    }

    public static void listadoDeCurso(String altKeyCurso)throws Exception{
        //TODO - SE DEBE MEJORAR LA IMPLEMENTACIÓN DE EXPORTACIÓN.
        Curso curso = Curso.find("altKey",altKeyCurso).first();
        String template = "/Users/claudio/Desktop/Proyecto Final/Gym/app/views/ClaseController/listadoDeCurso.xls";
        String destino = "/Users/claudio/Desktop/curso.xls";
        try {
            InputStream in = new FileInputStream(template);
            Map bean = new HashMap();
            bean.put("curso",curso);
            XLSTransformer transformer = new XLSTransformer();
            transformer.transformXLS(template,bean, destino);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

       }

}
