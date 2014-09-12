package models;

import play.db.jpa.Model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by claudio on 08-09-14.
 */
@Entity
@SequenceGenerator(name = "profile_seq", sequenceName = "profile_seq")
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

    @OneToOne
    public Login login;



}
