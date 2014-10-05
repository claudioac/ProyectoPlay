package controllers.cruds;

import controllers.CRUD;
import controllers.Secure;
import models.Persona;
import play.mvc.With;

/**
 * @author Claudio Acuña
 */
@CRUD.For(Persona.class)
public class Personas extends CRUD {
}
