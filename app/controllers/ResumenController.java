package controllers;

import models.ClasesDTO.EjercicioDTO;
import models.Ejercicio;
import models.FichaDeSalud;
import models.Producto;
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


    public static void resumenProducto(String altKeyProducto){
        Producto producto = Producto.find("altKey",altKeyProducto).first();
        render(producto);
    }

}
