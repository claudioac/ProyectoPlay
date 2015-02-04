package controllers;

import controllers.variblesEstaticas.TipoUsuariosDTO;
import models.ClasesDTO.ProductoDTO;
import models.Producto;
import models.TipoDeProducto;
import models.error.ErrorJSON;
import play.data.validation.Validation;
import play.mvc.Controller;
import play.mvc.Http;

import java.util.List;

/**
 * @author Claudio Acuña
 */
public class ProductosController extends Controller {

    public static void listadoDeProductos(){
        List<TipoDeProducto> categorias = TipoDeProducto.findAll();
        if (session.get("tipo").equals(TipoUsuariosDTO.ADMIN)){
            renderTemplate("InicioAdmin/listadoDeProductosAdmin.html",categorias);
        }
    }

    public static void nuevoProducto(ProductoDTO producto){
        Validation.required("producto.categoria",producto.categoria);
        if(producto.cantidadDisponible == 0){
            Validation.addError("producto.cantidadDisponible", "Necesita Ingresar un Valor");
        }
        Validation.required("producto.descripcionProducto",producto.descripcionProducto);
        Validation.required("producto.nombreProducto",producto.nombreProducto);
        Validation.required("producto.precio",producto.precio);
        if (Validation.hasErrors()) {
            response.status = Http.StatusCode.BAD_REQUEST;
            renderJSON(ErrorJSON.fromValidation());
        }
        Producto nuevoProducto = new Producto();
        nuevoProducto.activo = true;
        nuevoProducto.cantidadDisponible = producto.cantidadDisponible;
        nuevoProducto.descripcion = producto.descripcionProducto;
        nuevoProducto.nombre = producto.nombreProducto;
        nuevoProducto.precio = producto.precio;
        nuevoProducto.tipoDeProducto = TipoDeProducto.findById(producto.categoria);
        nuevoProducto.save();
    }

    public static void eliminarProducto(){

    }

    public static void editarProducto(){

    }

    public static void descontarStockDisponible(){

    }

    public static void getProductosDisponibles(Long categoria){
        List<ProductoDTO> productos = Producto.findAllProductos(categoria);
        renderJSON(productos);
    }

}
