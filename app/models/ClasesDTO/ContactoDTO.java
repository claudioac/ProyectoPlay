package models.ClasesDTO;

import java.util.Date;

/**
 * @author Claudio Acuña
 */
public class ContactoDTO {


    public String email;
    public String mensaje;
    public Long idTipoDeContacto;
    public String asunto;
    public String altKeyContacto;
    public Date fechaDeContacto;
    public String estadoContacto;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Long getIdTipoDeContacto() {
        return idTipoDeContacto;
    }

    public void setIdTipoDeContacto(Long idTipoDeContacto) {
        this.idTipoDeContacto = idTipoDeContacto;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getAltKeyContacto() {
        return altKeyContacto;
    }

    public void setAltKeyContacto(String altKeyContacto) {
        this.altKeyContacto = altKeyContacto;
    }

    public Date getFechaDeContacto() {
        return fechaDeContacto;
    }

    public void setFechaDeContacto(Date fechaDeContacto) {
        this.fechaDeContacto = fechaDeContacto;
    }

    public String getEstadoContacto() {
        return estadoContacto;
    }

    public void setEstadoContacto(String estadoContacto) {
        this.estadoContacto = estadoContacto;
    }
}
