package controllers;

import controllers.variblesEstaticas.NacionalidadDTO;
import controllers.variblesEstaticas.TipoUsuariosDTO;
import models.*;
import models.ClasesDTO.Cliente;
import play.data.binding.As;
import play.data.validation.Validation;
import play.mvc.Controller;
import play.mvc.With;

import java.util.Date;

/**
 *@author Claudio Acuña
 */
@With(Autenticar.class)
public class ClientesController extends Controller {

    public static void nuevoCliente(Cliente cliente,@As("dd/MM/yyyy") Date fechaNacimiento){

        Validation.required("cliente.nombres",cliente.nombres);
        Validation.required("cliente.apellidoMaterno",cliente.apellidoMaterno);
        Validation.required("cliente.apellidoPaterno",cliente.apellidoPaterno);
        Validation.required("cliente.rut",cliente.rut);
        Validation.required("cliente.celular",cliente.celular);
        Validation.required("cliente.telefono",cliente.telefono);
        Validation.required("cliente.genero",cliente.genero);
        if (cliente.comuna == 0){
            Validation.addError("cliente.comuna","validation.comuna");
        }
        Validation.required("cliente.direccion",cliente.direccion);
        Validation.required("fechaNacimiento",fechaNacimiento);

        if (Validation.hasErrors()){
            params.flash();
            Validation.keep();
            InicioAdmin.clientes();
        }

        Persona persona = new Persona();
        persona.setNombres(cliente.nombres);
        persona.setApellidoPaterno(cliente.apellidoPaterno);
        persona.setApellidoMaterno(cliente.apellidoMaterno);
        persona.setRut(cliente.rut);
        persona.setFechaNacimiento(fechaNacimiento);
        persona.setTelefono(cliente.telefono);
        persona.setCelular(cliente.celular);
        persona.setDireccion(cliente.direccion);
        persona.setGenero(cliente.genero);
        persona.setComuna(Comuna.getId(cliente.comuna));
        persona.setTipoUsuario(TipoUsuario.getById(TipoUsuariosDTO.IdUsuario));
        persona.setNacionalidad(Pais.findNacionalidadById(NacionalidadDTO.CHILENA));
        persona.save();
        Usuario usuario = new Usuario();
        usuario.setUsuario(cliente.rut);
        usuario.setPassword("123");
        usuario.save();
        persona.setUsuario(usuario);
        persona.save();
        redirect("/admin/clientes");
    }

}
