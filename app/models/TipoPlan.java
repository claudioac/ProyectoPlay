package models;

import models.ClasesDTO.TipoDePlanes;
import play.db.jpa.JPA;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import java.util.List;

/**
 * Entidad que representa el tipo de plan contratado por el nuevo cliente y/o cliente ya registrado.
 *
 * @author Claudio Acuña
 */
@Entity
@SequenceGenerator(initialValue = 1, name = "idAutonGen", sequenceName = "seq_tipo_plan")
public class TipoPlan extends EntidadIdAutoLongTipos {

    public String nombre;

    public String getNombre() {
        return nombre;
    }

    public Long valorTotalPlan;

    public Long valorMensualidad;

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getValorTotalPlan() {
        return valorTotalPlan;
    }

    public void setValorTotalPlan(Long valorTotalPlan) {
        this.valorTotalPlan = valorTotalPlan;
    }

    public Long getValorMensualidad() {
        return valorMensualidad;
    }

    public void setValorMensualidad(Long valorMensualidad) {
        this.valorMensualidad = valorMensualidad;
    }

    @Override
    public String toString() {
        return nombre;
    }

    public static List<TipoPlan> findAllTipoDePlanActivo() {
        return JPA.em().createQuery("select p from TipoPlan p where p.activo = true",TipoPlan.class).getResultList();
    }

    public static TipoPlan getCoutaDeIncorporacion() {
        TipoPlan cuotaIncorporacion = JPA.em().createQuery("select p from TipoPlan p where p.id=?1",TipoPlan.class).setParameter(1,TipoDePlanes.CUOTA_DE_INCORPORACION).getSingleResult();
        return cuotaIncorporacion;
    }

    public static TipoPlan getValoresDePlan(Long id) {
        TipoPlan valores = JPA.em().createQuery("select p from TipoPlan p where p.id=?1",TipoPlan.class).setParameter(1,id).getSingleResult();
        return valores;
    }
}
