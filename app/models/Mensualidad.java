package models;



import javax.persistence.*;
import java.util.Date;

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
}
