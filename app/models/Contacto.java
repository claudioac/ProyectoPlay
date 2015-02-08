package models;

import models.ClasesDTO.ContactoDTO;
import models.ClasesDTO.EstadoContactoDTO;
import play.db.jpa.JPA;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Claudio Acuña
 */
@Entity
@SequenceGenerator(initialValue = 1, name = "idAutonGen", sequenceName = "seq_contacto")
public class Contacto extends EntidadIdAutoLongAltKey {

    @Temporal(TemporalType.TIMESTAMP)
    public Date fechaDeContacto;

    @Lob
    public String asunto;

    @Lob
    public String mensaje;

    public String email;
    @ManyToOne
    public TipoDeContacto tipoDeContacto;
    @ManyToOne
    public EstadoContacto estado;

    @ManyToOne(fetch = FetchType.LAZY)
    public Persona cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    public Persona responsable;

    @Lob
    public String respuesta;

    public Date getFechaDeContacto() {
        return fechaDeContacto;
    }

    public void setFechaDeContacto(Date fechaDeContacto) {
        this.fechaDeContacto = fechaDeContacto;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TipoDeContacto getTipoDeContacto() {
        return tipoDeContacto;
    }

    public void setTipoDeContacto(TipoDeContacto tipoDeContacto) {
        this.tipoDeContacto = tipoDeContacto;
    }

    public EstadoContacto getEstado() {
        return estado;
    }

    public void setEstado(EstadoContacto estado) {
        this.estado = estado;
    }

    public Persona getCliente() {
        return cliente;
    }

    public void setCliente(Persona cliente) {
        this.cliente = cliente;
    }

    public Persona getResponsable() {
        return responsable;
    }

    public void setResponsable(Persona responsable) {
        this.responsable = responsable;
    }

    public static List<ContactoDTO> findAllContactosByIdCliente(Persona cliente) {
        List<Contacto> contactos = JPA.em().createQuery("select c from Contacto c where c.cliente=?1",Contacto.class).setParameter(1,cliente).getResultList();
        return Contacto.toContactoListDTO(contactos);
    }

    private ContactoDTO toContactoDTO() {
        ContactoDTO dto = new ContactoDTO();
        dto.mensaje = mensaje;
        dto.altKeyContacto = altKey;
        dto.asunto = asunto;
        dto.email = email;
        dto.fechaDeContacto = fechaDeContacto;
        dto.estadoContacto = estado.estado;
        return dto;
    }

    private static List<ContactoDTO> toContactoListDTO(List<Contacto> contactos){
        List<ContactoDTO> resultado = new ArrayList<ContactoDTO>();
        for (Contacto contacto : contactos) {
            resultado.add(contacto.toContactoDTO());
        }
        return resultado;
    }

    public static List<ContactoDTO> findAllContactosSinRevisar() {
        List<Contacto> contactos = JPA.em().createQuery("select c from Contacto c where c.estado.id=?1",Contacto.class).setParameter(1, EstadoContactoDTO.ENVIADO).getResultList();
        return Contacto.toContactoListDTO(contactos);
    }
}
