package controllers.cruds;

import controllers.Autenticar;
import controllers.AutenticarAdministracion;
import controllers.CRUD;
import controllers.Secure;
import models.Persona;
import play.mvc.With;

/**
 * @author Claudio Acuña
 */
@With(AutenticarAdministracion.class)
@CRUD.For(Persona.class)
public class Personas extends CRUD {
}
