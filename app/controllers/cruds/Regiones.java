package controllers.cruds;

import controllers.Autenticar;
import controllers.CRUD;
import controllers.Secure;
import models.Region;
import play.mvc.With;

/**
 * @author Claudio Acuña
 */
@With(Autenticar.class)
@CRUD.For(Region.class)
public class Regiones extends CRUD {
}
