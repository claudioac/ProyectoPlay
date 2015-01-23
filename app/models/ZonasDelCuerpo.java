package models;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;

/**
 * @author Claudio Acuña
 */
@Entity
@SequenceGenerator(initialValue = 1, name = "idAutonGen", sequenceName = "seq_zonas_del_cuerpo")
public class ZonasDelCuerpo extends EntidadIdAutoLong {

    public String zona;
}
