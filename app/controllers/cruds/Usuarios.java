package controllers.cruds;

import controllers.Autenticar;
import controllers.CRUD;
import models.Usuario;
import play.mvc.With;

/**
 * @author Claudio Acuña
 */
@With(Autenticar.class)
@CRUD.For(Usuario.class)
public class Usuarios extends CRUD{
}
