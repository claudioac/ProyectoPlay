package controllers.cruds;

import controllers.Autenticar;
import controllers.CRUD;
import controllers.Secure;
import models.Pais;
import play.mvc.With;

/**
 * @author Claudio Acuña
 */
@With(Autenticar.class)
@CRUD.For(Pais.class)
public class Paises extends CRUD {
}
