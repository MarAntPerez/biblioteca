package biblioteca;

import biblioteca.pojo.Libro;
import biblioteca.pojo.Usuario;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import poo.Lecturas;

/**
 *
 * @author Mar
 */
public class AccionAdmin
{

    static private String[] menuAdmin =
    {
        "Agregar libro", "Eliminar libro", "Buscar libro", "Prestar libro", "Devolver libro", "Revisar libros devueltos", "Generar reporte", "Salir"
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
                            System.out.println("Eliminar libro?\n1)Sí.\n2)No.");
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
                        if (Sesion.isEmptyUser())
                        {
                            System.out.println("Aun no hay usuarios registrados");
                        } else
                        {
                            System.out.println("Ingrese el Id del usuario: ");
                            String id = Lecturas.leerCadena();
                            Usuario usuario = obtenerUsuario(id);
                            if (usuario != null && usuario.getSancion().equals("No aplica"))
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
                                    prestar(lib, usuario.getId());
                                }
                            } else
                            {
                                System.out.println("Este usuario tiene una sancion, no es posible prestarle un libro");
                            }
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
                    generarReporte();
                    break;

                case 8:
                    Manipulacion.mostrarLibros();
                    break;

                case 9:
                    System.out.println("Saliendo...");
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
        do
        {
            System.out.println("Estado (D = Disponible, P = Prestado):");
            estado = Lecturas.leerCaracter();
            if (estado != 'D' && estado != 'P')
            {
                System.out.println("Por favor ingrese solo 'D' o 'P'");
            }
        } while (estado != 'D' && estado != 'P');

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

    public static void prestar(Libro lib, String idUsuario)
    {
        if (lib.getEstado() != 'D')
        {
            System.out.println("El libro no está disponible para préstamo (Estado actual: " + lib.getEstado() + ")");
            return;
        }

        Nodo actual = ObjetosBiblioteca.primero;
        while (actual != null)
        {
            if (actual.lib.equals(lib))
            {
                actual.lib.setEstado('P');
                actual.lib.setIdUsuarioPrestamo(idUsuario);
                ObjetosBiblioteca.mapa.put(lib.getIsbn(), actual.lib);
                System.out.println("Libro prestado correctamente al usuario con ID: " + idUsuario);
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
            return null;
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
        if (lib == null)
        {
            return;
        }

        System.out.println(lib.toString());
        System.out.println("ID del usuario que lo tenía prestado: " + lib.getIdUsuarioPrestamo());

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
                    String idUsuario = lib.getIdUsuarioPrestamo();
                    Usuario usuario = obtenerUsuario(idUsuario);
                    if (usuario != null)
                    {
                        System.out.println("Colocar sanción al usuario con ID: " + idUsuario + "?\n1)Sí\n2)No");
                        int sancion = Lecturas.leerEntero(true);
                        if (sancion == 1)
                        {
                            sancionar(usuario);
                            System.out.println("Sanción registrada.");
                        }
                    } else
                    {
                        System.out.println("No se pudo aplicar la sanción: usuario no encontrado.");
                    }
                    desencolar();
                    System.out.println("¿El libro aun esta en condiciones de ser usado?\n1)Si.\n2)No.");
                    int dis = Lecturas.leerEntero(true);
                    if (dis == 1)
                    {
                        libroDisponible(lib);
                    } else
                    {
                        eliminarLibro(lib);
                    }
                    break;
                case 3:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción incorrecta");
                    break;
            }
        } while (opc != 3 && opc != 1);
    }

    public static Usuario obtenerUsuario(String id)
    {
        NodoUser aux = ObjetosBiblioteca.primerUser;
        if (Sesion.isEmptyUser())
        {
            System.out.println("No se encontro al usuario");
        } else
        {
            while (aux != null)
            {
                if (aux.user.getId().equals(id))
                {
                    return aux.user;
                }
                aux = aux.siguiente;
            }
        }
        return null;
    }

    public static void sancionar(Usuario usuario)
    {
        NodoUser aux = ObjetosBiblioteca.primerUser;
        while (aux != null)
        {
            if (aux.user.getId().equals(usuario.getId()))
            {
                if (aux.user.getSancion().equals("No aplica"))
                {
                    System.out.println("Ingrese la sancion del usuario: ");
                    String sancion = Lecturas.leerCadena();
                    aux.user.setSancion(sancion);
                    System.out.println("Sancion aplicada correctamente...");
                    return;
                } else
                {
                    System.out.println("Este usuario ya tiene una sancion");
                    return;
                }
            }
        }
    }

    public static Libro[] insertarLibro(Libro libros[], Libro obj)
    {
        if (libros == null)
        {
            libros = new Libro[1];
            libros[0] = obj;
        } else
        {
            Libro tmp[] = new Libro[libros.length + 1];
            System.arraycopy(libros, 0, tmp, 0, libros.length);
            tmp[libros.length] = obj;
            libros = tmp;
        }
        return libros;
    }

    public static void generarReporte()
    {
        ObjetosBiblioteca.librosDisponibles = null;
        ObjetosBiblioteca.librosPrestados = null;

        Nodo aux = ObjetosBiblioteca.primero;
        while (aux != null)
        {
            if (aux.lib.getEstado() == 'D')
            {
                ObjetosBiblioteca.librosDisponibles = insertarLibro(ObjetosBiblioteca.librosDisponibles, aux.lib);
            } else if (aux.lib.getEstado() == 'P')
            {
                ObjetosBiblioteca.librosPrestados = insertarLibro(ObjetosBiblioteca.librosPrestados, aux.lib);
            }
            aux = aux.siguiente;
        }

        if (ObjetosBiblioteca.librosDisponibles == null)
        {
            System.out.println("No hay libros disponibles.");
        } else
        {
            System.out.println("\n--- Libros disponibles ---");
            for (Libro libro : ObjetosBiblioteca.librosDisponibles)
            {
                System.out.println(libro.toString());
            }
            guardaArreglo(ObjetosBiblioteca.librosDisponibles, "Datos/Libros Disponibles.dat");
            guardarReporteTexto(ObjetosBiblioteca.librosDisponibles, "Libros Disponibles");
        }

        if (ObjetosBiblioteca.librosPrestados == null)
        {
            System.out.println("No hay libros prestados.");
        } else
        {
            System.out.println("\n--- Libros prestados ---");
            for (Libro libro : ObjetosBiblioteca.librosPrestados)
            {
                System.out.println(libro.toString());
            }
            guardaArreglo(ObjetosBiblioteca.librosDisponibles, "Datos/Libros Prestados.dat");
            guardarReporteTexto(ObjetosBiblioteca.librosPrestados, "Libros Prestados");
        }
    }

    public static void guardaArreglo(Object[] obj, String nombreArchivo)
    {
        try
        {
            FileOutputStream fos = new FileOutputStream(nombreArchivo);
            try
            {
                ObjectOutputStream arc = new ObjectOutputStream(fos);
                arc.writeObject(obj);
                arc.close();
            } catch (IOException e)
            {
                System.out.println("Error en la entrada salida de datos.");
            }
        } catch (FileNotFoundException e)
        {
            System.out.println("No se encontro el archivo.");
        }
    }

    public static void guardarReporteTexto(Libro[] libros, String nombreArchivo)
    {
        try
        {
            FileWriter fw = new FileWriter("Datos/" + nombreArchivo + ".txt");
            PrintWriter pw = new PrintWriter(fw);

            LocalDate fechaActual = LocalDate.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            pw.println("Fecha: " + fechaActual.format(formato));
            pw.println(nombreArchivo);
            pw.println(String.format("%-40s %-20s %-10s %-10s", "Título", "Autor", "Año", "ISBN"));
            pw.println("--------------------------------------------------------------------------");

            for (Libro libro : libros)
            {
                pw.println(String.format("%-40s %-20s %-10d %-10d",
                        libro.getTitulo(), libro.getAutor(), libro.getAnio(), libro.getIsbn()));
            }

            pw.close();
            fw.close();

            System.out.println("Reporte legible generado correctamente con formato de tabla.");
        } catch (IOException e)
        {
            System.out.println("Error al guardar el archivo de texto: " + e.getMessage());
        }
    }

    public static void guardaArregloPrestados(Object[] obj)
    {
        try
        {
            FileOutputStream fos = new FileOutputStream("Datos/Libros Prestados.dat");
            try
            {
                ObjectOutputStream arc = new ObjectOutputStream(fos);
                arc.writeObject(obj);
                arc.close();
            } catch (IOException e)
            {
                System.out.println("Error en la entrada salida de datos.");
            }
        } catch (FileNotFoundException e)
        {
            System.out.println("No se encontro el archivo.");
        }
    }

    public static Object[] cargaArreglo()
    {
        Object obj[] = null;
        try
        {
            FileInputStream fis = new FileInputStream("Datos/Libros Disponibles.dat");
            try
            {
                ObjectInputStream arc = new ObjectInputStream(fis);
                try
                {
                    obj = (Object[]) arc.readObject();
                } catch (IOException | ClassNotFoundException e)
                {
                    System.out.println("Objeto no encontrado.");
                }
                arc.close();
            } catch (IOException e)
            {
                System.out.println("No se pudo leer el archivo.");
            }
        } catch (FileNotFoundException e)
        {
            System.out.println("No se encontro el archivo.");
        }

        return obj;
    }

}
