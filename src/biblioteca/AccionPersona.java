package biblioteca;

import biblioteca.pojo.Libro;
import java.util.ArrayList;
import java.util.List;
import poo.Lecturas;

/**
 *
 * @author Mar
 */
public class AccionPersona
{

    public static void buscar()
    {
        if (isEmpty())
        {
            System.out.println("Aun no hay ningun libro registrado");
        } else
        {
            int bus;
            do
            {
                bus = Manipulacion.menu(Manipulacion.menuBuscar, "Menu de busqueda");
                switch (bus)
                {
                    case 1:
                        System.out.println("Ingrese el ISBN");
                        int isbnBuscar = Lecturas.leerEntero(true);
                        Libro libBus = busquedaISBN(isbnBuscar);
                        if (libBus == null)
                        {
                            System.out.println("Libro no encontrado");
                        } else
                        {
                            System.out.println(libBus.toString());
                        }
                        break;

                    case 2:
                        System.out.println("Ingrese el titulo del libro");
                        String titulo = Lecturas.leerCadena();
                        Nodo libBusDos = busquedaTitulo(titulo);
                        if (libBusDos == null)
                        {
                            System.out.println("Libro no encontrado");
                        } else
                        {
                            System.out.println(libBusDos.toString());
                        }
                        break;

                    case 3:
                        System.out.println("Ingrese el autor del libro.");
                        String autor = Lecturas.leerCadena();
                        List<Libro> librosAutor = busquedaAutor(autor);
                        if (librosAutor == null)
                        {
                            System.out.println("Libro no encontrado");
                        } else
                        {
                            for (int i = 0; i < librosAutor.size(); i++)
                            {
                                System.out.println(librosAutor.toString());
                            }
                        }
                        break;

                    case 4:
                        System.out.println("Saliendo");
                        break;

                    default:
                        System.out.println("Opcion incorrecta.");
                        break;
                }
            } while (bus != Manipulacion.menuBuscar.length);
        }
    }

    public static boolean isEmpty()
    {
        return (ObjetosBiblioteca.primero == null);
    }

    public static Libro busquedaISBN(long isbn)
    {
        return ObjetosBiblioteca.mapa.get(isbn);
    }

    public static Nodo busquedaTitulo(String titulo)
    {
        Nodo aux = ObjetosBiblioteca.primero;
        if (isEmpty())
        {
            System.out.println("No hay libros en existencia");
        } else
        {
            while (aux != null)
            {
                if (aux.lib.getTitulo().equals(titulo))
                {
                    return aux;
                }
                aux = aux.siguiente;
            }
        }
        return null;
    }

    public static List<Libro> busquedaAutor(String autor)
    {
        Nodo aux = ObjetosBiblioteca.primero;
        List<Libro> librosAutor = new ArrayList<>();

        if (isEmpty())
        {
            System.out.println("No hay libros en existencia");
        } else
        {
            while (aux != null)
            {
                if (aux.lib.getAutor().equalsIgnoreCase(autor))
                {
                    librosAutor.add(aux.lib);
                }
                aux = aux.siguiente;
            }
        }

        if (!librosAutor.isEmpty())
        {
            return librosAutor;
        } else
        {
            return null;
        }
    }
}
