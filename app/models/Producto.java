package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

/**
 * @author Claudio ACuña
 */
@Entity
@SequenceGenerator(initialValue = 1, name = "idAutonGen", sequenceName = "seq_producto")
public class Producto extends EntidadIdAutoLongAltKey{

    public String nombre;
    public String descripcion;
    public int cantidadDisponible;
    public Long precio;
    public boolean activo;

    @ManyToOne
    public TipoDeProducto tipoDeProducto;
}
