package controllers.cruds;

import controllers.CRUD;
import controllers.Secure;
import models.TipoUsuario;
import play.mvc.With;

/**
 * @author Claudio Acuña
 */
@CRUD.For(TipoUsuario.class)
public class TipoUsuarios extends CRUD {
}
