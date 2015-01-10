package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import java.util.List;

/**
 * @author Claudio Acuña
 */
@Entity
@SequenceGenerator(initialValue = 1, name = "idAutonGen", sequenceName = "seq_curso")
public class Curso extends EntidadIdAutoLongAltKey{

    @ManyToOne
    public Persona profesor;

    @OneToMany
    public List<Persona> clientes;

}
