package controllers;

import controllers.variblesEstaticas.NacionalidadDTO;
import controllers.variblesEstaticas.TipoUsuariosDTO;
import models.*;
import play.data.binding.As;
import play.data.validation.Validation;
import play.db.jpa.JPA;
import play.mvc.Controller;

import java.util.Date;

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
        if (personal.comuna == 0) {
            Validation.addError("personal.comuna", "validation.comuna");
        }
        Validation.required("personal.direccion", personal.direccion);
        if (fechaNacimiento == null) {
            Validation.addError("fechaNacimiento", "validation.fechaRequerida");
        }
        Validation.required("personal.email", personal.email);
        Validation.email("personal.email", personal.email);

        if (Validation.hasErrors()) {
            params.flash();
            Validation.keep();
            InicioAdmin.index("");
        }

        Usuario usuario = new Usuario();
        Persona persona = new Persona();
        persona.setNombres(personal.nombres);
        persona.setApellidoPaterno(personal.apellidoPaterno);
        persona.setApellidoMaterno(personal.apellidoMaterno);
        persona.setRut(personal.rut);
        persona.setFechaNacimiento(fechaNacimiento);
        persona.setTelefono(personal.telefono);
        persona.setCelular(personal.celular);
        persona.setDireccion(personal.direccion);
        persona.setGenero(personal.genero);
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

}
