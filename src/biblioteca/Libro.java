package biblioteca;

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

    public Libro(String titulo, String autor, int anio, long isbn, char estado)
    {
        this.titulo = titulo;
        this.autor = autor;
        this.anio = anio;
        this.isbn = isbn;
        this.estado = estado;
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
    
    public String estadoTexto(){
        return (estado == 'D') ? "Disponible" : "Prestado";
    }

    @Override
    public String toString()
    {
        return "Titulo = " + titulo + "\nAutor = " + autor + "\nAnio = " + anio + "\nISBN = " + isbn + "\nEstado = " + estadoTexto() + "\n";
    }
    
    
}
