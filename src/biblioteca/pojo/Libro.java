package biblioteca.pojo;

import java.io.Serializable;

public class Libro implements Serializable
{

    private static final long serialVersionUID = 1L;
    public Libro next;
    private String titulo;
    private String autor;
    private int anio;
    private long isbn;
    private char estado;
    private static int cons = 0;
    private String id;
    private String idUsuarioPrestamo;

    public Libro(String titulo, String autor, int anio, long isbn, char estado)
    {
        this.titulo = titulo;
        this.autor = autor;
        this.anio = anio;
        this.isbn = isbn;
        this.estado = estado;
        ++cons;
        this.id = Integer.toString(cons);
    }

    public Libro()
    {
    }

    /**
     * @return the titulo
     */
    public String getTitulo()
    {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo)
    {
        this.titulo = titulo;
    }

    /**
     * @return the autor
     */
    public String getAutor()
    {
        return autor;
    }

    /**
     * @param autor the autor to set
     */
    public void setAutor(String autor)
    {
        this.autor = autor;
    }

    /**
     * @return the anio
     */
    public int getAnio()
    {
        return anio;
    }

    /**
     * @param anio the anio to set
     */
    public void setAnio(int anio)
    {
        this.anio = anio;
    }

    /**
     * @return the isbn
     */
    public long getIsbn()
    {
        return isbn;
    }

    /**
     * @param isbn the isbn to set
     */
    public void setIsbn(long isbn)
    {
        this.isbn = isbn;
    }

    /**
     * @return the estado
     */
    public char getEstado()
    {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(char estado)
    {
        this.estado = estado;
    }

    public String estadoTexto()
    {
        return (estado == 'D') ? "Disponible" : "Prestado";
    }

    public static String formatoId(String id)
    {
        while (id.length() < 5)
        {
            id = "0" + id;
        }

        return id;
    }

    public void setIdUsuarioPrestamo(String idUsuario)
    {
        this.idUsuarioPrestamo = idUsuario;
    }

    public String getIdUsuarioPrestamo()
    {
        return idUsuarioPrestamo;
    }

    public static int getCons()
    {
        return cons;
    }

    public static void setCons(int valor)
    {
        cons = valor;
    }

    @Override
    public String toString()
    {
        return "Libro{" + "titulo=" + titulo + ", autor=" + autor + ", anio=" + anio + ", isbn=" + isbn + ", estado=" + estadoTexto() + ", id=" + formatoId(id) + '}';
    }

}
