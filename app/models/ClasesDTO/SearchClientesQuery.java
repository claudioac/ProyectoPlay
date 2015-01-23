package models.ClasesDTO;

/**
 *@author Claudio Acuña
 */
public class SearchClientesQuery {

    public Integer sexo;
    public String rut;
    public Long region;
    public Long provincia;
    public Long comuna;

    public Integer getSexo() {
        return sexo;
    }

    public void setSexo(Integer sexo) {
        this.sexo = sexo;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
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
}
