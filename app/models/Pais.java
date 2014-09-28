package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;

/**
 *@author Claudio Acuña
 *
 *
 * Entidad Pais
 */
@Entity
@SequenceGenerator(name = "idAutoGen", sequenceName = "seq_tipo_usuario")
public class Pais extends Model {

    public Integer codNum;

    public String alfaTres;

    public String alfaDos;

    public String nombre;

    public String nacionalidad;


    @Override
    public String toString() {
        return nacionalidad;
    }
}
