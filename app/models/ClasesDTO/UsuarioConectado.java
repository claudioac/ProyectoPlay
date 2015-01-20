package models.ClasesDTO;

import java.io.Serializable;

/**
 * @author Claudio Acuña
 */
public class UsuarioConectado implements Serializable {

    public String usuario;
    public String nombreCompleto;
    public String email;
    public Long idTipoUsuario;
    public String tipoUsuario;

}
