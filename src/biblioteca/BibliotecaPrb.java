package biblioteca;

/**
 *
 * @author Mar
 */
public class BibliotecaPrb
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Manipulacion.cargarDatos();
        Sesion.cargarSesion();
        Sesion.inicio();
        Sesion.guardarSesion();
        Manipulacion.guardarDatos();
    }
    
}
