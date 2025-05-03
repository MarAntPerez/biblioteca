package biblioteca;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import poo.Lecturas;

/**
 *
 * @author Mar
 */
public class Sesion
{

    static String menuInicio[] =
    {
        "Administrador.", "Usuario.", "Nueva Cuenta.", "Salir."
    };
    static private String menuNuevo[] =
    {
        "Nuevo Administrador.", "Nuevo Usuario", "Salir"
    };
    static public NodoAdmin primerAdmin;
    static public NodoUser primerUser;

    public static void inicio()
    {
        int opc = 0;
        do
        {
            opc = Manipulacion.menu(menuInicio, "¿Como desea ingresar?");
            switch (opc)
            {
                case 1:
                    ingresar("Administrador");
                    break;

                case 2:
                    ingresar("Usuario");
                    break;

                case 3:
                    nuevo();
                    break;

                case 4:
                    System.out.println("Saliendo");
                    break;

                default:
                    System.out.println("Opcion incorrecta");
                    break;
            }
        } while (opc != menuInicio.length);

    }

    private static void nuevo()
    {
        int opc = 0;
        do
        {
            opc = Manipulacion.menu(menuNuevo, "----------------");
            switch (opc)
            {
                case 1:
                    nuevaPersona("Administrador");
                    break;

                case 2:
                    nuevaPersona("Usuario");
                    break;

                case 3:
                    System.out.println("Saliendo");
                    break;

                default:
                    System.out.println("opcion incorrecta");
                    break;
            }
        } while (opc != menuNuevo.length);

    }

    private static void nuevaPersona(String tipo)
    {
        System.out.println("Ingrese su nombre: ");
        String nombre = Lecturas.leerCadena();
        System.out.println("Ingrese su usuario: ");
        String user = Lecturas.leerCadena();
        System.out.println("Ingrese su cointrasenia: ");
        String contrasenia = Lecturas.leerCadena();

        if (tipo.equals("Administrador"))
        {
            Administrador admin = new Administrador(nombre, user, contrasenia);
            NodoAdmin nuevo = new NodoAdmin(admin);
            nuevo.siguiente = primerAdmin;
            primerAdmin = nuevo;
            System.out.println(tipo + "agregado con exito.");
        } else if (tipo.equals("Usuario"))
        {
            Usuario usuario = new Usuario(nombre, user, contrasenia);
            NodoUser nuevo = new NodoUser(usuario);
            nuevo.siguiente = primerUser;
            primerUser = nuevo;
            System.out.println(tipo + "agregado con exito.");
        } else
        {
            System.out.println("No se ha podido ingresar al usuario");
        }
    }

    private static boolean isEmptyAdmin()
    {
        return (primerAdmin == null);
    }

    private static boolean isEmptyUser()
    {
        return (primerUser == null);
    }

    private static void ingresar(String tipo)
    {
        boolean encontrado = false;

        if (tipo.equals("Administrador"))
        {
            if (isEmptyAdmin())
            {
                System.out.println("Aun no se ha registrado ningun administrador");
                return;
            }

            do
            {
                System.out.println("Ingrese su nombre de usuario: ");
                String user = Lecturas.leerCadena();
                System.out.println("Ingrese su contrasenia: ");
                String contrasenia = Lecturas.leerCadena();

                NodoAdmin aux = primerAdmin;
                while (aux != null)
                {
                    if (aux.admin.getUser().equals(user) && aux.admin.getContrasenia().equals(contrasenia))
                    {
                        System.out.println("Bienvenido " + aux.admin.getNombre());
                        Manipulacion.opcionesAdmin();
                        encontrado = true;
                        break;
                    }
                    aux = aux.siguiente;
                }

                if (!encontrado)
                {
                    System.out.println("Usuario o contrasenia incorrectos. Intente de nuevo.\n");
                }

            } while (!encontrado);

        } else if (tipo.equals("Usuario"))
        {
            if (isEmptyUser())
            {
                System.out.println("Aun no se ha registrado ningun usuario");
                return;
            }

            do
            {
                System.out.println("Ingrese su nombre de usuario: ");
                String user = Lecturas.leerCadena();
                System.out.println("Ingrese su contrasenia: ");
                String contrasenia = Lecturas.leerCadena();

                NodoUser aux = primerUser;
                while (aux != null)
                {
                    if (aux.user.getUser().equals(user) && aux.user.getContrasenia().equals(contrasenia))
                    {
                        System.out.println("Bienvenido " + aux.user.getNombre());
                        Manipulacion.opcionesUser();
                        encontrado = true;
                        break;
                    }
                    aux = aux.siguiente;
                }

                if (!encontrado)
                {
                    System.out.println("Usuario o contrasenia incorrectos. Intente de nuevo.\n");
                }

            } while (!encontrado);
        }
    }

    public static void guardarSesion()
    {
        try
        {
            ObjectOutputStream oosAdmin = new ObjectOutputStream(new FileOutputStream("admins.dat"));
            oosAdmin.writeObject(primerAdmin);
            oosAdmin.close();

            ObjectOutputStream oosUser = new ObjectOutputStream(new FileOutputStream("users.dat"));
            oosUser.writeObject(primerUser);
            oosUser.close();

            System.out.println("Sesión guardada correctamente.");

        } catch (IOException e)
        {
            System.out.println("Error al guardar la sesión: " + e.getMessage());
        }
    }

    public static void cargarSesion()
    {
        try
        {
            File adminFile = new File("admins.dat");
            if (adminFile.exists())
            {
                ObjectInputStream oisAdmin = new ObjectInputStream(new FileInputStream(adminFile));
                primerAdmin = (NodoAdmin) oisAdmin.readObject();
                oisAdmin.close();
            } else
            {
                primerAdmin = null;
            }
        } catch (IOException | ClassNotFoundException e)
        {
            System.out.println("Error al cargar administradores: " + e.getMessage());
            primerAdmin = null;
        }

        try
        {
            File userFile = new File("users.dat");
            if (userFile.exists())
            {
                ObjectInputStream oisUser = new ObjectInputStream(new FileInputStream(userFile));
                primerUser = (NodoUser) oisUser.readObject();
                oisUser.close();
            } else
            {
                primerUser = null;
            }
        } catch (IOException | ClassNotFoundException e)
        {
            System.out.println("Error al cargar usuarios: " + e.getMessage());
            primerUser = null;
        }

        System.out.println("Sesión cargada correctamente (o inicializada si era nueva).");
    }

}
