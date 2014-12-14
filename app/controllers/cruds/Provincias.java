package controllers.cruds;

import controllers.Autenticar;
import controllers.AutenticarAdministracion;
import controllers.CRUD;
import controllers.Secure;
import models.Provincia;
import play.mvc.With;

/**
 * @author Claudio Acuña
 */
@With(AutenticarAdministracion.class)
@CRUD.For(Provincia.class)
public class Provincias extends CRUD {
}
