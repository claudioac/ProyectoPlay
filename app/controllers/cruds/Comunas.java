package controllers.cruds;

import controllers.CRUD;
import controllers.Secure;
import models.Comuna;
import play.mvc.With;

/**
 * @author Claudio Acuña
 */
@With(Secure.class)
@CRUD.For(Comuna.class)
public class Comunas extends CRUD {
}
