package biblioteca;

import biblioteca.pojo.Libro;
import poo.Lecturas;

/**
 *
 * @author Mar
 */
public class AccionAdmin
{
    static private String[] menuAdmin =
    {
        "Agregar libro", "Eliminar libro", "Buscar libro", "Prestar libro", "Devolver libro", "Revisar libros devueltos", "Salir"
    };

    public static void opcionesAdmin()
    {
        int opc;

        do
        {
            opc = Manipulacion.menu(menuAdmin, "Menu Administrador");

            switch (opc)
            {
                case 1:
                    leerDatos();
                    break;

                case 2:
                    if (AccionPersona.isEmpty())
                    {
                        System.out.println("Aun no hay ningun libro registrado");
                    } else
                    {
                        System.out.println("Ingrese el ISBN del libro que desea eliminar: ");
                        int isbn = Lecturas.leerEntero(true);
                        Libro lib = AccionPersona.busquedaISBN(isbn);
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
                    AccionPersona.buscar();
                    break;

                case 4:
                    if (AccionPersona.isEmpty())
                    {
                        System.out.println("Aun no hay libros registrados");
                    } else
                    {
                        System.out.println("Ingrese el isbn del libro");
                        int isbn = Lecturas.leerEntero(true);
                        Libro lib = AccionPersona.busquedaISBN(isbn);
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
                    if (AccionPersona.isEmpty())
                    {
                        System.out.println("Aun no hay libros registrados");
                    } else
                    {
                        System.out.println("Ingres el ISBN del libro");
                        int isbn = Lecturas.leerEntero(true);
                        Libro lib = AccionPersona.busquedaISBN(isbn);
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
                    
                case 8:
                    Manipulacion.mostrarLibros();
                    break;

                default:
                    System.out.println("Opcion incorrecta...");
                    break;
            }
        } while (opc != menuAdmin.length);

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
        nuevo.siguiente = ObjetosBiblioteca.primero;
        ObjetosBiblioteca.primero = nuevo;
        ObjetosBiblioteca.mapa.put(lib.getIsbn(), lib);
    }

    public static void eliminarLibro(Libro lib)
    {
        if (ObjetosBiblioteca.primero == null)
        {
            return;
        }

        if (ObjetosBiblioteca.primero.lib.equals(lib))
        {
            ObjetosBiblioteca.primero = ObjetosBiblioteca.primero.siguiente;
            return;
        }

        Nodo actual = ObjetosBiblioteca.primero;
        while (actual.siguiente != null && !actual.siguiente.lib.equals(lib))
        {
            actual = actual.siguiente;
        }

        if (actual.siguiente != null)
        {
            actual.siguiente = actual.siguiente.siguiente;
        }

        ObjetosBiblioteca.mapa.remove(lib.getIsbn());
    }

    public static void prestar(Libro lib)
    {
        if (ObjetosBiblioteca.primero == null)
        {
            return;
        }

        Nodo actual = ObjetosBiblioteca.primero;
        while (actual != null)
        {
            if (actual.lib.equals(lib))
            {
                actual.lib.setEstado('P');
                ObjetosBiblioteca.mapa.put(lib.getIsbn(), actual.lib);
                System.out.println("Libro prestado correctamente.");
                return;
            }
            actual = actual.siguiente;
        }

        System.out.println("No se encontró el libro en la lista.");
    }

    public static void devolver(Libro lib)
    {
        if (ObjetosBiblioteca.primero == null)
        {
            return;
        }

        Nodo actual = ObjetosBiblioteca.primero;
        while (actual != null)
        {
            if (actual.lib.equals(lib))
            {
                actual.lib.setEstado('R');
                ObjetosBiblioteca.mapa.put(lib.getIsbn(), actual.lib);
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
        if (ObjetosBiblioteca.primero == null)
        {
            return;
        }

        Nodo actual = ObjetosBiblioteca.primero;
        while (actual != null)
        {
            if (actual.lib.equals(lib))
            {
                actual.lib.setEstado('D');
                ObjetosBiblioteca.mapa.put(lib.getIsbn(), actual.lib);
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
        return ObjetosBiblioteca.frente == null;
    }

    public static void encolar(Libro lib)
    {
        Nodo nuevo = new Nodo(lib);
        if (colaVacia())
        {
            ObjetosBiblioteca.frente = ObjetosBiblioteca.fin = nuevo;
        } else
        {
            ObjetosBiblioteca.fin.siguiente = nuevo;
            ObjetosBiblioteca.fin = nuevo;
        }
    }

    public static Libro desencolar()
    {
        if (colaVacia())
        {
            System.out.println("La cola esta vacia");
        }

        Libro lib = ObjetosBiblioteca.frente.lib;
        ObjetosBiblioteca.frente = ObjetosBiblioteca.frente.siguiente;

        if (ObjetosBiblioteca.frente == null)
        {
            ObjetosBiblioteca.fin = null;
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
        return ObjetosBiblioteca.frente.lib;

    }

    public static void librosDevueltos()
    {
        Nodo aux = ObjetosBiblioteca.frente;
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
}
