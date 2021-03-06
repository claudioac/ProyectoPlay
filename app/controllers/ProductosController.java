package controllers;

import controllers.variblesEstaticas.TipoUsuariosDTO;
import models.ClasesDTO.ProductoDTO;
import models.Producto;
import models.TipoDeProducto;
import models.error.ErrorJSON;
import mvc.results.JxlsResult;
import play.data.validation.Validation;
import play.mvc.Controller;
import play.mvc.Http;

import java.util.HashMap;
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
        if (session.get("tipo").equals(TipoUsuariosDTO.ADMINISTRATIVO)){
            renderTemplate("InicioAdministrativo/listadoDeProductosAdministrativo.html",categorias);
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

    public static void exportarProductos(){
        List<Producto> productos = Producto.findAll();
        HashMap<String, Object> model = new HashMap<String, Object>();
        model.put("productos",productos);
        throw new JxlsResult(model, "Lista de Productos");
    }

    public static void descontarStockDisponible(String altKeyProducto){
       Producto producto = Producto.find("altKey",altKeyProducto).first();
       producto.cantidadDisponible = (producto.cantidadDisponible-1);
       producto.save();
    }

    public static void getProductosDisponibles(Long categoria){
        List<ProductoDTO> productos = Producto.findAllProductos(categoria);
        renderJSON(productos);
    }

}
