package models;



import play.db.jpa.JPA;

import javax.persistence.*;
import java.util.ArrayList;
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

    public Long numeroBoleta;

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

    public Long getNumeroBoleta() {
        return numeroBoleta;
    }

    public void setNumeroBoleta(Long numeroBoleta) {
        this.numeroBoleta = numeroBoleta;
    }

    public static List<Mensualidad> mensualidadesByAltkeyCliente(String altKey) {
        Contrato contrato = Contrato.findContratoByAltKeyPersona(altKey);
        List<Mensualidad> mensualidades = JPA.em().createQuery("SELECT m from Mensualidad m where m.contrato=?1",Mensualidad.class).setParameter(1,contrato).setMaxResults(12).getResultList();
        return mensualidades;
    }

    public static Mensualidad lastMensualidadByAltkeyCliente(String altKey){
        Contrato contrato = Contrato.findContratoByAltKeyPersona(altKey);
        try {
            Mensualidad mensualidad = JPA.em().createQuery("SELECT m from Mensualidad m where m.contrato=?1 order by m.id desc",Mensualidad.class).setParameter(1,contrato).setMaxResults(1).getSingleResult();
            return mensualidad;
        }catch (NoResultException e){
            return null;
        }

    }

    public static List<models.ClasesDTO.Mensualidad> mensualidadesListDTO(List<Mensualidad> list){
        List<models.ClasesDTO.Mensualidad> dto = new ArrayList<models.ClasesDTO.Mensualidad>();
        for (Mensualidad mensualidad : list) {
            dto.add(mensualidad.toMensualidadDTO());
        }
        return dto;
    }

    private models.ClasesDTO.Mensualidad toMensualidadDTO() {
        models.ClasesDTO.Mensualidad dto = new models.ClasesDTO.Mensualidad();
        dto.fechaPago = fechaPago;
        dto.numeroBoleta = numeroBoleta;
        dto.montoCancelado = montoCancelado;
        dto.fechaVencimiento = fechaVencimiento;
        return dto;
    }
}