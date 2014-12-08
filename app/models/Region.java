package models;

import play.db.jpa.JPA;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.List;

/**
 *@author Claudio Acuña
 *
 * Entidad Región
 */
@Entity
public class Region extends Model {

    public String nombre;

    public String corfo;

    public String codigo;

    public Integer numero;

    @ManyToOne
    public Pais pais;


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorfo() {
        return corfo;
    }

    public void setCorfo(String corfo) {
        this.corfo = corfo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    @Override
    public String toString() {
        return nombre;
    }

    public static List<Region> getAllRegiones() {
        List<Region> regionesList = JPA.em().createQuery("SELECT r from Region r order by r.id",Region.class).getResultList();
        return regionesList;
    }
}
