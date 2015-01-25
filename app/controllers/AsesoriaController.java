package controllers;

import controllers.variblesEstaticas.TipoUsuariosDTO;
import models.*;
import models.ClasesDTO.CitaEstadoDTO;
import models.ClasesDTO.EstadoIMCDTO;
import models.ClasesDTO.FichaDeSaludDTO;
import models.error.ErrorJSON;
import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import play.data.validation.Validation;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.With;

import java.util.Date;
import java.util.List;

/**
 * @author Claudio Acuña
 */
@With(Autenticar.class)
public class AsesoriaController extends Controller {

    public static void cancelarCitaReservadaParaAsesoria(String altKeyCita){
        Cita cita = Cita.find("altKey",altKeyCita).first();
        cita.delete();
    }

    public static void asesorarCliente(String altKeyCita, String altKeyCliente){
        Persona cliente = Persona.findPersonabyAltKey(altKeyCliente);
        List<FichaDeSaludDTO> fichas = FichaDeSalud.findAllFichasDeSaludByAltKeyCliente(altKeyCliente);
        Cita cita = Cita.find("altKey",altKeyCita).first();
        if (cita != null){
            cita.estado = EstadoCita.findById(CitaEstadoDTO.EN_PROCESO);
            cita.save();
        }
        if (session.get("tipo").equals(TipoUsuariosDTO.PROFESOR)){
            renderTemplate("InicioProfesor/asesorarClienteProfesor.html",cliente,fichas);
        }
    }

    public static void nuevaFichaDeSalud(String altKeyCliente){
        Persona cliente = Persona.findPersonabyAltKey(altKeyCliente);
        LocalDate fechaNacimiento = new LocalDate(cliente.fechaNacimiento);
        LocalDate fechaActual = new LocalDate();
        Period edad = new Period(fechaNacimiento,fechaActual, PeriodType.yearMonthDay());
        if (session.get("tipo").equals(TipoUsuariosDTO.PROFESOR)){
            renderTemplate("InicioProfesor/nuevaFichaDeSalud.html",cliente,edad);
        }
        if (session.get("tipo").equals(TipoUsuariosDTO.ADMINISTRATIVO)){
            renderTemplate("InicioAdministrativo/nuevaFichaDeSalud.html",cliente,edad);
        }
        if (session.get("tipo").equals(TipoUsuariosDTO.ADMIN)){
            renderTemplate("InicioAdmin/nuevaFichaDeSalud.html",cliente,edad);
        }
    }

    public static void guardarFichaDeSalud(FichaDeSaludDTO ficha){
        Validation.required("ficha.estatura",ficha.estatura);
        Validation.required("ficha.imc",ficha.imc);
        Validation.required("ficha.img",ficha.img);
        Validation.required("ficha.problemasDeSalud",ficha.problemasDeSalud);
        Validation.required("ficha.peso",ficha.peso);
        if (Validation.hasErrors()) {
            response.status = Http.StatusCode.BAD_REQUEST;
            renderJSON(ErrorJSON.fromValidation());
        }
        FichaDeSalud fichaDeSalud = new FichaDeSalud();
        fichaDeSalud.cliente = Persona.findPersonabyAltKey(ficha.altKeyCliente);
        fichaDeSalud.antecedentesMedicos = ficha.problemasDeSalud;
        fichaDeSalud.estatura = ficha.estatura;
        fichaDeSalud.imc = ficha.imc;
        fichaDeSalud.img = ficha.img;
        fichaDeSalud.peso = ficha.peso;
        fichaDeSalud.fechaDeControl = new Date();
        fichaDeSalud.profesor = Persona.findPersonabyAltKey(session.get("altKey"));

        if (ficha.imc < EstadoIMCDTO.MEDIDA_DELGADEZ_SEVERA){
            fichaDeSalud.estadosIMC = EstadosIMC.findById(EstadoIMCDTO.DELGADEZ_SEVERA);
        }
        if (ficha.imc> EstadoIMCDTO.MEDIDA_DELGADEZ_SEVERA && ficha.imc<EstadoIMCDTO.MEDIDA_DELGADEZ_MODERADA){
            fichaDeSalud.estadosIMC = EstadosIMC.findById(EstadoIMCDTO.DELGADEZ_MODERADA);
        }
        if (ficha.imc> EstadoIMCDTO.MEDIDA_DELGADEZ_MODERADA && ficha.imc<EstadoIMCDTO.MEDIDA_DELGADEZ_ACEPTABLE){
            fichaDeSalud.estadosIMC = EstadosIMC.findById(EstadoIMCDTO.MEDIDA_DELGADEZ_ACEPTABLE);
        }
        if (ficha.imc> EstadoIMCDTO.MEDIDA_DELGADEZ_ACEPTABLE && ficha.imc<EstadoIMCDTO.MEDIDA_PESO_NORMAL){
            fichaDeSalud.estadosIMC = EstadosIMC.findById(EstadoIMCDTO.PESO_NORMAL);
        }
        if (ficha.imc> EstadoIMCDTO.MEDIDA_PESO_NORMAL && ficha.imc<EstadoIMCDTO.MEDIDA_SOBREPRESO){
            fichaDeSalud.estadosIMC = EstadosIMC.findById(EstadoIMCDTO.SOBREPRESO);
        }
        if (ficha.imc> EstadoIMCDTO.MEDIDA_SOBREPRESO && ficha.imc<EstadoIMCDTO.MEDIDA_OBESO_TIPO_1){
            fichaDeSalud.estadosIMC = EstadosIMC.findById(EstadoIMCDTO.OBESO_TIPO_1);
        }
        if (ficha.imc> EstadoIMCDTO.MEDIDA_OBESO_TIPO_1 && ficha.imc<EstadoIMCDTO.MEDIDA_OBESO_TIPO_2){
            fichaDeSalud.estadosIMC = EstadosIMC.findById(EstadoIMCDTO.OBESO_TIPO_2);
        }
        if (ficha.imc > EstadoIMCDTO.MEDIDA_OBESO_TIPO_2){
            fichaDeSalud.estadosIMC = EstadosIMC.findById(EstadoIMCDTO.OBESO_TIPO_3);
        }

        fichaDeSalud.save();

    }

    public static void eliminarFichaDeSalud(Long numeroDeFicha){
        FichaDeSalud fichaDeSalud = FichaDeSalud.findById(numeroDeFicha);
        fichaDeSalud.delete();
    }

    public static void verFichaDeSaludEdit(Long numeroDeFicha){
        FichaDeSalud ficha = FichaDeSalud.findById(numeroDeFicha);
        LocalDate fechaNacimiento = new LocalDate(ficha.cliente.fechaNacimiento);
        LocalDate fechaActual = new LocalDate();
        Period edad = new Period(fechaNacimiento,fechaActual, PeriodType.yearMonthDay());
        if (session.get("tipo").equals(TipoUsuariosDTO.PROFESOR)){
            renderTemplate("InicioProfesor/editFichaDeSalud.html",edad,ficha);
        }
        if (session.get("tipo").equals(TipoUsuariosDTO.ADMINISTRATIVO)){
            renderTemplate("InicioAdministrativo/editFichaDeSalud.html",edad,ficha);
        }
        if (session.get("tipo").equals(TipoUsuariosDTO.ADMIN)){
            renderTemplate("InicioAdmin/editFichaDeSalud.html",edad,ficha);
        }
    }

    public static void editarFichaDeSalud(FichaDeSaludDTO ficha){
        Validation.required("ficha.estatura",ficha.estatura);
        Validation.required("ficha.imc",ficha.imc);
        Validation.required("ficha.img",ficha.img);
        Validation.required("ficha.problemasDeSalud",ficha.problemasDeSalud);
        Validation.required("ficha.peso",ficha.peso);
        if (Validation.hasErrors()) {
            response.status = Http.StatusCode.BAD_REQUEST;
            renderJSON(ErrorJSON.fromValidation());
        }
        FichaDeSalud fichaDeSalud = FichaDeSalud.findById(ficha.numeroDeFicha);
        fichaDeSalud.cliente = Persona.findPersonabyAltKey(ficha.altKeyCliente);
        fichaDeSalud.antecedentesMedicos = ficha.problemasDeSalud;
        fichaDeSalud.estatura = ficha.estatura;
        fichaDeSalud.imc = ficha.imc;
        fichaDeSalud.img = ficha.img;
        fichaDeSalud.peso = ficha.peso;
        fichaDeSalud.profesor = Persona.findPersonabyAltKey(session.get("altKey"));

        if (ficha.imc < EstadoIMCDTO.MEDIDA_DELGADEZ_SEVERA){
            fichaDeSalud.estadosIMC = EstadosIMC.findById(EstadoIMCDTO.DELGADEZ_SEVERA);
        }
        if (ficha.imc> EstadoIMCDTO.MEDIDA_DELGADEZ_SEVERA && ficha.imc<EstadoIMCDTO.MEDIDA_DELGADEZ_MODERADA){
            fichaDeSalud.estadosIMC = EstadosIMC.findById(EstadoIMCDTO.DELGADEZ_MODERADA);
        }
        if (ficha.imc> EstadoIMCDTO.MEDIDA_DELGADEZ_MODERADA && ficha.imc<EstadoIMCDTO.MEDIDA_DELGADEZ_ACEPTABLE){
            fichaDeSalud.estadosIMC = EstadosIMC.findById(EstadoIMCDTO.MEDIDA_DELGADEZ_ACEPTABLE);
        }
        if (ficha.imc> EstadoIMCDTO.MEDIDA_DELGADEZ_ACEPTABLE && ficha.imc<EstadoIMCDTO.MEDIDA_PESO_NORMAL){
            fichaDeSalud.estadosIMC = EstadosIMC.findById(EstadoIMCDTO.PESO_NORMAL);
        }
        if (ficha.imc> EstadoIMCDTO.MEDIDA_PESO_NORMAL && ficha.imc<EstadoIMCDTO.MEDIDA_SOBREPRESO){
            fichaDeSalud.estadosIMC = EstadosIMC.findById(EstadoIMCDTO.SOBREPRESO);
        }
        if (ficha.imc> EstadoIMCDTO.MEDIDA_SOBREPRESO && ficha.imc<EstadoIMCDTO.MEDIDA_OBESO_TIPO_1){
            fichaDeSalud.estadosIMC = EstadosIMC.findById(EstadoIMCDTO.OBESO_TIPO_1);
        }
        if (ficha.imc> EstadoIMCDTO.MEDIDA_OBESO_TIPO_1 && ficha.imc<EstadoIMCDTO.MEDIDA_OBESO_TIPO_2){
            fichaDeSalud.estadosIMC = EstadosIMC.findById(EstadoIMCDTO.OBESO_TIPO_2);
        }
        if (ficha.imc > EstadoIMCDTO.MEDIDA_OBESO_TIPO_2){
            fichaDeSalud.estadosIMC = EstadosIMC.findById(EstadoIMCDTO.OBESO_TIPO_3);
        }
        fichaDeSalud.save();
    }
}
