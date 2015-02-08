package models;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;

/**
 * @author Claudio Acuña
 */
@Entity
@SequenceGenerator(initialValue = 1, name = "idAutonGen", sequenceName = "seq_estado_de_contacto")
@Cacheable(true)
public class EstadoContacto extends EntidadIdAutoLongTipos {

    public String estado;
}
