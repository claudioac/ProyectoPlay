package controllers;

import models.Cita;
import models.Persona;
import play.mvc.Controller;

/**
 * @author Claudio Acuña
 */
public class AsesoriaController extends Controller {

    public static void cancelarCitaReservadaParaAsesoria(String altKeyCita){
        Cita cita = Cita.find("altKey",altKeyCita).first();
        cita.delete();
    }
}
