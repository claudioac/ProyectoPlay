package models;



import play.db.jpa.JPA;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author Claudio Acuña
 */

@Entity
@SequenceGenerator(initialValue = 1, name = "idAutonGen", sequenceName = "seq_mensualidad")
public class Mensualidad extends EntidadIdAutoLongAltKey{

    @ManyToOne
    public Contrato contrato;

    @Temporal(TemporalType.TIMESTAMP)
    public Date fechaPago;

    @Temporal(TemporalType.TIMESTAMP)
    public Date fechaVencimiento;

    public Long montoCancelado;

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Long getMontoCancelado() {
        return montoCancelado;
    }

    public void setMontoCancelado(Long montoCancelado) {
        this.montoCancelado = montoCancelado;
    }

    public static List<Mensualidad> mensualidadesByAltkeyCliente(String altKey) {
        Contrato contrato = Contrato.findContratoByAltKeyPersona(altKey);
        List<Mensualidad> mensualidades = JPA.em().createQuery("SELECT m from Mensualidad m where m.contrato=?1",Mensualidad.class).setParameter(1,contrato).getResultList();
        return mensualidades;
    }
}