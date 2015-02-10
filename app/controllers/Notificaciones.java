package controllers;


import models.Contacto;
import models.Persona;
import play.mvc.Mailer;

/**
 *@author Claudio Acuña
 */
public class Notificaciones extends Mailer {

    public static void respuestaContacto(Persona cliente, Contacto contacto){
        setFrom("Gimansio <planetGym@planet.cl>");
        setSubject("Respuesta a Contacto Nº"+contacto.id);
        addRecipient(contacto.email);
        send(cliente,contacto);
     }

}
