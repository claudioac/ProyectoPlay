package controllers.cruds;

import controllers.Autenticar;
import controllers.AutenticarAdministracion;
import controllers.CRUD;
import controllers.Secure;
import models.Region;
import play.mvc.With;

/**
 * @author Claudio Acuña
 */
@With(AutenticarAdministracion.class)
@CRUD.For(Region.class)
public class Regiones extends CRUD {
}
