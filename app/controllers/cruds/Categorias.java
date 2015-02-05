package controllers.cruds;

import controllers.AutenticarAdministracion;
import controllers.CRUD;
import models.TipoDeProducto;
import play.mvc.With;

/**
 * @author Claudio Acuña
 */
@With(AutenticarAdministracion.class)
@CRUD.For(TipoDeProducto.class)
public class Categorias extends CRUD {
}
