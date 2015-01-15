package models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author Claudio Acuña
 */
@Entity
@SequenceGenerator(initialValue = 1, name = "idAutonGen", sequenceName = "seq_curso")
public class Curso extends EntidadIdAutoLongAltKey{

    @Temporal(TemporalType.TIMESTAMP)
    public Date fechaDeCurso;

    @OneToMany
    public List<Persona> clientes;

}
