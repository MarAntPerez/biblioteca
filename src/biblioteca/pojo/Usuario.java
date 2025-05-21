package biblioteca.pojo;

import java.io.Serializable;

/**
 *
 * @author Mar
 */
public class Usuario extends Persona implements Serializable
{

    private static final long serialVersionUID = 1L;
    private static int cons = 0;
    private String id;
    private String sancion;

    public Usuario(String nombre, String user, String contrasenia)
    {
        super(nombre, user, contrasenia);
        ++cons;
        this.id = Integer.toString(cons);
        sancion = "No aplica";
    }

    public String getId()
    {
        return id;
    }

    public void setSancion(String sancion)
    {
        this.sancion = sancion;
    }

    public String getSancion()
    {
        return sancion;
    }

    public static String formatoId(String id)
    {
        while (id.length() < 5)
        {
            id = "0" + id;
        }

        return id;
    }

    public static int getCons()
    {
        return cons;
    }

    public static void setCons(int c)
    {
        cons = c;
    }

    @Override
    public String toString()
    {
        return "Usuario{" + super.toString() + "id=" + formatoId(id) + '}';
    }

}
