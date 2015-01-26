package models;

import play.db.jpa.JPA;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import java.util.List;

/**
 * @author Claudio Acuña
 */
@Entity
@SequenceGenerator(initialValue = 1, name = "idAutonGen", sequenceName = "seq_tipo_de_ejercicio")
public class TipoDeEjercicio extends EntidadIdAutoLongTipos {

    public String nombre;

    @ManyToOne
    public ZonasDelCuerpo zonasDelCuerpo;

    @Override
    public String toString() {
        return nombre;
    }

    public static List<TipoDeEjercicio> findAllByIdZonaDelCuerpo(Long idZona) {
        List<TipoDeEjercicio> tipoDeEjercicios = JPA.em().createQuery("select t from TipoDeEjercicio t where t.zonasDelCuerpo.id=?1", TipoDeEjercicio.class).setParameter(1, idZona).getResultList();
        return tipoDeEjercicios;
    }
}
