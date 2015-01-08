package models;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;

/**
 * @author Claudio Acuña
 */
@Entity
@SequenceGenerator(initialValue = 1, name = "idAutonGen", sequenceName = "seq_tipo_plan")
public class ContratoEstado extends EntidadIdAutoLongTipos {

    public String estado;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
