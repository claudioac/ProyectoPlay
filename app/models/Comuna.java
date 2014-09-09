package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by claudio on 08-09-14.
 */
@Entity
public class Comuna  extends Model {

    public String nombre;

    @ManyToOne
    public  Provincia provincia;


    @Override
    public String toString() {
        return nombre;
    }
}
