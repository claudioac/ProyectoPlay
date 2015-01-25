package models;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Claudio Acuña
 */
@Entity
@SequenceGenerator(initialValue = 1, name = "idAutonGen", sequenceName = "seq_ejercicio")
public class Ejercicio extends EntidadIdAutoLongAltKey{

    @ManyToOne
    public Rutina rutina;

    @ManyToOne
    public TipoDeEjercicio tipoDeEjercicio;

    @ManyToOne
    public ZonasDelCuerpo zonasDelCuerpo;

    public Integer repeticiones;

    public Integer series;

    @Temporal(TemporalType.TIME)
    public Date descanso;
}
