package controllers;

import models.ClasesDTO.PersonaDTO;
import models.Persona;
import play.mvc.Controller;
import play.mvc.With;

import java.util.List;

/**
 * @author Claudio Acuña
 */
@With(Autenticar.class)
public class InicioUsuario extends Controller{

    /**
     * Funcion que renderisa la página principal del usuario.
     */
    public static void indexUsuario(){
        render();
    }

    public static void solicitarAsesoramiento(){
        List<PersonaDTO> profesores = Persona.findAllProfesoresParaCitas();
        render(profesores);
    }
}
