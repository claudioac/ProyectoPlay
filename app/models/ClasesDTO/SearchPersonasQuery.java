package models.ClasesDTO;

/**
 * @author Claudio Acuña
 */
public class SearchPersonasQuery {

    public String contiene;
    public Long region;
    public Long provincia;
    public Long comuna;
    public Integer sexo;
    public Long tipoUsuario;

    public String getContiene() {
        return contiene;
    }

    public void setContiene(String contiene) {
        this.contiene = contiene;
    }

    public Long getRegion() {
        return region;
    }

    public void setRegion(Long region) {
        this.region = region;
    }

    public Long getProvincia() {
        return provincia;
    }

    public void setProvincia(Long provincia) {
        this.provincia = provincia;
    }

    public Long getComuna() {
        return comuna;
    }

    public void setComuna(Long comuna) {
        this.comuna = comuna;
    }

    public Integer getSexo() {
        return sexo;
    }

    public void setSexo(Integer sexo) {
        this.sexo = sexo;
    }

    public Long getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(Long tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
