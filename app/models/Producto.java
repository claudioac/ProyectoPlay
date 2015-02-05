package models;

import models.ClasesDTO.ProductoDTO;
import play.db.jpa.JPA;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Claudio ACuña
 */
@Entity
@SequenceGenerator(initialValue = 1, name = "idAutonGen", sequenceName = "seq_producto")
public class Producto extends EntidadIdAutoLongAltKey{

    public String nombre;
    @Lob
    public String descripcion;
    public int cantidadDisponible;
    public Long precio;
    public boolean activo;

    @Override
    public String toString() {
        return nombre;
    }

    @ManyToOne
    public TipoDeProducto tipoDeProducto;

    public static List<ProductoDTO> findAllProductos(Long categoria) {
        String oql = "select p from Producto p " +
                "where 1=1 ";
        if (categoria != null){
            oql += " and p.tipoDeProducto.id=?1 ";
        }
        TypedQuery<Producto> query = JPA.em().createQuery(oql.toString(),Producto.class);
        if (categoria != null){
            query.setParameter(1,categoria);
        }
        List<Producto>  productos = query.getResultList();
        List<ProductoDTO> resultado = new ArrayList<ProductoDTO>();
        for (Producto producto : productos) {
            resultado.add(producto.toProductoDTO());
        }
        return resultado;
    }

    private ProductoDTO toProductoDTO(){
        ProductoDTO dto = new ProductoDTO();
        dto.cantidadDisponible = cantidadDisponible;
        dto.categoria = tipoDeProducto.id;
        dto.precio = precio;
        dto.descripcionProducto = descripcion;
        dto.nombreProducto = nombre;
        dto.altKeyProducto = altKey;
        return  dto;
    }
}
