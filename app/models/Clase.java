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
    public Persona profesor;

    public int cupos;

    @ManyToOne
    public TipoDeClase tipoDeClase;

    @ManyToOne
    public Curso curso;

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }

    public Persona getProfesor() {
        return profesor;
    }

    public void setProfesor(Persona profesor) {
        this.profesor = profesor;
    }

    public int getCupos() {
        return cupos;
    }

    public void setCupos(int cupos) {
        this.cupos = cupos;
    }

    public TipoDeClase getTipoDeClase() {
        return tipoDeClase;
    }

    public void setTipoDeClase(TipoDeClase tipoDeClase) {
        this.tipoDeClase = tipoDeClase;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
