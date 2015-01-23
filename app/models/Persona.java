package models;

import controllers.variblesEstaticas.TipoUsuariosDTO;
import models.ClasesDTO.PersonaDTO;
import models.ClasesDTO.SearchPersonalQuery;
import models.ClasesDTO.SearchPersonasQuery;
import models.ClasesDTO.UsuarioConectado;
import org.apache.commons.lang.StringUtils;
import play.db.jpa.JPA;

import javax.persistence.*;
import java.util.*;

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

    @OneToMany(mappedBy = "persona", cascade = {CascadeType.REMOVE})
    public List<Horario> horarios;

    @OneToMany(mappedBy = "cliente", cascade = {CascadeType.REMOVE})
    public List<Rutina> rutinas;

    @OneToMany(mappedBy = "cliente",cascade = {CascadeType.REMOVE})
    public List<FichaDeSalud> fichaDeSalud;


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

    public String getNombreCompleto() {
        String nombreCompleto;
        nombreCompleto = nombres + ' ' + apellidoPaterno + ' ' + apellidoMaterno;
        return nombreCompleto;
    }

    /**
     * Funciones JPA
     */
    public static Persona checkUsuario(String Usuario) {
        try {
            Persona usuario = JPA.em().createQuery("Select p from Persona p where p.usuario.usuario=?1", Persona.class).setParameter(1, Usuario).getSingleResult();
            return usuario;
        } catch (NoResultException e) {
            return null;
        }
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

    public static List<PersonaDTO> getAllPersonas(SearchPersonasQuery personas) {
        String oql = "SELECT p " +
                "FROM Persona p " +
                "WHERE 1=1 ";
        if (personas != null && personas.getSexo() != null) {
            oql += "AND p.genero =?1 ";
        }
        if (personas != null && personas.getTipoUsuario() != null) {
            oql += "AND p.tipoUsuario.id =?2 ";
        }
        if (personas != null && personas.getComuna() != null) {
            oql += "AND p.comuna.id =?3 ";
        }
        if (personas != null && personas.getRegion() != null) {
            oql += "AND p.region.id =?4 ";
        }
        if (personas != null && personas.getProvincia() != null) {
            oql += "AND p.provincia.id =?5 ";
        }
        if (personas != null && personas.getContiene() != null) {
            oql += "AND CONCAT(p.rut, ' ',p.nombres, ' ',p.apellidoPaterno, ' ',p.apellidoMaterno ) like :contiene ";
        }
        TypedQuery<Persona> query = JPA.em().createQuery(oql.toString(), Persona.class);
        if (personas != null && personas.getSexo() != null) {
            query.setParameter(1, personas.getSexo());
        }
        if (personas != null && personas.getTipoUsuario() != null) {
            query.setParameter(2, personas.getTipoUsuario());
        }
        if (personas != null && personas.getComuna() != null) {
            query.setParameter(3, personas.getComuna());
        }
        if (personas != null && personas.getRegion() != null) {
            query.setParameter(4, personas.getRegion());
        }
        if (personas != null && personas.getProvincia() != null) {
            query.setParameter(5, personas.getProvincia());
        }
        if (personas != null && personas.getContiene() != null) {
            query.setParameter("contiene", personas.getContiene() + "%");
        }
        //List<Persona> persona = JPA.em().createQuery("Select p from Persona p").getResultList();
        List<Persona> persona = query.getResultList();
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

    public static List<PersonaDTO> getAllProfesores(SearchPersonalQuery personal) {

        String oql = "Select p " +
                "From Persona p " +
                "Where p.tipoUsuario.id !=?3 ";
        if (personal != null && personal.getSexo() != null) {
            oql += "AND p.genero =?1 ";
        }
        if (personal != null && personal.getTipoUsuario() != null) {
            oql += "AND p.tipoUsuario.id =?2 ";
        }
        if (personal != null && StringUtils.isNotBlank(personal.getRut())) {
            oql += "AND p.rut like :rut";
        }
        TypedQuery<Persona> query = JPA.em().createQuery(oql.toString(), Persona.class);
        if (personal != null && personal.getSexo() != null) {
            query.setParameter(1, personal.getSexo());
        }
        if (personal != null && personal.getTipoUsuario() != null) {
            query.setParameter(2, personal.getTipoUsuario());
        }
        if (personal != null && StringUtils.isNotBlank(personal.getRut())) {
            query.setParameter("rut", personal.getRut() + "%");
        }
        query.setParameter(3, TipoUsuariosDTO.IdUsuario);
        List<Persona> persona = query.getResultList();

        //List<Persona> persona = JPA.em().createQuery("SELECT p from Persona p where p.tipoUsuario.id in (?1,?2) ").setParameter(1, TipoUsuariosDTO.IdProfesor).setParameter(2,TipoUsuariosDTO.IdVendedor).getResultList();
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
        if (region != null) {
            dto.setRegion(this.region.getId().intValue());
        }
        if (provincia != null) {
            dto.setProvincia(this.provincia.getId().intValue());
        }
        dto.setComuna(this.comuna.getId().intValue());
        dto.setEmail(this.usuario.email);
        dto.setDireccion(this.direccion);
        dto.setGenero(this.genero);
        dto.setRut(this.rut);
        dto.nombreCompleto = getNombreCompleto();
        if (horarios.size() != 0) {
            Horario horario = Horario.findHorarioPersonaByAltKey(altKey);
            if (horario != null) {
                HashMap<String,Date> h = new HashMap<String, Date>();
                h.put("entrada",horario.horaInicio);
                h.put("salida",horario.horaTermino);
                dto.setHorario(h);
            }
        }
        return dto;
    }

    public static List<Persona> findAllProfesores() {
        List<Persona> profesores = JPA.em().createQuery("SELECT p from Persona p where p.tipoUsuario.id=?1", Persona.class).setParameter(1, TipoUsuariosDTO.IdProfesor).getResultList();
        return profesores;
    }

    public static UsuarioConectado setUsuarioConectado(Persona login) {
        UsuarioConectado usuario = new UsuarioConectado();
        usuario.email = login.usuario.email;
        usuario.idTipoUsuario = login.tipoUsuario.id;
        usuario.nombreCompleto = login.nombres + ' ' + login.apellidoPaterno + ' ' + login.apellidoMaterno;
        usuario.tipoUsuario = login.tipoUsuario.tipoUsuario;
        return usuario;
    }

    public static List<PersonaDTO> findAllProfesoresParaCitas() {
        List<Persona> profesores = findAllProfesores();
        List<PersonaDTO> result = new ArrayList<PersonaDTO>();
        for (Persona profesor : profesores) {
            result.add(profesor.toPersonaDTO());
        }
        return result;
    }
}
