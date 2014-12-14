package controllers.cruds;

import controllers.Autenticar;
import controllers.AutenticarAdministracion;
import controllers.CRUD;
import controllers.Secure;
import models.TipoUsuario;
import play.mvc.With;

/**
 * @author Claudio Acuña
 */
@With(AutenticarAdministracion.class)
@CRUD.For(TipoUsuario.class)
public class TipoUsuarios extends CRUD {
}
