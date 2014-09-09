package models;

import play.db.jpa.Model;

import javax.persistence.Entity;

/**
 * Created by claudio on 08-09-14.
 */
@Entity
public class Pais extends Model{

    public Integer codNum;

    public String alfaTres;

    public String alfaDos;

    public String nombre;

    public String nacionalidad;


    @Override
    public String toString() {
        return nombre;
    }
}
