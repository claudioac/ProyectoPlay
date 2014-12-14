package models;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;

/**
 * Entidad que representa el tipo de plan contratado por el nuevo cliente y/o cliente ya registrado.
 *
 * @author Claudio Acuña
 */
@Entity
@SequenceGenerator(initialValue = 1, name = "idAutonGen", sequenceName = "seq_tipo_plan")
public class TipoPlan extends EntidadIdAutoLongTipos {

    public String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
