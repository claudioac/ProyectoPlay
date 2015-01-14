package models;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;

/**
 * @author Claudio Acuña
 */
@Entity
@SequenceGenerator(initialValue = 1, name = "idAutonGen", sequenceName = "seq_tipo_de_clase")
public class TipoDeClase extends EntidadIdAutoLongTipos {

    public String tipo;

    @Override
    public String toString() {
        return tipo;
    }
}
