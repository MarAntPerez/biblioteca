package biblioteca;

import java.io.Serializable;

/**
 *
 * @author Mar
 */
public class Administrador extends Persona implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    public Administrador(String nombre, String user, String contrasenia)
    {
        super(nombre, user, contrasenia);
    }
    
}
