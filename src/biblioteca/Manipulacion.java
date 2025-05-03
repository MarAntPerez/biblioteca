package biblioteca;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import poo.Lecturas;
import java.io.*;

public class Manipulacion
{

    static private Nodo primero;
    static private Nodo frente;
    static private Nodo fin;

    static private HashMap<Long, Libro> mapa = new HashMap<>();
    static private String[] menuAdmin =
    {
        "Agregar libro", "Eliminar libro", "Buscar libro", "Prestar libro", "Devolver libro", "Revisar libros devueltos", "Salir"
    };
    static private String menuUser[] =
    {
        "Buscar libro", "Salir"
    };
    static private String menuBuscar[] =
    {
        "Buscar por ISBN", "Buscar por titulo", "Buscar por autor", "Salir"
    };

    public Manipulacion()
    {
        primero = null;
        frente = null;
        fin = null;
    }

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

    public static void opcionesAdmin()
    {
        int opc;

        do
        {
            opc = menu(menuAdmin, "Menu Administrador");

            switch (opc)
            {
                case 1:
                    leerDatos();
                    break;

                case 2:
                    if (isEmpty())
                    {
                        System.out.println("Aun no hay ningun libro registrado");
                    } else
                    {
                        System.out.println("Ingrese el ISBN del libro que desea eliminar: ");
                        int isbn = Lecturas.leerEntero(true);
                        Libro lib = busquedaISBN(isbn);
                        if (lib == null)
                        {
                            System.out.println("Libro no encontrado");
                        } else
                        {
                            System.out.println(lib.toString());
                            System.out.println("Eliminar libro?\n1)Si.\n)No.");
                            int eli = Lecturas.leerEntero(true);
                            if (eli == 1)
                            {
                                eliminarLibro(lib);
                            } else
                            {
                                return;
                            }
                        }
                    }
                    break;

                case 3:
                    if (isEmpty())
                    {
                        System.out.println("Aun no hay ningun libro registrado");
                    } else
                    {
                        int bus;
                        do
                        {
                            bus = menu(menuBuscar, "Menu de busqueda");
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
                        } while (bus != menuBuscar.length);
                    }
                    break;

                case 4:
                    if (isEmpty())
                    {
                        System.out.println("Aun no hay libros registrados");
                    } else
                    {
                        System.out.println("Ingrese el isbnm del libro");
                        int isbn = Lecturas.leerEntero(true);
                        Libro lib = busquedaISBN(isbn);
                        if (lib == null)
                        {
                            System.out.println("Libro no encontrado");
                        } else
                        {
                            System.out.println(lib.toString());
                            prestar(lib);
                        }
                    }
                    break;

                case 5:
                    if (isEmpty())
                    {
                        System.out.println("Aun no hay libros registrados");
                    } else
                    {
                        System.out.println("Ingres el ISBN del libro");
                        int isbn = Lecturas.leerEntero(true);
                        Libro lib = busquedaISBN(isbn);
                        devolver(lib);
                    }
                    break;

                case 6:
                    if (colaVacia())
                    {
                        System.out.println("Aun no se ha devuelto ningun libro");
                    } else
                    {
                        revisar();
                    }
                    break;

                case 7:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opcion incorrecta...");
                    break;
            }
        } while (opc != menuAdmin.length);

    }

    public static void opcionesUser()
    {
        int opc;

        do
        {
            opc = menu(menuUser, "Menu de usuario");
            switch (opc)
            {
                case 1:
                    if (isEmpty())
                    {
                        System.out.println("Aun no hay ningun libro resgitrado");
                    } else
                    {
                        int bus;
                        do
                        {
                            bus = menu(menuBuscar, "Menu de busqueda");
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
                        } while (bus != menuBuscar.length);
                    }
                    break;

                case 2:
                    System.out.println("Saliendo");
                    break;

                default:
                    System.out.println("opcion incorrecta");
                    break;
            }
        } while (opc != menuUser.length);
    }

    public static boolean isEmpty()
    {
        return (primero == null);
    }

    public static void leerDatos()
    {
        Libro lib;

        System.out.println("Agregar libro\n");
        System.out.println("Nombre del libro: ");
        String nombre = Lecturas.leerCadena();
        System.out.println("Autor: ");
        String autor = Lecturas.leerCadena();
        System.out.println("Año de publicación: ");
        int anio = Lecturas.leerEntero(true);
        System.out.println("ISBN: ");
        long isbn = Lecturas.leerEntero(true);
        char estado = ' ';
        while (estado != 'D' && estado != 'P')
        {
            if (estado != 'D' && estado != 'P')
            {
                System.out.println("Estado (D o P):");
                estado = Lecturas.leerCaracter();
            } else
            {
                System.out.println("Por favor ingrese el caracter 'D' o 'P'");
            }
        }

        lib = new Libro(nombre, autor, anio, isbn, estado);

        agregar(lib);
    }

    public static void agregar(Libro lib)
    {
        Nodo nuevo = new Nodo(lib);
        nuevo.siguiente = primero;
        primero = nuevo;
        mapa.put(lib.getIsbn(), lib);
    }

    public static void eliminarLibro(Libro lib)
    {
        if (primero == null)
        {
            return;
        }

        if (primero.lib.equals(lib))
        {
            primero = primero.siguiente;
            return;
        }

        Nodo actual = primero;
        while (actual.siguiente != null && !actual.siguiente.lib.equals(lib))
        {
            actual = actual.siguiente;
        }

        if (actual.siguiente != null)
        {
            actual.siguiente = actual.siguiente.siguiente;
        }

        mapa.remove(lib.getIsbn());
    }

    public static void mostrarLibros()
    {
        Nodo aux = primero;
        if (isEmpty())
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
        mapa.forEach((key, value) -> System.out.println("[Key] : " + key + " [Value] : " + value));
    }

    public static Libro busquedaISBN(long isbn)
    {
        return mapa.get(isbn);
    }

    public static Nodo busquedaTitulo(String titulo)
    {
        Nodo aux = primero;
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
        Nodo aux = primero;
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

    public static void prestar(Libro lib)
    {
        if (primero == null)
        {
            return;
        }

        Nodo actual = primero;
        while (actual != null)
        {
            if (actual.lib.equals(lib))
            {
                actual.lib.setEstado('P');
                mapa.put(lib.getIsbn(), actual.lib);
                System.out.println("Libro prestado correctamente.");
                return;
            }
            actual = actual.siguiente;
        }

        System.out.println("No se encontró el libro en la lista.");
    }

    public static void devolver(Libro lib)
    {
        if (primero == null)
        {
            return;
        }

        Nodo actual = primero;
        while (actual != null)
        {
            if (actual.lib.equals(lib))
            {
                actual.lib.setEstado('R');
                mapa.put(lib.getIsbn(), actual.lib);
                encolar(lib);
                System.out.println("Libro devuelto correctamente.");
                return;
            }
            actual = actual.siguiente;
        }

        System.out.println("No se encontró el libro en la lista.");
    }

    public static void libroDisponible(Libro lib)
    {
        if (primero == null)
        {
            return;
        }

        Nodo actual = primero;
        while (actual != null)
        {
            if (actual.lib.equals(lib))
            {
                actual.lib.setEstado('D');
                mapa.put(lib.getIsbn(), actual.lib);
                encolar(lib);
                System.out.println("Estatus de libro cambiado correctamente.");
                return;
            }
            actual = actual.siguiente;
        }

        System.out.println("No se encontró el libro en la lista.");
    }

    public static boolean colaVacia()
    {
        return frente == null;
    }

    public static void encolar(Libro lib)
    {
        Nodo nuevo = new Nodo(lib);
        if (colaVacia())
        {
            frente = fin = nuevo;
        } else
        {
            fin.siguiente = nuevo;
            fin = nuevo;
        }
    }

    public static Libro desencolar()
    {
        if (colaVacia())
        {
            System.out.println("La cola esta vacia");
        }

        Libro lib = frente.lib;
        frente = frente.siguiente;

        if (frente == null)
        {
            fin = null;
        }

        return lib;
    }

    public static Libro primerLibro()
    {
        if (colaVacia())
        {
            System.out.println("Aun no se ha regresado ningun libro");
            return null;
        }
        return frente.lib;

    }

    public static void librosDevueltos()
    {
        Nodo aux = frente;
        if (colaVacia())
        {
            System.out.println("Aun no se ha devuelto ningun libro");
        } else
        {
            while (aux != null)
            {
                System.out.println(aux.lib.toString());
                aux = aux.siguiente;
            }
        }
    }

    public static void revisar()
    {
        int opc;
        Libro lib = primerLibro();
        System.out.println(lib.toString());

        do
        {
            System.out.println("Libro en buenas condiciones?\n1)Si.\n2)No.\n3)Salir.");
            opc = Lecturas.leerEntero(true);

            switch (opc)
            {
                case 1:
                    desencolar();
                    libroDisponible(lib);
                    System.out.println("Libro devuelto a la lista de libros");
                    break;
                case 2:
                    System.out.println("Colocar sancion?\n1)Si\n1)No");
                    break;

                case 3:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opcion incorrecta");
                    break;
            }
        } while (opc != 3 && opc != 1);

    }

    public static void guardarDatos()
    {
        try
        {
            ObjectOutputStream oosLibros = new ObjectOutputStream(new FileOutputStream("libros.dat"));
            oosLibros.writeObject(primero);
            oosLibros.close();

            ObjectOutputStream oosMapa = new ObjectOutputStream(new FileOutputStream("mapaLibros.dat"));
            oosMapa.writeObject(mapa);
            oosMapa.close();

            ObjectOutputStream oosCola = new ObjectOutputStream(new FileOutputStream("colaLibrosDevueltos.dat"));
            oosCola.writeObject(frente);
            oosCola.writeObject(fin);
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
            ObjectInputStream oisLibros = new ObjectInputStream(new FileInputStream("libros.dat"));
            primero = (Nodo) oisLibros.readObject();
            oisLibros.close();

            ObjectInputStream oisMapa = new ObjectInputStream(new FileInputStream("mapaLibros.dat"));
            mapa = (HashMap<Long, Libro>) oisMapa.readObject();
            oisMapa.close();

            ObjectInputStream oisCola = new ObjectInputStream(new FileInputStream("colaLibrosDevueltos.dat"));
            frente = (Nodo) oisCola.readObject();
            fin = (Nodo) oisCola.readObject();
            oisCola.close();

            System.out.println("Datos cargados correctamente.");

        } catch (IOException | ClassNotFoundException e)
        {
            System.out.println("Error al cargar datos: " + e.getMessage());
        }
    }

}
