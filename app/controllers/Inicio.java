package controllers;

import controllers.variblesEstaticas.TipoUsuariosDTO;
import models.Persona;
import play.mvc.Controller;

public class Inicio extends Controller {

    public static void index() {
        session.clear();
        render();
    }

    public static void login(String usuario, String password) {

        validation.required(usuario).message("Usuario Requerido");
        validation.required(password).message("Password Requerida");

        if (validation.hasErrors()){
            render("@Index");
        }

        Persona login = Persona.checkUsuario(usuario);
        if (login != null && login.password.equals(password)) {
            if (login.tipoUsuario.tipoUsuario.equals(TipoUsuariosDTO.ADMIN)) {
                session.put("user",usuario);
                session.put("tipo",login.tipoUsuario.tipoUsuario);
                InicioAdmin.index(login);
            }
            if (login.tipoUsuario.tipoUsuario.equals(TipoUsuariosDTO.USUARIO)){
                session.put("user",usuario);
                session.put("tipo",login.tipoUsuario.tipoUsuario);
                InicioUsuario.indexUsuario(login);
            }

            if(login.tipoUsuario.tipoUsuario.equals(TipoUsuariosDTO.PROFESOR)){
                session.put("user",usuario);
                session.put("tipo",login.tipoUsuario.tipoUsuario);
                InicioProfesor.index(login);
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