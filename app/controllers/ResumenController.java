package controllers;

import models.ClasesDTO.EjercicioDTO;
import models.Ejercicio;
import models.FichaDeSalud;
import models.Rutina;
import play.mvc.Controller;

import java.util.List;

/**
 * @author Claudio Acuña
 */
public class ResumenController extends Controller {


 public static void resumenFichaDeSalud(Long idFicha) {
     FichaDeSalud fichaDeSalud = FichaDeSalud.findById(idFicha);
     render(fichaDeSalud);
 }

 public static void resumenRutinaDeEjercicios(String altKeyRutina){
     Rutina rutina = Rutina.findRutinaByAltKey(altKeyRutina);
     List<EjercicioDTO> ejercicios = Ejercicio.findAllEjerciciosByAltKeyRutina(altKeyRutina);
     render(rutina,ejercicios);
 }

}
