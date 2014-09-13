package models;

import play.data.validation.Required;
import play.db.jpa.JPA;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

/**
 * Created by claudio on 06-09-14.
 */
@Entity
@SequenceGenerator(initialValue = 1, name = "idAutonGen", sequenceName = "seq_persona")
public class Login extends EntidadIdAutoLongAltKey {

    @Required
    public String usuario;

    @Required
    public String password;

    @ManyToOne
    public TipoUsuario tipoUsuario;


    public static Login checkUsuario(String Usuario){
        return find("usuario",Usuario).first();
    }
}
