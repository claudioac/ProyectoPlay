package models;



import javax.persistence.*;
import java.util.Date;

/**
 * @author Claudio Acuña
 */
@Entity
@SequenceGenerator(initialValue = 1, name = "idAutonGen", sequenceName = "seq_contrato")
public class Contrato extends EntidadIdAutoLongAltKey{

   @ManyToOne
   public TipoPlan tipoPlan;

   @ManyToOne
   public Persona persona;

   @Temporal(TemporalType.TIMESTAMP)
   public Date fechaCreacion;

   public boolean vigente;

    public TipoPlan getTipoPlan() {
        return tipoPlan;
    }

    public void setTipoPlan(TipoPlan tipoPlan) {
        this.tipoPlan = tipoPlan;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public boolean isVigente() {
        return vigente;
    }

    public void setVigente(boolean vigente) {
        this.vigente = vigente;
    }
}
