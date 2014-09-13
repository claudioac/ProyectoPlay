package models;

import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;

/**
 * Created by claudio on 07-09-14.
 */
@Entity
@SequenceGenerator(initialValue = 1, name = "idAutonGen", sequenceName = "seq_tipo_usuario")
public class TipoUsuario extends EntidadIdAutoLongAltKey{

    @Required
    public String tipoUsuario;

    @Override
    public String toString() {
        return tipoUsuario;
    }
}
