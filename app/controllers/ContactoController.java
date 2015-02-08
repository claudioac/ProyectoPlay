package controllers;

import models.ClasesDTO.ContactoDTO;
import models.ClasesDTO.EstadoContactoDTO;
import models.Contacto;
import models.EstadoContacto;
import models.Persona;
import models.TipoDeContacto;
import models.error.ErrorJSON;
import play.data.validation.Validation;
import play.mvc.Controller;
import play.mvc.Http;

import java.util.Date;

/**
 * @author Claudio Acuña
 */
public class ContactoController extends Controller {


    public static void nuevoContactoCliente(ContactoDTO contacto){
        Validation.required("contacto.asunto",contacto.asunto);
        Validation.required("contacto.email",contacto.email);
        Validation.required("contacto.mensaje",contacto.mensaje);
        Validation.required("contacto.idTipoDeContacto",contacto.idTipoDeContacto);
        Validation.email("contacto.email",contacto.email);
        if (Validation.hasErrors()) {
            response.status = Http.StatusCode.BAD_REQUEST;
            renderJSON(ErrorJSON.fromValidation());
        }
        String altKeyCliente = session.get("altKey");
        Persona cliente = Persona.findPersonabyAltKey(altKeyCliente);
        Contacto contactoCliente = new Contacto();
        contactoCliente.asunto = contacto.asunto;
        contactoCliente.cliente = cliente;
        contactoCliente.email = contacto.email;
        contactoCliente.fechaDeContacto = new Date();
        contactoCliente.tipoDeContacto = TipoDeContacto.findById(contacto.idTipoDeContacto);
        contactoCliente.mensaje = contacto.mensaje;
        contactoCliente.estado = EstadoContacto.findById(EstadoContactoDTO.ENVIADO);
        contactoCliente.save();
    }

    public static void nuevoContactoPersonaExterna(){

    }
}
