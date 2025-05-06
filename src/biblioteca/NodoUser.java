package biblioteca;

import biblioteca.pojo.Usuario;
import java.io.Serializable;

/**
 *
 * @author Mar
 */
public class NodoUser implements Serializable
{
    private static final long serialVersionUID = 1L;
    Usuario user;
    NodoUser siguiente;

    public NodoUser(Usuario user)
    {
        this.user = user;
        this.siguiente = null;
    }
                
}
