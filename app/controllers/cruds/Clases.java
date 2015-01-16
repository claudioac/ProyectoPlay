package controllers.cruds;

import controllers.AutenticarAdministracion;
import controllers.CRUD;
import models.Clase;
import play.mvc.With;

/**
 * @author Claudio Acuña
 */
@With(AutenticarAdministracion.class)
@CRUD.For(Clase.class)
public class Clases extends CRUD {

}
