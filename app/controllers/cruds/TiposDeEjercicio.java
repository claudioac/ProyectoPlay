package controllers.cruds;

import controllers.AutenticarAdministracion;
import controllers.CRUD;
import models.TipoDeEjercicio;
import play.mvc.With;

/**
 *@author Claudio Acuña
 */
@With(AutenticarAdministracion.class)
@CRUD.For(TipoDeEjercicio.class)
public class TiposDeEjercicio extends CRUD{
}
