package controllers;

import models.*;
import models.ClasesDTO.*;
import models.Mensualidad;
import models.error.ErrorJSON;
import mvc.results.JxlsResult;
import play.data.validation.Validation;
import play.modules.pdf.PDF;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.With;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static play.modules.pdf.PDF.renderPDF;

/**
 * @author Claudio Acuña
 */
@With(Autenticar.class)
public class InicioUsuario extends Controller {

    /**
     * Funcion que renderisa la página principal del usuario.
     */
    public static void indexUsuario() {
        render();
    }

    public static void solicitarAsesoramiento() {
        List<PersonaDTO> profesores = Persona.findAllProfesoresParaCitas();
        render(profesores);
    }

    public static void clasesDisponibles(){
        List<TipoDeClase> tipoDeClases = TipoDeClase.findAll();
        render(tipoDeClases);
    }

    public static void historialDeRutinas(){
     String altKeyCliente = session.get("altKey");
     List<RutinaDTO> rutinas = Rutina.findAllRutinasByAltKeyCliente(altKeyCliente);
        render(rutinas);
    }

    public static void fichaDeSalud(){
        String altKeyCliente = session.get("altKey");
        List<FichaDeSaludDTO> fichas = FichaDeSalud.findAllFichasDeSaludByAltKeyCliente(altKeyCliente);
        render(fichas);
    }

    public static void historialDePagos(){
        String altKeyCliente = session.get("altKey");
        Persona cliente = Persona.findPersonabyAltKey(altKeyCliente);
        Contrato contrato = Contrato.findContratoByAltKeyPersona(altKeyCliente);
        List<Mensualidad> mensualidades = Mensualidad.mensualidadesByAltkeyCliente(altKeyCliente);
        int montoCancelado = 0;
        int subTotal = 0;
        for (Mensualidad mensualidad : mensualidades) {
            montoCancelado += mensualidad.montoCancelado;
        }
        subTotal = (int) (contrato.tipoPlan.valorTotalPlan - montoCancelado);
        render(cliente,contrato,mensualidades,subTotal,montoCancelado);
    }

    public static void reservarHoraParaAsesoria(String altKeyProfesor, String altKeyCliente, Date fechaDeAsesoria) {
        Validation.required("fechaDeAsesoria",fechaDeAsesoria);
        if (Validation.hasErrors()) {
            response.status = Http.StatusCode.BAD_REQUEST;
            renderJSON(ErrorJSON.fromValidation());
        }
        Persona profesor = Persona.findPersonabyAltKey(altKeyProfesor);
        Persona cliente = Persona.findPersonabyAltKey(altKeyCliente);
        Cita cita  = new Cita();
        cita.fecha = fechaDeAsesoria;
        cita.profesor = profesor;
        cita.cliente = cliente;
        cita.estado = EstadoCita.findById(CitaEstadoDTO.EN_ESPERA);
        cita.save();
    }

    public static void misCitasParaAsesoria(){
        String altKeyCliente = session.get("altKey");
        Persona usuarioConectado = Persona.findPersonabyAltKey(altKeyCliente);
        List<CitasDTO> citas = Cita.findAllCitasByPersona(usuarioConectado);
        render(citas);
    }

    public static void cancelarCitaReservadaParaAsesoria(String altKeyCita){
        String altKeyCliente = session.get("altKey");
        Persona usuarioConectado = Persona.findPersonabyAltKey(altKeyCliente);
        Cita cita = Cita.find("altKey",altKeyCita).first();
        cita.delete();
    }

    public static void generarContrato(String altKey) {
        Persona persona = Persona.findPersonabyAltKey(altKey);
        Contrato contrato = Contrato.findContratoByAltKeyPersona(altKey);
        Date fechaActual = new Date();
        PDF.Options options = new PDF.Options();
        options.filename = "Contrato" + ".pdf";
        renderPDF(options, persona, contrato, fechaActual);
    }

    public static void descargaDeRutina(String altKeyRutina){
        String altKeyCliente = session.get("altKey");
        Persona cliente = Persona.findPersonabyAltKey(altKeyCliente);
        Rutina rutina = Rutina.findRutinaByAltKey(altKeyRutina);
        List<EjercicioDTO> ejercicio = Ejercicio.findAllEjerciciosByAltKeyRutina(altKeyRutina);
        FichaDeSalud fichaDeSalud = FichaDeSalud.findUltimaFichaDeSalud(cliente.altKey);
        PDF.Options options = new PDF.Options();
        options.filename = "Rutina Nº"+ rutina.id + ".pdf";
        renderPDF(options, rutina,ejercicio,cliente,fichaDeSalud);
    }

    public static void exportMensualidadesExcel(){
        DateFormat dateFormat = DateFormat.getDateInstance();
        String altKeyCliente = session.get("altKey");
        Persona cliente = Persona.findPersonabyAltKey(altKeyCliente);
        Contrato contrato = Contrato.findContratoByAltKeyPersona(altKeyCliente);
        List<Mensualidad> mensualidades = Mensualidad.mensualidadesByAltkeyCliente(altKeyCliente);
        HashMap<String, Object> model = new HashMap<String, Object>();
        model.put("contrato",contrato);
        model.put("mensualidades",mensualidades);
        model.put("cliente",cliente);
        model.put("dateFmt", dateFormat);
        throw new JxlsResult(model, cliente.getNombreCompleto()+" Mensualidades");
    }

    public static void productosDisponiblesUsuario(){
        List<TipoDeProducto> categorias = TipoDeProducto.findAll();
        render(categorias);
    }

    public static void misContactos(){
        String altKeyCliente = session.get("altKey");
        Persona cliente = Persona.findPersonabyAltKey(altKeyCliente);
        List<ContactoDTO> contactos = Contacto.findAllContactosByIdCliente(cliente);
        List<TipoDeContacto> tipoDeContactos = TipoDeContacto.findAll();
        render(contactos,tipoDeContactos);
    }
}
