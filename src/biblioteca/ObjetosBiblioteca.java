package biblioteca;

import biblioteca.pojo.Libro;
import java.util.HashMap;

/**
 *
 * @author Mar
 */
public class ObjetosBiblioteca
{
    static public Nodo primero;
    static public Nodo frente;
    static public Nodo fin;
    static public NodoAdmin primerAdmin;
    static public NodoUser primerUser;
    static public HashMap<Long, Libro> mapa = new HashMap<>();
    static Libro[] librosDisponibles = new Libro[0];
    static Libro[] librosPrestados = new Libro[0];

    public ObjetosBiblioteca()
    {
        primero = null;
        frente = null;
        fin = null;
        primerAdmin = null;
        primerUser = null;
    }
    
}
