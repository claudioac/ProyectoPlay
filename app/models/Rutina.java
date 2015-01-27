package models;

import models.ClasesDTO.RutinaDTO;
import play.db.jpa.JPA;

import javax.persistence.*;
import java.util.ArrayList;
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

    public static List<RutinaDTO> findAllRutinasByAltKeyCliente(String altKeyCliente) {
        List<Rutina> rutinas = JPA.em().createQuery("SELECT r from Rutina r where r.cliente.altKey=?1 order by r.fechaDeRutina DESC ",Rutina.class).setParameter(1,altKeyCliente).getResultList();
        List<RutinaDTO> resultado = new ArrayList<RutinaDTO>();
        for (Rutina rutina : rutinas) {
            resultado.add(rutina.toRutinaDTO());
        }
        return resultado;
    }

    private RutinaDTO toRutinaDTO() {
        RutinaDTO dto = new RutinaDTO();
        dto.altKeyRutina = altKey;
        dto.fechaDeControl = fechaDeRutina;
        dto.numeroDeRutina = id;
        dto.profesor = profesor.getNombreCompleto();
        return dto;
    }

    public static Rutina findRutinaByAltKey(String altKey) {
        try {
            Rutina rutina = JPA.em().createQuery("SELECT r from Rutina r where r.altKey=?1",Rutina.class).setParameter(1,altKey).getSingleResult();
            return rutina;
        } catch (NoResultException e) {
            return null;
        }
    }
}
