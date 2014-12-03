package controllers.cruds;

import controllers.Autenticar;
import controllers.CRUD;
import controllers.Secure;
import models.Persona;
import play.mvc.With;

/**
 * @author Claudio Acuña
 */
@With(Autenticar.class)
@CRUD.For(Persona.class)
public class Personas extends CRUD {
}
