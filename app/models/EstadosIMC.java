package models;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;

/**
 *@author Claudio Acuña
 */
@Entity
@SequenceGenerator(initialValue = 1, name = "idAutonGen", sequenceName = "seq_estado_imc")
public class EstadosIMC extends EntidadIdAutoLong {

    public float indice;
    public String clasificacion;
}
