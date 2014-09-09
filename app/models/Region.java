package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by claudio on 08-09-14.
 */
@Entity
public class Region extends Model{

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
