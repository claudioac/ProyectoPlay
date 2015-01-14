package controllers.cruds;

import controllers.AutenticarAdministracion;
import controllers.CRUD;
import models.TipoDeClase;
import play.mvc.With;

/**
 *@author Claudio Acuña
 */
@With(AutenticarAdministracion.class)
@CRUD.For(TipoDeClase.class)
public class TiposDeClases extends CRUD{
}
