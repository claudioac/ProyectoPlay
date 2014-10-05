package controllers.cruds;

import controllers.CRUD;
import controllers.Secure;
import models.Pais;
import play.mvc.With;

/**
 * @author Claudio Acuña
 */

@CRUD.For(Pais.class)
public class Paises extends CRUD {
}
