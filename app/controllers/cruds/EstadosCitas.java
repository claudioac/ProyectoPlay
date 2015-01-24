package controllers.cruds;

import controllers.AutenticarAdministracion;
import controllers.CRUD;
import models.EstadoCita;
import play.mvc.With;

/**
 * @author Claudio Acuña
 */
@With(AutenticarAdministracion.class)
@CRUD.For(EstadoCita.class)
public class EstadosCitas extends CRUD {
}
