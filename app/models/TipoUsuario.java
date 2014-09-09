package models;

import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.Entity;

/**
 * Created by claudio on 07-09-14.
 */
@Entity
public class TipoUsuario extends Model{

    @Required
    public String tipoUsuario;

    @Override
    public String toString() {
        return tipoUsuario;
    }
}
