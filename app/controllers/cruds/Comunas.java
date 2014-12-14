package controllers.cruds;

import controllers.Autenticar;
import controllers.AutenticarAdministracion;
import controllers.CRUD;
import controllers.Secure;
import models.Comuna;
import play.mvc.With;

/**
 * @author Claudio Acuña
 */
@With(AutenticarAdministracion.class)
@CRUD.For(Comuna.class)
public class Comunas extends CRUD {
}
