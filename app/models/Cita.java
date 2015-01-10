package models;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Claudio Acuña
 */
@Entity
@SequenceGenerator(initialValue = 1, name = "idAutonGen", sequenceName = "seq_cita")
public class Cita extends EntidadIdAutoLongAltKey{

    @ManyToOne
    public Persona profesor;

    @ManyToOne
    public Persona cliente;

    @Temporal(TemporalType.TIMESTAMP)
    public Date fecha;




}
