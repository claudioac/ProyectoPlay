package controllers.cruds;

import controllers.Autenticar;
import controllers.CRUD;
import controllers.Secure;
import models.Comuna;
import play.mvc.With;

/**
 * @author Claudio Acuña
 */
@With(Autenticar.class)
@CRUD.For(Comuna.class)
public class Comunas extends CRUD {
}
