package models.ClasesDTO;


import java.util.Date;

/**
 * @author Claudio Acuña
 */
public class Mensualidad {
    public Long montoCancelado;
    public Date fechaPago;
    public Date fechaVencimiento;
    public Long numeroBoleta;

    public Long getMontoCancelado() {
        return montoCancelado;
    }

    public void setMontoCancelado(Long montoCancelado) {
        this.montoCancelado = montoCancelado;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Long getNumeroBoleta() {
        return numeroBoleta;
    }

    public void setNumeroBoleta(Long numeroBoleta) {
        this.numeroBoleta = numeroBoleta;
    }
}
