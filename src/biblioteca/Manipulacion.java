package biblioteca;

import biblioteca.pojo.Libro;
import java.util.HashMap;
import poo.Lecturas;
import java.io.*;

public class Manipulacion
{

    static public String menuBuscar[] =
    {
        "Buscar por ISBN", "Buscar por titulo", "Buscar por autor", "Salir"
    };

    public static int menu(String mostrar[], String titulo)
    {
        System.out.println("**************" + titulo + "**************");
        for (int i = 0; i < mostrar.length; i++)
        {
            System.out.println((i + 1) + ") " + mostrar[i]);
        }
        System.out.println("Seleccione una opcion: ");
        return Lecturas.leerEntero(true);
    }

    public static void mostrarLibros()
    {
        Nodo aux = ObjetosBiblioteca.primero;
        if (AccionPersona.isEmpty())
        {
            System.out.println("No hay libros en existencia");
        } else
        {
            while (aux != null)
            {
                System.out.println(aux.lib.toString());
                aux = aux.siguiente;
            }
        }
    }

    public static void mostrarHashMap()
    {
        ObjetosBiblioteca.mapa.forEach((key, value) -> System.out.println("[Key] : " + key + " [Value] : " + value));
    }

    public static void guardarDatos()
    {
        try
        {
            ObjectOutputStream oosLibros = new ObjectOutputStream(new FileOutputStream("Datos/Libros.dat"));
            oosLibros.writeObject(ObjetosBiblioteca.primero);
            oosLibros.close();

            ObjectOutputStream oosMapa = new ObjectOutputStream(new FileOutputStream("Datos/Mapa Libros.dat"));
            oosMapa.writeObject(ObjetosBiblioteca.mapa);
            oosMapa.close();

            ObjectOutputStream oosCola = new ObjectOutputStream(new FileOutputStream("Datos/Libros Devueltos.dat"));
            oosCola.writeObject(ObjetosBiblioteca.frente);
            oosCola.writeObject(ObjetosBiblioteca.fin);
            oosCola.close();

            System.out.println("Datos guardados correctamente.");

        } catch (IOException e)
        {
            System.out.println("Error al guardar datos: " + e.getMessage());
        }
    }

    public static void cargarDatos()
    {
        try
        {
            ObjectInputStream oisLibros = new ObjectInputStream(new FileInputStream("Datos/Libros.dat"));
            ObjetosBiblioteca.primero = (Nodo) oisLibros.readObject();
            oisLibros.close();

            ObjectInputStream oisMapa = new ObjectInputStream(new FileInputStream("Datos/Mapa Libros.dat"));
            ObjetosBiblioteca.mapa = (HashMap<Long, Libro>) oisMapa.readObject();
            oisMapa.close();

            ObjectInputStream oisCola = new ObjectInputStream(new FileInputStream("Datos/Libros Devueltos.dat"));
            ObjetosBiblioteca.frente = (Nodo) oisCola.readObject();
            ObjetosBiblioteca.fin = (Nodo) oisCola.readObject();
            oisCola.close();

            try
            {
                Libro.setCons(cargarIds("Folios de Libros.dat"));
            } catch (IOException e)
            {
                Libro.setCons(0);
            }

            System.out.println("Datos cargados correctamente.");

        } catch (IOException | ClassNotFoundException e)
        {
            System.out.println("Error al cargar datos: " + e.getMessage());
        }
    }

    public static int cargarIds(String archivo) throws IOException
    {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Datos/" + archivo));
        int valor = ois.readInt();
        ois.close();
        return valor;
    }

    public static void guardarIds()
    {
        try
        {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Datos/Folios de Libros.dat"));
            oos.writeInt(Libro.getCons());
            oos.close();
        } catch (IOException e)
        {
            System.out.println("Error al guardar folio: " + e.getMessage());
        }
    }

}
