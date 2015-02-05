package controllers.cruds;

import controllers.AutenticarAdministracion;
import controllers.CRUD;
import models.Producto;
import play.mvc.With;

/**
 * @author Claudio Acuña
 */
@With(AutenticarAdministracion.class)
@CRUD.For(Producto.class)
public class ProductosDisponibles extends CRUD {
}
