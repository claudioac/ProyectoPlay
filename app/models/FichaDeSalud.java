package models;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Claudio Acuña
 */
@Entity
@SequenceGenerator(initialValue = 1, name = "idAutonGen", sequenceName = "seq_ficha_de_salud")
public class FichaDeSalud extends EntidadIdAutoLongAltKey {

    @ManyToOne
    public Persona profesor;

    @ManyToOne
    public Persona cliente;

    @Temporal(TemporalType.TIMESTAMP)
    public Date fechaDeControl;

    public Float estatura;

    public Float peso;

    public Float imc;

    public Float img;

}
