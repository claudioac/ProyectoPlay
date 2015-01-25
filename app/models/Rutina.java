package models;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author Claudio Acuña
 */
@Entity
@SequenceGenerator(initialValue = 1, name = "idAutonGen", sequenceName = "seq_rutina")
public class Rutina extends EntidadIdAutoLongAltKey{

    @ManyToOne
    public Persona profesor;

    @ManyToOne
    public Persona cliente;

    @Temporal(TemporalType.TIMESTAMP)
    public Date fechaDeRutina;

    @OneToMany(mappedBy = "rutina",cascade = {CascadeType.REMOVE})
    public List<Ejercicio> ejercicios;

}
