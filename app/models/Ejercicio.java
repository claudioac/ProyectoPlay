package models;

import models.ClasesDTO.EjercicioDTO;
import play.db.jpa.JPA;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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


    public static List<EjercicioDTO> findAllEjerciciosByAltKeyRutina(String altKeyRutina) {
        List<Ejercicio> ejercicios = JPA.em().createQuery("select e from Ejercicio e  where e.rutina.altKey=?1 order by e.zonasDelCuerpo.zona",Ejercicio.class).setParameter(1,altKeyRutina).getResultList();
        List<EjercicioDTO> resultados = new ArrayList<EjercicioDTO>();
        for (Ejercicio ejercicio : ejercicios) {
            resultados.add(ejercicio.toEjercicioDTO());
        }
        return resultados;
    }

    private EjercicioDTO toEjercicioDTO() {
    EjercicioDTO dto = new EjercicioDTO();
        dto.zonaEjercitada = zonasDelCuerpo.zona;
        dto.nombreDelEjercicio = tipoDeEjercicio.nombre;
        dto.series = series;
        dto.repeticiones = repeticiones;
        dto.tiempo = descanso;
       return dto;
    }
}
