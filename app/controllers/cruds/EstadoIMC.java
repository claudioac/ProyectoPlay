package controllers.cruds;

import controllers.AutenticarAdministracion;
import controllers.CRUD;
import models.EstadosIMC;
import play.mvc.With;

/**
 *@author Claudio Acuña
 */
@With(AutenticarAdministracion.class)
@CRUD.For(EstadosIMC.class)
public class EstadoIMC extends CRUD {
}
