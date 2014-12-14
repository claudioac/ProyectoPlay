package controllers.cruds;

import controllers.AutenticarAdministracion;
import controllers.CRUD;
import models.TipoPlan;
import play.mvc.With;

/**
 *@author Claudio Acuña
 */
@With(AutenticarAdministracion.class)
@CRUD.For(TipoPlan.class)
public class TiposDePlanes extends CRUD {
}
