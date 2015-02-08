package controllers;

import models.*;
import models.ClasesDTO.EjercicioDTO;
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

    public static void resumenContactoUsuario(String altKeyContacto){
        Contacto contacto = Contacto.find("altKey",altKeyContacto).first();
        render(contacto);
    }

}
