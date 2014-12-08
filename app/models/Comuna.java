package models;

import play.db.jpa.JPA;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.List;

/**
 *@author Claudio Acuña
 *
 * Entidad Comuna
 */
@Entity
public class Comuna extends Model {

    public String nombre;

    @ManyToOne
    public Provincia provincia;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    @Override
    public String toString() {
        return nombre;
    }

    public static List<Comuna> getAllComunas(Long idProvincia) {
        List<Comuna> comunaList = JPA.em().createQuery("Select c from Comuna c where c.provincia.id=?1 order by c.id",Comuna.class).setParameter(1,idProvincia).getResultList();
        return comunaList;
    }
}
