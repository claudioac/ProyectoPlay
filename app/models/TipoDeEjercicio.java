package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

/**
 * @author Claudio Acuña
 */
@Entity
@SequenceGenerator(initialValue = 1, name = "idAutonGen", sequenceName = "seq_tipo_de_ejercicio")
public class TipoDeEjercicio extends EntidadIdAutoLongTipos {

    public String nombre;

    @ManyToOne
    public ZonasDelCuerpo zonasDelCuerpo;

    @Override
    public String toString() {
        return nombre;
    }
}
