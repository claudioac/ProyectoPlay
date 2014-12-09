package controllers;

import controllers.variblesEstaticas.NacionalidadDTO;
import controllers.variblesEstaticas.TipoUsuariosDTO;
import models.*;
import models.ClasesDTO.Cliente;
import play.data.binding.As;
import play.mvc.Controller;
import play.mvc.With;

import java.util.Date;

/**
 *@author Claudio Acuña
 */
@With(Autenticar.class)
public class ClientesController extends Controller {

    public static void nuevoCliente(Cliente cliente,@As("dd/MM/yyyy") Date fechaNacimiento){
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
