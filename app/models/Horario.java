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

   @Temporal(TemporalType.TIMESTAMP)
   public Date fecha;

   @Temporal(TemporalType.TIME)
   public Date horaInicio;

   @Temporal(TemporalType.TIME)
   public Date horaTermino;


    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraTermino() {
        return horaTermino;
    }

    public void setHoraTermino(Date horaTermino) {
        this.horaTermino = horaTermino;
    }
}
