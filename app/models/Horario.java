package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

/**
 * @author Claudio Acuña
 */
@Entity
@SequenceGenerator(initialValue = 1,name = "idAutonGen", sequenceName = "seq_horario")
public class Horario extends EntidadIdAutoLongAltKey {

   @ManyToOne
   public Persona persona;

}
