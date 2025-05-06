package biblioteca;

import biblioteca.pojo.Libro;
import java.io.Serializable;

/**
 *
 * @author Mar
 */
public class Nodo implements Serializable
{
    private static final long serialVersionUID = 1L;
    Libro lib;
    Nodo siguiente;
    
    public Nodo(Libro lib){
        this.lib = lib;
        this.siguiente = null;
    }
}
