package models;

import models.ClasesDTO.CitasDTO;
import play.db.jpa.JPA;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author Claudio Acuña
 */
@Entity
@SequenceGenerator(initialValue = 1, name = "idAutonGen", sequenceName = "seq_cita")
public class Cita extends EntidadIdAutoLongAltKey {

    @ManyToOne
    public Persona profesor;

    @ManyToOne
    public Persona cliente;

    @Temporal(TemporalType.TIMESTAMP)
    public Date fecha;


    public static List<CitasDTO> findAllCitasByPersona(Persona usuarioConectado) {
        List<Cita> citas = JPA.em().createQuery("SELECT c from Cita c where c.cliente=?1 and c.fecha>=?2", Cita.class).setParameter(1, usuarioConectado).setParameter(2,new Date()).setMaxResults(12).getResultList();
        List<CitasDTO> result = new ArrayList<CitasDTO>();
        for (Cita cita : citas) {
            result.add(cita.toCitaDTO());
        }
        return result;
    }

    private CitasDTO toCitaDTO() {
        CitasDTO dto = new CitasDTO();
        dto.altKeyCita = altKey;
        dto.nombreProfesor = profesor.getNombreCompleto();
        dto.fechaDeCita = fecha;
        if (profesor.horarios.size() != 0) {
            Horario horario = Horario.findHorarioPersonaByAltKey(profesor.altKey);
            HashMap<String, Date> h = new HashMap<String, Date>();
            if (horario != null) {
                h.put("entrada", horario.horaInicio);
                h.put("salida", horario.horaTermino);
                dto.horarioProfesor = h;
            }
        }
        return dto;
    }

    public static List<Cita> findAllCitasByAltKeyProfesor(String altKey) {
        List<Cita> citas = JPA.em().createQuery("SELECT c from Cita c where c.profesor.altKey=?1",Cita.class).setParameter(1,altKey).getResultList();
        return citas;
    }
}
