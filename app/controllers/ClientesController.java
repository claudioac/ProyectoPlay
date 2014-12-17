package controllers;

import controllers.variblesEstaticas.NacionalidadDTO;
import controllers.variblesEstaticas.TipoUsuariosDTO;
import models.*;
import models.ClasesDTO.Cliente;
import org.joda.time.Instant;
import org.joda.time.LocalDate;
import org.joda.time.Years;
import play.data.binding.As;
import play.data.validation.Validation;
import play.db.jpa.JPA;
import play.mvc.Controller;
import play.mvc.With;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static play.modules.pdf.PDF.*;

/**
 *@author Claudio Acuña
 */
@With(AutenticarAdministracion.class)
public class ClientesController extends Controller {

    public static void nuevoCliente(Cliente cliente,@As("dd/mm/yyyy") Date fechaNacimiento){

        Validation.required("cliente.nombres",cliente.nombres);
        Validation.required("cliente.apellidoMaterno",cliente.apellidoMaterno);
        Validation.required("cliente.apellidoPaterno",cliente.apellidoPaterno);
        Validation.required("cliente.rut",cliente.rut);
        Validation.required("cliente.celular",cliente.celular);
        Validation.required("cliente.telefono",cliente.telefono);
        Validation.required("cliente.genero",cliente.genero);
        if (cliente.comuna == 0){
            Validation.addError("cliente.comuna","validation.comuna");
        }
        Validation.required("cliente.direccion",cliente.direccion);
        if (fechaNacimiento == null){
            Validation.addError("fechaNacimiento","validation.fechaRequerida");
        }
        Validation.required("cliente.email",cliente.email);
        Validation.email("cliente.email",cliente.email);

        if (Validation.hasErrors()){
            params.flash();
            Validation.keep();
            InicioAdmin.clientes();
        }

        Usuario usuario = new Usuario();
        Persona persona = new Persona();
        persona.setNombres(cliente.nombres);
        persona.setApellidoPaterno(cliente.apellidoPaterno);
        persona.setApellidoMaterno(cliente.apellidoMaterno);
        persona.setRut(cliente.rut);
        persona.setFechaNacimiento(fechaNacimiento);
        persona.setTelefono(cliente.telefono);
        persona.setCelular(cliente.celular);
        persona.setDireccion(cliente.direccion);
        persona.setGenero(cliente.genero);
        persona.setComuna(Comuna.getId(cliente.comuna));
        persona.setTipoUsuario(TipoUsuario.getById(TipoUsuariosDTO.IdUsuario));
        persona.setNacionalidad(Pais.findNacionalidadById(NacionalidadDTO.CHILENA));
        usuario.setEmail(cliente.email);
        persona.setUsuario(usuario);
        usuario.save();
        persona.save();
        JPA.em().flush();
        contratoCliente(persona.altKey);
    }

    public static void contratoCliente(String altKey){
        List<TipoPlan> tipoDePlanes = TipoPlan.findAllTipoDePlanActivo();
        TipoPlan cuotaDeIncorporacion = TipoPlan.getCoutaDeIncorporacion();
        Persona persona = Persona.findById(15L);
        //Persona persona = Persona.findPersonabyAltKey(altKey);
        Date fechaActual = new Date();
        int anos = (fechaActual.getYear()-persona.fechaNacimiento.getYear());
        renderTemplate("InicioAdmin/Cliente/contratoCliente.html",tipoDePlanes,persona,anos,cuotaDeIncorporacion);
    }

    public static void generarContrato(){
        Options options = new Options();
        options.filename = "Contrato"+".pdf";
        renderPDF(options);
    }

    public static void reglamento(){
        Options options = new Options();
        options.filename = "Reglamento"+".pdf";
        renderPDF(options);
    }

    public static void getValoresDePlan(Long id){
        TipoPlan plan = TipoPlan.getValoresDePlan(id);
        renderJSON(plan);
    }

    public static  void completarRegistro(Long tipoDePlan, String altKeyPersona){
        Persona persona = Persona.findPersonabyAltKey(altKeyPersona);
        TipoPlan tipoPlan = TipoPlan.findById(tipoDePlan);
        Date fechaIncorporacion = new Date();
        Contrato contrato = new Contrato();
        contrato.setPersona(persona);
        contrato.setFechaCreacion(fechaIncorporacion);
        contrato.setTipoPlan(tipoPlan);
        contrato.setVigente(true);
        contrato.save();
        persona.setFechaDeIncorporacion(fechaIncorporacion);
        persona.save();
        JPA.em().flush();
        mesualidadInicial(contrato);
    }

    private static void mesualidadInicial(Contrato contrato) {
        Mensualidad mensualidad = new Mensualidad();
        mensualidad.setContrato(contrato);
        mensualidad.setFechaPago(contrato.getFechaCreacion());
        Calendar vencimiento = Calendar.getInstance();
        vencimiento.setTime(contrato.getFechaCreacion());
        vencimiento.add(Calendar.MONTH,1);
        mensualidad.setFechaVencimiento(vencimiento.getTime());
        mensualidad.setMontoCancelado(contrato.getTipoPlan().getValorMensualidad());
        mensualidad.save();
    }

}
