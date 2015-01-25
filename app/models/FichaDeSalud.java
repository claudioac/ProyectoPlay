package models;

import models.ClasesDTO.FichaDeSaludDTO;
import play.db.jpa.JPA;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @ManyToOne
    public EstadosIMC estadosIMC;

    @Lob
    public String antecedentesMedicos;

    public static List<FichaDeSaludDTO> findAllFichasDeSaludByAltKeyCliente(String altKeyCliente) {
        List<FichaDeSalud> fichaDeSalud = JPA.em().createQuery("SELECT f from FichaDeSalud f where f.cliente.altKey=?1 ORDER BY f.fechaDeControl,f.id DESC ",FichaDeSalud.class).setParameter(1,altKeyCliente).getResultList();
        List<FichaDeSaludDTO> resultado = new ArrayList<FichaDeSaludDTO>();
        for (FichaDeSalud salud : fichaDeSalud) {
            resultado.add(salud.toFichaDeSaludDTO());
        }
        return resultado;
    }

    private FichaDeSaludDTO toFichaDeSaludDTO() {
        FichaDeSaludDTO dto = new FichaDeSaludDTO();
        dto.imc = imc;
        if (estadosIMC != null){
            dto.estadoDeCliente = estadosIMC.clasificacion;
        }
        dto.fechaDeControl = fechaDeControl;
        dto.estatura = estatura;
        dto.img = img;
        dto.numeroDeFicha = id;
        dto.problemasDeSalud = antecedentesMedicos;
        dto.peso= peso;
        return dto;
    }
}
