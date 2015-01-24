package models;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;

/**
 * @autor Claudio Acuña
 */
@Entity
@SequenceGenerator(initialValue = 1, name = "idAutonGen", sequenceName = "seq_estado_cita")
public class EstadoCita extends EntidadIdAutoLong{

    public String estado;
}
