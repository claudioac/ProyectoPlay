package models;

import play.db.jpa.JPA;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author Claudio Acuña
 */
@Entity
@SequenceGenerator(initialValue = 1,name = "idAutonGen", sequenceName = "seq_horario")
public class Horario extends EntidadIdAutoLong {

   @ManyToOne
   public Persona persona;

   @ManyToOne
   public Clase clase;


   @Temporal(TemporalType.TIMESTAMP)
   public Date fecha;

   @Temporal(TemporalType.TIME)
   public Date horaInicio;

   @Temporal(TemporalType.TIME)
   public Date horaTermino;


    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraTermino() {
        return horaTermino;
    }

    public void setHoraTermino(Date horaTermino) {
        this.horaTermino = horaTermino;
    }

    public static List<Horario> findListHorarioByAltkeyPersona(String altKey) {
        Persona persona = Persona.findPersonabyAltKey(altKey);
        List<Horario> horario = JPA.em().createQuery("SELECT h from Horario h where h.persona=?1 order by h.id desc").setParameter(1,persona).setMaxResults(1).getResultList();
        return horario;
    }

    public static Horario findHorarioPersonaByAltKey(String altKey) {
        Persona persona = Persona.findPersonabyAltKey(altKey);
        try{
            Horario horario = JPA.em().createQuery("SELECT h from Horario h where h.persona=?1 order by h.id desc",Horario.class).setParameter(1,persona).setMaxResults(1).getSingleResult();
            return horario;
        }catch (NoResultException e){
            return null;
        }
    }
}
