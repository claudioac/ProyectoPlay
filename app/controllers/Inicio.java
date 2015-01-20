package controllers;

import controllers.variblesEstaticas.TipoUsuariosDTO;
import models.ClasesDTO.UsuarioConectado;
import models.Persona;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Scope;

import javax.servlet.http.HttpSession;

/**
 * @author Claudio Acuña
 *
 */
public class Inicio extends Controller {

    /**
     * Función para pagina principal.
     */
    public static void index() {
        session.clear();
        render();
    }

    /**
     * Autentificador
     * @param usuario
     * @param password
     */
    public static void login(String usuario, String password) {

        validation.required(usuario).message("validation.userRequired");
        validation.required(password).message("validation.passwordRequired");

        if (validation.hasErrors()){
            render("@Index");
        }

        Persona login = Persona.checkUsuario(usuario);
        if (login != null && login.usuario.getPassword().equals(password)) {
            if (login.tipoUsuario.tipoUsuario.equals(TipoUsuariosDTO.ADMIN)){
                UsuarioConectado usuarioConectado = Persona.setUsuarioConectado(login);
                session.put("user",login.getNombreCompleto());
                session.put("tipo",login.tipoUsuario.tipoUsuario);
                InicioAdmin.index();
            }
            if (login.tipoUsuario.tipoUsuario.equals(TipoUsuariosDTO.USUARIO)){
                session.put("user",login.getNombreCompleto());
                session.put("tipo",login.tipoUsuario.tipoUsuario);
                InicioUsuario.indexUsuario();
            }

            if(login.tipoUsuario.tipoUsuario.equals(TipoUsuariosDTO.PROFESOR)){
                session.put("user",login.getNombreCompleto());
                session.put("tipo",login.tipoUsuario.tipoUsuario);
                InicioProfesor.index();
            }

            if (login.tipoUsuario.tipoUsuario.equals(TipoUsuariosDTO.ADMINISTRATIVO)){
                session.put("user",login.getNombreCompleto());
                session.put("tipo",login.tipoUsuario.tipoUsuario);
                InicioAdministrativo.index();
            }

        }else {
           String error = "No se Encuentra el usuario";
           flash("error",error);
           renderTemplate("@index",error);

        }

    }

    public static void acercaDe(){
        renderTemplate("@acerca_de");
    }

    public static void contacto(){
        renderTemplate("@contacto");
    }

}