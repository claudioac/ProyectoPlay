package controllers;

import controllers.variblesEstaticas.NacionalidadDTO;
import controllers.variblesEstaticas.TipoUsuariosDTO;
import models.*;
import models.error.ErrorJSON;
import play.data.binding.As;
import play.data.validation.Validation;
import play.mvc.Controller;
import play.mvc.Http;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Claudio Acuña
 */
public class Personal extends Controller {


    /**
     * Función que persiste a un nuevo personal interno para el establecimiento.
     * @param personal
     * @param fechaNacimiento
     */
    public static void nuevoPersonalInterno(models.ClasesDTO.Personal personal, @As("dd/mm/yyyy") Date fechaNacimiento, Long tipoDePersonal) {

        Validation.required("personal.nombres", personal.nombres);
        Validation.required("personal.apellidoMaterno", personal.apellidoMaterno);
        Validation.required("personal.apellidoPaterno", personal.apellidoPaterno);
        Validation.required("personal.rut", personal.rut);
        Validation.required("personal.celular", personal.celular);
        Validation.required("personal.telefono", personal.telefono);
        Validation.required("personal.genero", personal.genero);
        if(personal.comuna == null){
            Validation.addError("personal.comuna", "validation.comuna");
        }
        if (personal.region == null){
            Validation.addError("personal.region", "validation.region");
        }
        if(personal.provincia == null){
            Validation.addError("personal.provincia", "validation.provincia");
        }
        Validation.required("personal.direccion", personal.direccion);
        if (fechaNacimiento == null) {
            Validation.addError("fechaNacimiento", "validation.fechaRequerida");
        }
        Validation.required("personal.email", personal.email);
        Validation.email("personal.email", personal.email);

        if (Validation.hasErrors()) {
            response.status = Http.StatusCode.BAD_REQUEST;
            renderJSON(ErrorJSON.fromValidation());
        }

        Usuario usuario = new Usuario();
        Persona persona = new Persona();
        persona.setNombres(personal.nombres);
        persona.setApellidoPaterno(personal.apellidoPaterno);
        persona.setApellidoMaterno(personal.apellidoMaterno);
        persona.setRut(personal.rut);
        persona.setFechaNacimiento(fechaNacimiento);
        persona.setFechaDeIncorporacion(new Date());
        persona.setTelefono(personal.telefono);
        persona.setCelular(personal.celular);
        persona.setDireccion(personal.direccion);
        persona.setGenero(personal.genero);
        persona.setRegion(Region.getId(personal.region));
        persona.setProvincia(Provincia.getId(personal.provincia));
        persona.setComuna(Comuna.getId(personal.comuna));
        if (tipoDePersonal.equals(TipoUsuariosDTO.IdProfesor)){
            persona.setTipoUsuario(TipoUsuario.getById(TipoUsuariosDTO.IdProfesor));
        }
        if (tipoDePersonal.equals(TipoUsuariosDTO.IdVendedor)){
            persona.setTipoUsuario(TipoUsuario.getById(TipoUsuariosDTO.IdVendedor));
        }
        if (tipoDePersonal.equals(TipoUsuariosDTO.IdAdministrador)){
            persona.setTipoUsuario(TipoUsuario.getById(TipoUsuariosDTO.IdAdministrador));
        }
        persona.setNacionalidad(Pais.findNacionalidadById(NacionalidadDTO.CHILENA));
        usuario.setUsuario(personal.rut.trim());
        usuario.setPassword("1234");
        usuario.setEmail(personal.email);
        persona.setUsuario(usuario);
        usuario.save();
        persona.save();
    }

    public static void fichaPersonalInterno(String altKey){
        Persona personal = Persona.findPersonabyAltKey(altKey);
        List<Horario> horario = Horario.findListHorarioByAltkeyPersona(altKey);
        if (session.get("tipo").equals(TipoUsuariosDTO.ADMIN)){
            renderTemplate("InicioAdmin/Personal/fichaDePersonal.html",personal,horario);
        }
    }

    public static void actualizarPersonalInterno(String altKey){
        Persona personal = Persona.findPersonabyAltKey(altKey);
        List<Region> regiones = Region.getAllRegiones();
        Horario horario = Horario.findHorarioPersonaByAltKey(altKey);
        if (session.get("tipo").equals(TipoUsuariosDTO.ADMIN)){
            renderTemplate("InicioAdmin/Personal/actualizarPersonalAdmin.html",personal,regiones,horario);
        }
    }

    public static void ingresarHorarioProfesional(String altKeyPersona, @As("HH:mm") Date  horaEntrada, @As("HH:mm") Date horaSalida){
        Validation.required("horaEntrada",horaEntrada);
        Validation.required("horaSalida",horaSalida);
        if (Validation.hasErrors()) {
            response.status = Http.StatusCode.BAD_REQUEST;
            renderJSON(ErrorJSON.fromValidation());
        }
            Persona persona = Persona.findPersonabyAltKey(altKeyPersona);
            Horario horario = Horario.findHorarioPersonaByAltKey(altKeyPersona);
            if (horario != null){
                horario.setHoraInicio(horaEntrada);
                horario.setHoraTermino(horaSalida);
                horario.setPersona(persona);
                horario.save();
            }else {
                horario = new Horario();
                horario.setHoraInicio(horaEntrada);
                horario.setHoraTermino(horaSalida);
                horario.setPersona(persona);
                horario.save();
            }

    }


}
