package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

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


    @Override
    public String toString() {
        return nombre;
    }
}
