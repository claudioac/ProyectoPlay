package controllers;

import controllers.variblesEstaticas.TipoUsuariosDTO;
import models.Login;
import play.mvc.Controller;

public class Inicio extends Controller {

    public static void index() {
        session.clear();
        render();
    }

    public static void login(String usuario, String password) {

        Login login = Login.checkUsuario(usuario);
        if (login != null && login.password.equals(password)) {
            if (login.tipoUsuario.tipoUsuario.equals(TipoUsuariosDTO.ADMIN)) {
                session.put("user",usuario);
                session.put("tipo",login.tipoUsuario.tipoUsuario);
                InicioAdmin.index();
            }
            if (login.tipoUsuario.tipoUsuario.equals(TipoUsuariosDTO.USUARIO)){
                session.put("user",usuario);
                session.put("tipo",login.tipoUsuario.tipoUsuario);
                InicioUsuario.indexUsuario();
            }

            if(login.tipoUsuario.tipoUsuario.equals(TipoUsuariosDTO.PROFESOR)){
                session.put("user",usuario);
                session.put("tipo",login.tipoUsuario.tipoUsuario);
                InicioProfesor.index();
            }

        } else {
            error("No existe Usuario");
        }
    }

}