package controllers;

import controllers.variblesEstaticas.TipoUsuariosDTO;
import play.mvc.Controller;

/**
 * @author Claudio Acuña
 */
public class ProductosController extends Controller {

    public static void listadoDeProductos(){

        if (session.get("tipo").equals(TipoUsuariosDTO.ADMIN)){
            renderTemplate("InicioAdmin/listadoDeProductosAdmin.html");
        }
    }

    public static void nuevoProducto(){

    }

    public static void eliminarProducto(){

    }

    public static void editarProducto(){

    }

    public static void descontarStockDisponible(){

    }

}
