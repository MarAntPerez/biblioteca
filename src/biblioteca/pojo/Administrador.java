package biblioteca.pojo;

import java.io.Serializable;

/**
 *
 * @author Mar
 */
public class Administrador extends Persona implements Serializable
{
    private static final long serialVersionUID = 1L;
    private static int cons = 0;
    private String id;
    
    public Administrador(String nombre, String user, String contrasenia)
    {
        super(nombre, user, contrasenia);
        this.id = Integer.toString(cons);
        cons++;
    }
    
    public static String formatoId(String id)
    {
        while (id.length() < 5)
        {
            id = "0" + id;
        }
        
        return id;
    }

    @Override
    public String toString()
    {
        return "Administrador{" + super.toString() + "Id: " + formatoId(id) + '}';
    }
    
}
