package models;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Claudio Acuña
 */
@Entity
@SequenceGenerator(initialValue = 1,name = "idAutonGen", sequenceName = "seq_horario")
public class Horario extends EntidadIdAutoLong {

   @ManyToOne
   public Persona persona;

   @Temporal(TemporalType.TIME)
   public Date horaInicio;

   @Temporal(TemporalType.TIME)
   public Date horaTermino;

}
