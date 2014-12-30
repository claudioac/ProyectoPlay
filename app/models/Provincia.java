package models;

import play.db.jpa.JPA;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.List;

/**
 * @author Claudio Acuña
 *         <p/>
 *         Entidad Provincia
 */
@Entity
public class Provincia extends Model {

    public String nombre;

    @ManyToOne
    public Region region;

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }

    public static List<Provincia> getAllProvincias(Long idRegion) {
        List<Provincia> provinciaList = JPA.em().createQuery("SELECT p from Provincia p WHERE p.region.id=?1 order by p.id", Provincia.class).setParameter(1, idRegion).getResultList();
        return provinciaList;
    }

    public static Provincia getId(int provincia) {
        Long idProvincia = Long.valueOf(provincia);
        return JPA.em().find(Provincia.class, idProvincia);
    }
}
