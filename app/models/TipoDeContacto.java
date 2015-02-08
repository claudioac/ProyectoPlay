package models;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;

/**
 * @author Claudio Acuña
 */
@Entity
@SequenceGenerator(initialValue = 1, name = "idAutonGen", sequenceName = "seq_tipo_de_contacto")
@Cacheable(true)
public class TipoDeContacto extends EntidadIdAutoLongTipos {

    public String tipo;
}
