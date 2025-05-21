package biblioteca.pojo;

import java.io.Serializable;

/**
 *
 * @author Mar
 */
public class Persona implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String nombre;
    private String user;
    private String contrasenia;

    public Persona(String nombre, String user, String contrasenia)
    {
        this.nombre = nombre;
        this.user = user;
        this.contrasenia = contrasenia;
    }
    
    /**
     * @return the nombre
     */
    public String getNombre()
    {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    /**
     * @return the user
     */
    public String getUser()
    {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user)
    {
        this.user = user;
    }

    /**
     * @return the contrasenia
     */
    public String getContrasenia()
    {
        return contrasenia;
    }

    /**
     * @param contrasenia the contrasenia to set
     */
    public void setContrasenia(String contrasenia)
    {
        this.contrasenia = contrasenia;
    }

    @Override
    public String toString()
    {
        return "Persona{" + "nombre=" + nombre + ", user=" + user + ", contrasenia=" + contrasenia + '}';
    }
    
}
