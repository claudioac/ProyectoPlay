package controllers.cruds;

import controllers.AutenticarAdministracion;
import controllers.CRUD;
import models.ContratoEstado;
import play.mvc.With;

/**
 * @author Claudio Acuña
 */
@With(AutenticarAdministracion.class)
@CRUD.For(ContratoEstado.class)
public class ContratoEstados extends CRUD {
}
