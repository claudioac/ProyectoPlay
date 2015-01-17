package models;

import models.ClasesDTO.CursosDTO;
import play.db.jpa.JPA;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Claudio Acuña
 */
@Entity
@SequenceGenerator(initialValue = 1, name = "idAutonGen", sequenceName = "seq_curso")
public class Curso extends EntidadIdAutoLongAltKey{

    @ManyToOne
    public Clase clase;

    @Temporal(TemporalType.TIMESTAMP)
    public Date fechaDeCurso;

    @OneToMany
    public List<Persona> clientes;


    public static List<CursosDTO> findAllCursosByAltKeyYFecha(Date fechaDeCurso, String altKeyClase) {
        Clase clase = Clase.find("altKey",altKeyClase).first();
        String oql = "SELECT c FROM Curso c " +
                "WHERE c.clase=?1 ";
        if (fechaDeCurso != null){
            oql += "AND c.fechaDeCurso=?2 ";
        }
        TypedQuery<Curso> query = JPA.em().createQuery(oql.toString(),Curso.class);
        query.setParameter(1,clase);
        if (fechaDeCurso != null){
           query.setParameter(2,fechaDeCurso);
        }
        List<Curso> cursos = query.getResultList();
        List<CursosDTO> resultado = new ArrayList<CursosDTO>();
        for (Curso curso : cursos) {
            resultado.add(curso.toCursosDTO());
        }
        return resultado;
    }

    private CursosDTO toCursosDTO() {
        CursosDTO dto = new CursosDTO();
        dto.clase = clase.tipoDeClase.tipo;
        dto.profesor = clase.profesor.nombres + ' ' + clase.profesor.apellidoPaterno + ' ' + clase.profesor.apellidoMaterno;
        dto.cupos = clase.cupos;
        dto.fecha = fechaDeCurso;
        dto.altKey = altKey;
        return dto;
    }
}
