package models;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;

/**
 * @author Claudio Acuña
 */
@Entity
@SequenceGenerator(initialValue = 1, name = "idAutonGen", sequenceName = "seq_tipo_de_ejercicio")
public class TipoDeEjercicio extends EntidadIdAutoLongTipos {

    public String nombre;

}
