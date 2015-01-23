package models;

import javax.persistence.*;
import java.util.Date;

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

    @ManyToOne
    public TipoDeEjercicio tipoDeEjercicio;

    @ManyToOne
    public ZonasDelCuerpo zonasDelCuerpo;

    public int repeticiones;

    public int series;

    @Temporal(TemporalType.TIME)
    public Date descanso;

}
