package controllers.cruds;

import controllers.Autenticar;
import controllers.CRUD;
import controllers.Secure;
import models.Provincia;
import play.mvc.With;

/**
 * @author Claudio Acuña
 */
@With(Autenticar.class)
@CRUD.For(Provincia.class)
public class Provincias extends CRUD {
}
