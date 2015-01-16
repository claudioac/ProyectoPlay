package models;

import models.ClasesDTO.ClaseDTO;

import javax.persistence.*;
import java.util.ArrayList;
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

    @OneToMany(mappedBy = "clase",cascade = CascadeType.REMOVE)
    public List<Curso> curso;

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

    public List<Curso> getCurso() {
        return curso;
    }

    public void setCurso(List<Curso> curso) {
        this.curso = curso;
    }

    @Override
    public String toString() {
        return  tipoDeClase.tipo;
    }

    public static List<ClaseDTO> findAllClasesActivas() {
        List<Clase> clases = Clase.findAll();
        List<ClaseDTO> resultado = new ArrayList<ClaseDTO>();
        for (Clase clase : clases) {
            resultado.add(clase.toClaseDTO());
        }
        return resultado;
    }

    private ClaseDTO toClaseDTO() {
        ClaseDTO dto = new ClaseDTO();
        dto.altKeyClase = altKey;
        dto.cuposDisponibles = cupos;
        dto.nombreProfesor = profesor.nombres;
        dto.nombreDeClase = tipoDeClase.tipo;
        dto.apellidoMaternoProfesor = profesor.apellidoMaterno;
        dto.apellidoPaternoProfesor = profesor.apellidoPaterno;
        return dto;
    }
}
