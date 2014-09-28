package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *@author Claudio Acuña
 *
 * Entidad Provincia
 */
@Entity
public class Provincia extends Model {

    public String nombre;

    @ManyToOne
    public Region region;

    @Override
    public String toString() {
        return nombre;
    }
}
