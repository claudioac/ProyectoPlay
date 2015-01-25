package controllers;

import models.FichaDeSalud;
import play.mvc.Controller;

/**
 * @author Claudio Acuña
 */
public class ResumenController extends Controller {


 public static void resumenFichaDeSalud(Long idFicha) {
     FichaDeSalud fichaDeSalud = FichaDeSalud.findById(idFicha);
     render(fichaDeSalud);
 }

}
