package models;


import play.db.jpa.JPA;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Claudio Acuña
 */
@Entity
@SequenceGenerator(initialValue = 1, name = "idAutonGen", sequenceName = "seq_contrato")
public class Contrato extends EntidadIdAutoLongAltKey {

    @ManyToOne
    public TipoPlan tipoPlan;

    @ManyToOne(cascade = CascadeType.ALL)
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

    public static Contrato findContratoByAltKeyPersona(String altKey) {
        Persona pers = Persona.findPersonabyAltKey(altKey);
        try{
            Contrato contrato = JPA.em().createQuery("select c from Contrato c where c.persona=?1", Contrato.class).setParameter(1, pers).getSingleResult();
            return contrato;
        }catch (NoResultException e){
            return null;
        }

    }
}
