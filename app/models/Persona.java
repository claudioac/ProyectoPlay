package models;

import controllers.variblesEstaticas.TipoUsuariosDTO;
import play.data.validation.Required;
import play.db.jpa.JPA;
import play.db.jpa.Model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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

    @OneToOne
    public Usuario usuario;

    @ManyToOne
    public TipoUsuario tipoUsuario;

    /**
     * Setter y Getters de atributos
     * @return
     */

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getGenero() {
        return genero;
    }

    public void setGenero(int genero) {
        this.genero = genero;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Comuna getComuna() {
        return comuna;
    }

    public void setComuna(Comuna comuna) {
        this.comuna = comuna;
    }

    public Pais getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(Pais nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }


    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    /**
     * Funciones JPA
     */
    public static Persona checkUsuario(String Usuario){
          Persona usuario = JPA.em().createQuery("Select p from Persona p where p.usuario.usuario=?1",Persona.class).setParameter(1,Usuario).getSingleResult();
        return usuario;
    }

    public static Persona findPersonabyAltKey(String altKey){
        return find("altKey",altKey).first();
    }

    public static Persona findPersonaByRut(String rut){
        return find("rut",rut).first();
    }

    public static List<Persona> getAllPersonas(){
        return findAll();
    }

    public static List<Persona> getAllClientes(){
        return JPA.em().createQuery("Select p from Persona p where p.tipoUsuario.id =?1").setParameter(1, TipoUsuariosDTO.IdUsuario).getResultList();
    }

    public static List<Persona> getAllProfesores() {
        return JPA.em().createQuery("SELECT p from Persona p where p.tipoUsuario.id =?1").setParameter(1,TipoUsuariosDTO.IdProfesor).getResultList();
    }
}
