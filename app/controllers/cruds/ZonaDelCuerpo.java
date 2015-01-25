package controllers.cruds;

import controllers.AutenticarAdministracion;
import controllers.CRUD;
import models.ZonasDelCuerpo;
import play.mvc.With;

/**
 *@author Claudio Acuña
 */
@With(AutenticarAdministracion.class)
@CRUD.For(ZonasDelCuerpo.class)
public class ZonaDelCuerpo extends CRUD {
}
