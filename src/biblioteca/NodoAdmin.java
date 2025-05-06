package biblioteca;

import biblioteca.pojo.Administrador;
import java.io.Serializable;

/**
 *
 * @author Mar
 */
public class NodoAdmin implements Serializable
{
    private static final long serialVersionUID = 1L;
    Administrador admin;
    NodoAdmin siguiente;

    public NodoAdmin(Administrador admin)
    {
        this.admin = admin;
        this.siguiente = null;
    }
    
}
