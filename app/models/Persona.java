package models;

import controllers.variblesEstaticas.TipoUsuariosDTO;
import models.ClasesDTO.PersonaDTO;
import play.db.jpa.JPA;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Claudio Acuña
 *         <p/>
 *         <p/>
 *         Entidad Persona
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

    @Temporal(TemporalType.TIMESTAMP)
    public Date fechaDeIncorporacion;

    /**
     * Genero 0.- Masculino
     * 1.- Femenino
     */
    public int genero;

    public String direccion;

    @ManyToOne(fetch = FetchType.LAZY)
    public Region region;

    @ManyToOne(fetch = FetchType.LAZY)
    public Provincia provincia;

    @ManyToOne(fetch = FetchType.LAZY)
    public Comuna comuna;

    @ManyToOne
    public Pais nacionalidad;

    public String telefono;

    public String celular;

    @OneToOne(cascade = {CascadeType.ALL})
    public Usuario usuario;

    @ManyToOne
    public TipoUsuario tipoUsuario;

    @OneToMany(mappedBy = "persona")
    public List<Horario> horarios;


    /**
     * Setter y Getters de atributos
     *
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

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getFechaDeIncorporacion() {
        return fechaDeIncorporacion;
    }

    public void setFechaDeIncorporacion(Date fechaDeIncorporacion) {
        this.fechaDeIncorporacion = fechaDeIncorporacion;
    }

    /**
     * Funciones JPA
     */
    public static Persona checkUsuario(String Usuario) {
        Persona usuario = JPA.em().createQuery("Select p from Persona p where p.usuario.usuario=?1", Persona.class).setParameter(1, Usuario).getSingleResult();
        return usuario;
    }

    public static Persona findPersonabyAltKey(String altKey) {
        Persona persona = find("altKey", altKey).first();
        return persona;
    }


    public static PersonaDTO findPersonaDTObyAltKey(String altKey) {
        Persona persona = find("altKey", altKey).first();
        return persona.toPersonaDTO();
    }


    public static Persona findPersonaByRut(String rut) {
        return find("rut", rut).first();
    }

    public static List<PersonaDTO> getAllPersonas() {
        List<Persona> persona = JPA.em().createQuery("Select p from Persona p").getResultList();
        List<PersonaDTO> resultado = new ArrayList<PersonaDTO>();
        for (Persona usr : persona) {
            resultado.add(usr.toPersonaDTO());
        }
        return resultado;
    }

    public static List<PersonaDTO> getAllClientes() {
        List<Persona> persona = JPA.em().createQuery("Select p from Persona p where p.tipoUsuario.id =?1").setParameter(1, TipoUsuariosDTO.IdUsuario).getResultList();
        List<PersonaDTO> resultado = new ArrayList<PersonaDTO>();
        for (Persona usr : persona) {
            resultado.add(usr.toPersonaDTO());
        }
        return resultado;
    }

    public static List<PersonaDTO> getAllProfesores() {
        List<Persona> persona = JPA.em().createQuery("SELECT p from Persona p where p.tipoUsuario.id =?1").setParameter(1, TipoUsuariosDTO.IdProfesor).getResultList();
        List<PersonaDTO> resultado = new ArrayList<PersonaDTO>();
        for (Persona usr : persona) {
            resultado.add(usr.toPersonaDTO());
        }
        return resultado;
    }

    public PersonaDTO toPersonaDTO() {
        PersonaDTO dto = new PersonaDTO();
        dto.altKey = altKey;
        dto.setNombres(this.nombres);
        dto.setApellidoMaterno(this.apellidoMaterno);
        dto.setApellidoPaterno(this.apellidoPaterno);
        dto.setCelular(this.celular);
        dto.setTelefono(this.telefono);
        if (region != null){
            dto.setRegion(this.region.getId().intValue());
        }
        if (provincia != null){
            dto.setProvincia(this.provincia.getId().intValue());
        }
        dto.setComuna(this.comuna.getId().intValue());
        dto.setEmail(this.usuario.email);
        dto.setDireccion(this.direccion);
        dto.setGenero(this.genero);
        dto.setRut(this.rut);
        return dto;
    }
}
