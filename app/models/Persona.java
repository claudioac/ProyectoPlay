package models;

import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Claudio Acuña
 *
 *
 * Entidad Persona
 */
@Entity
@SequenceGenerator(initialValue = 1, name = "idAutonGen", sequenceName = "seq_login")
public class Persona extends EntidadIdAutoLongAltKey {

    public String nombres;

    public String apellidoPaterno;

    public String apellidoMaterno;

    public String rut;

    @Temporal(TemporalType.TIMESTAMP)
    public Date fechaNacimiento;

    public int genero;

    public String direccion;

    @ManyToOne
    public Comuna comuna;

    @ManyToOne
    public Pais nacionalidad;

    public String telefono;

    public String celular;

    public String email;


    @Required
    public String usuario;

    @Required
    public String password;

    @ManyToOne
    public TipoUsuario tipoUsuario;

    public static Persona checkUsuario(String Usuario){
        return find("usuario",Usuario).first();
    }

    public static Persona findPersonabyAltKey(String altKey){
        return find("altKey",altKey).first();
    }
}
