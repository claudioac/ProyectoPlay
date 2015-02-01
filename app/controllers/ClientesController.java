package controllers;

import controllers.variblesEstaticas.NacionalidadDTO;
import controllers.variblesEstaticas.TipoUsuariosDTO;
import models.*;
import models.ClasesDTO.*;
import models.Mensualidad;
import models.Persona;
import models.error.ErrorJSON;
import org.joda.time.Instant;
import org.joda.time.LocalDate;
import org.joda.time.Years;
import play.data.binding.As;
import play.data.validation.Validation;
import play.db.jpa.JPA;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.With;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static play.modules.pdf.PDF.*;

/**
 * @author Claudio Acuña
 */
@With(AutenticarAdministracion.class)
public class ClientesController extends Controller {

    public static void nuevoCliente(Cliente cliente, @As("dd/mm/yyyy") Date fechaNacimiento) {

        validacionesCreacionYEdicion(cliente,fechaNacimiento);
        if (Validation.hasErrors()) {
            response.status = Http.StatusCode.BAD_REQUEST;
            renderJSON(ErrorJSON.fromValidation());
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
        persona.setRegion(Region.getId(cliente.region));
        persona.setProvincia(Provincia.getId(cliente.provincia));
        persona.setComuna(Comuna.getId(cliente.comuna));
        persona.setTipoUsuario(TipoUsuario.getById(TipoUsuariosDTO.IdUsuario));
        persona.setNacionalidad(Pais.findNacionalidadById(NacionalidadDTO.CHILENA));
        usuario.setEmail(cliente.email);
        persona.setUsuario(usuario);
        usuario.save();
        persona.save();
        JPA.em().flush();
        renderText(persona.altKey);
    }

    public static Validation validacionesCreacionYEdicion(Cliente cliente,Date fechaNacimiento){
        Validation.required("cliente.nombres", cliente.nombres);
        Validation.required("cliente.apellidoMaterno", cliente.apellidoMaterno);
        Validation.required("cliente.apellidoPaterno", cliente.apellidoPaterno);
        Validation.required("cliente.rut", cliente.rut);
        Validation.required("cliente.celular", cliente.celular);
        Validation.required("cliente.telefono", cliente.telefono);
        Validation.required("cliente.genero", cliente.genero);

        if(cliente.comuna == null){
            Validation.addError("cliente.comuna", "validation.comuna");
        }
        if (cliente.region == null){
            Validation.addError("cliente.region", "validation.region");
        }
        if(cliente.provincia == null){
            Validation.addError("cliente.provincia", "validation.provincia");
        }

        Validation.required("cliente.direccion", cliente.direccion);
        if (fechaNacimiento == null) {
            Validation.addError("fechaNacimiento", "validation.fechaRequerida");
        }
        Validation.required("cliente.email", cliente.email);
        Validation.email("cliente.email", cliente.email);

        return Validation.current();
    }

    public static void contratoCliente(String altKey) {
        List<TipoPlan> tipoDePlanes = TipoPlan.findAllTipoDePlanActivo();
        TipoPlan cuotaDeIncorporacion = TipoPlan.getCoutaDeIncorporacion();
        List<Region> regiones = Region.getAllRegiones();
        //Persona persona = Persona.findById(23L);
        //TODO Cambiar a modo producción.
        Persona persona = Persona.findPersonabyAltKey(altKey);
        Date fechaActual = new Date();
        int anos = (fechaActual.getYear() - persona.fechaNacimiento.getYear());
        renderTemplate("InicioAdmin/Cliente/contratoCliente.html", tipoDePlanes, persona, anos, cuotaDeIncorporacion, regiones);
    }

    public static void generarContrato(String altKey) {
        Persona persona = Persona.findPersonabyAltKey(altKey);
        Contrato contrato = Contrato.findContratoByAltKeyPersona(altKey);
        Date fechaActual = new Date();
        Options options = new Options();
        options.filename = "Contrato" + ".pdf";
        renderPDF(options, persona, contrato, fechaActual);
    }

    public static void reglamento() {
        Options options = new Options();
        options.filename = "Reglamento" + ".pdf";
        renderPDF(options);
    }

    public static void getValoresDePlan(Long id, String altKey) {
        TipoPlan plan = TipoPlan.getValoresDePlan(id);
        Persona persona = Persona.findPersonabyAltKey(altKey);
        Date fechaIncorporacion = new Date();
        Contrato contrato = Contrato.findContratoByAltKeyPersona(altKey);
        if (contrato != null) {
            contrato.setPersona(persona);
            contrato.setFechaCreacion(fechaIncorporacion);
            contrato.setTipoPlan(plan);
            contrato.setVigente(true);
            contrato.save();
            persona.setFechaDeIncorporacion(fechaIncorporacion);
            persona.save();
        } else {
            contrato = new Contrato();
            contrato.setPersona(persona);
            contrato.setFechaCreacion(fechaIncorporacion);
            contrato.setTipoPlan(plan);
            contrato.setVigente(true);
            contrato.save();
            persona.setFechaDeIncorporacion(fechaIncorporacion);
            persona.save();
        }
        renderJSON(plan);
    }


    public static void descartarRegistro(String altKeyPersona) {
        Persona persona = Persona.findPersonabyAltKey(altKeyPersona);
        Contrato contrato = Contrato.findContratoByAltKeyPersona(altKeyPersona);
        if (contrato != null) {
            contrato.delete();
            persona.delete();
        } else {
            persona.delete();
        }
        if (session.get("tipo").equals(TipoUsuariosDTO.ADMIN)) {
            renderTemplate("InicioAdmin/clientes.html");
        }
        if (session.get("tipo").equals(TipoUsuariosDTO.ADMIN)) {
            renderTemplate("InicioAdministrativo/clientes.html");
        }
    }


    public static void editarCliente(Cliente cliente, @As("dd/mm/yyyy") Date fechaNacimiento) {
        validacionesCreacionYEdicion(cliente,fechaNacimiento);
        if (Validation.hasErrors()) {
            response.status = Http.StatusCode.BAD_REQUEST;
            renderJSON(ErrorJSON.fromValidation());
        }
        Persona persona = Persona.findPersonabyAltKey(cliente.altKey);
        persona.setNombres(cliente.nombres);
        persona.setApellidoPaterno(cliente.apellidoPaterno);
        persona.setApellidoMaterno(cliente.apellidoMaterno);
        persona.setRut(cliente.rut);
        persona.setFechaNacimiento(fechaNacimiento);
        persona.setTelefono(cliente.telefono);
        persona.setCelular(cliente.celular);
        persona.setDireccion(cliente.direccion);
        persona.setGenero(cliente.genero);
        persona.setRegion(Region.getId(cliente.region));
        persona.setProvincia(Provincia.getId(cliente.provincia));
        persona.setComuna(Comuna.getId(cliente.comuna));
        persona.setTipoUsuario(TipoUsuario.getById(TipoUsuariosDTO.IdUsuario));
        persona.setNacionalidad(Pais.findNacionalidadById(NacionalidadDTO.CHILENA));
        persona.usuario.setEmail(cliente.email);
        persona.save();
    }

//    public static void completarRegistro(String altKeyPersona) {
//        Contrato contrato = Contrato.findContratoByAltKeyPersona(altKeyPersona);
//        mesualidadInicial(contrato);
//        if (session.get("tipo").equals(TipoUsuariosDTO.ADMIN)) {
//            redirect("/admin/clientes");
//        }
//    }
//
//    private static void mesualidadInicial(Contrato contrato) {
//        Mensualidad mensualidad = new Mensualidad();
//        mensualidad.setContrato(contrato);
//        mensualidad.setFechaPago(contrato.getFechaCreacion());
//        Calendar vencimiento = Calendar.getInstance();
//        vencimiento.setTime(contrato.getFechaCreacion());
//        vencimiento.add(Calendar.MONTH, 1);
//        mensualidad.setFechaVencimiento(vencimiento.getTime());
//        mensualidad.setMontoCancelado(contrato.getTipoPlan().getValorMensualidad());
//        mensualidad.save();
//    }

    public static void fichaDeCliente(String altKey){
        Persona cliente = Persona.findPersonabyAltKey(altKey);
        Contrato contrato = Contrato.findContratoByAltKeyPersona(altKey);
        Mensualidad mensualidad = Mensualidad.lastMensualidadByAltkeyCliente(altKey);
        if (session.get("tipo").equals(TipoUsuariosDTO.ADMIN)) {
            renderTemplate("InicioAdmin/Cliente/fichaCliente.html",cliente,contrato,mensualidad);
        }
        if (session.get("tipo").equals(TipoUsuariosDTO.PROFESOR)) {
            renderTemplate("InicioProfesor/Cliente/fichaClienteProfesor.html",cliente,contrato,mensualidad);
        }
        if (session.get("tipo").equals(TipoUsuariosDTO.ADMINISTRATIVO)) {
            renderTemplate("InicioAdministrativo/Cliente/fichaClienteAdministrativo.html",cliente,contrato,mensualidad);
        }
    }

    public static void buscarMensualidades(String altKey){
        List<Mensualidad>  mensualidades = Mensualidad.mensualidadesByAltkeyCliente(altKey);
        renderJSON(Mensualidad.mensualidadesListDTO(mensualidades));
    }

    public static void actualizarCliente(String altKey){
        List<TipoPlan> tipoDePlanes = TipoPlan.findAllTipoDePlanActivo();
        Persona persona = Persona.findPersonabyAltKey(altKey);
        TipoPlan cuotaDeIncorporacion = TipoPlan.getCoutaDeIncorporacion();
        Contrato contrato = Contrato.findContratoByAltKeyPersona(altKey);
        List<Region> regiones = Region.getAllRegiones();
        if (session.get("tipo").equals(TipoUsuariosDTO.ADMIN)){
            renderTemplate("InicioAdmin/Cliente/actualizarClienteAdmin.html",persona,cuotaDeIncorporacion,regiones,tipoDePlanes,contrato);
        }
        if (session.get("tipo").equals(TipoUsuariosDTO.ADMINISTRATIVO)){
            renderTemplate("InicioAdministrativo/Cliente/actualizarClienteAdministrador.html",persona,cuotaDeIncorporacion,regiones,tipoDePlanes,contrato);
        }
    }

    public static void updateContrato(Long id, String altKey){
        TipoPlan plan = TipoPlan.getValoresDePlan(id);
        Persona persona = Persona.findPersonabyAltKey(altKey);
        Contrato contrato = Contrato.findContratoByAltKeyPersona(altKey);
        contrato.setVigente(false);
        contrato.save();
        Contrato contrato1 = new Contrato();
        contrato1.setPersona(persona);
        contrato1.setTipoPlan(plan);
        contrato1.setVigente(true);
        contrato1.setFechaCreacion(new Date());
        contrato1.save();
        renderJSON(plan);

    }

    public static void pagoMensualidad(models.ClasesDTO.Mensualidad mensualidad,String personaAltkey){
        Validation.required("mensualidad.fechaPago",mensualidad.fechaPago);
        Validation.required("mensualidad.montoCancelado",mensualidad.montoCancelado);
        Validation.required("mensualidad.numeroBoleta",mensualidad.numeroBoleta);
        if (Validation.hasErrors()) {
            response.status = Http.StatusCode.BAD_REQUEST;
            renderJSON(ErrorJSON.fromValidation());
        }
        Contrato contrato = Contrato.findContratoByAltKeyPersona(personaAltkey);
        Mensualidad pago = new Mensualidad();
        pago.setContrato(contrato);
        pago.setFechaPago(mensualidad.fechaPago);
        Calendar vencimiento = Calendar.getInstance();
        vencimiento.setTime(mensualidad.fechaPago);
        vencimiento.add(Calendar.MONTH, 1);
        pago.setFechaVencimiento(vencimiento.getTime());
        pago.setMontoCancelado(mensualidad.montoCancelado);
        pago.setNumeroBoleta(mensualidad.numeroBoleta);
        pago.save();

    }

}
