package models;

import javax.persistence.*;
import java.util.List;

/**
 * @author Claudio Acuña
 */
@Entity
@SequenceGenerator(initialValue = 1, name = "idAutonGen", sequenceName = "seq_clase")
public class Clase extends EntidadIdAutoLongAltKey {

    @OneToMany(mappedBy = "clase",cascade = CascadeType.REMOVE)
    public List<Horario> horarios;

    @ManyToOne
    public TipoDeClase tipoDeClase;

    @ManyToOne
    public Curso curso;


}
