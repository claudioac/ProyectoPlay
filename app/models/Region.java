package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

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

    @Override
    public String toString() {
        return nombre;
    }
}
