package models;

import play.data.validation.Required;
import play.db.jpa.JPA;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;

/**
 *@author Claudio Acuña
 *
 *
 * Entidad Tipo Usuario
 */
@Entity
@SequenceGenerator(initialValue = 1, name = "idAutonGen", sequenceName = "seq_tipo_usuario")
public class TipoUsuario extends EntidadIdAutoLongAltKey {

    @Required
    public String tipoUsuario;

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    @Override
    public String toString() {
        return tipoUsuario;
    }

    public static TipoUsuario getById(Long idUsuario) {
        return JPA.em().find(TipoUsuario.class,idUsuario);
    }
}
