package biblioteca;

import biblioteca.pojo.Usuario;
import biblioteca.pojo.Administrador;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
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

        Sesion.guardarSesion();
        Manipulacion.guardarDatos();

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
        boolean valido = false;
        String user;
        System.out.println("Ingrese su nombre: ");
        String nombre = Lecturas.leerCadena();
        do
        {
            System.out.println("Ingrese su usuario: ");
            user = Lecturas.leerCadena();
            valido = validaUsuario(user);
            if (!valido)
            {
                System.out.println("El usuario ya existe");
            }
        } while (!valido);
        System.out.println("Ingrese su cointrasenia: ");
        String contrasenia = Lecturas.leerCadena();

        if (tipo.equals("Administrador"))
        {
            Administrador admin = new Administrador(nombre, user, contrasenia);
            agregarAdmin(admin);
            System.out.println(" Administrador agregado con exito.");
        } else if (tipo.equals("Usuario"))
        {
            Usuario usuario = new Usuario(nombre, user, contrasenia);
            agregarUsuario(usuario);
            System.out.println(tipo + "agregado con exito.");
        } else
        {
            System.out.println("No se ha podido ingresar al usuario");
        }
    }

    public static void agregarAdmin(Administrador admin)
    {
        NodoAdmin nuevo = new NodoAdmin(admin);
        nuevo.siguiente = ObjetosBiblioteca.primerAdmin;
        ObjetosBiblioteca.primerAdmin = nuevo;
    }

    public static void agregarUsuario(Usuario usuario)
    {
        NodoUser nuevo = new NodoUser(usuario);
        nuevo.siguiente = ObjetosBiblioteca.primerUser;
        ObjetosBiblioteca.primerUser = nuevo;
    }

    public static boolean validaUsuario(String user)
    {
        NodoAdmin auxAdmin = ObjetosBiblioteca.primerAdmin;
        while (auxAdmin != null)
        {
            if (auxAdmin.admin.getUser().equalsIgnoreCase(user))
            {
                return false;
            }
            auxAdmin = auxAdmin.siguiente;
        }

        NodoUser auxUser = ObjetosBiblioteca.primerUser;
        while (auxUser != null)
        {
            if (auxUser.user.getUser().equalsIgnoreCase(user))
            {
                return false;
            }
            auxUser = auxUser.siguiente;
        }

        return true;
    }

    private static boolean isEmptyAdmin()
    {
        return (ObjetosBiblioteca.primerAdmin == null);
    }

    public static boolean isEmptyUser()
    {
        return (ObjetosBiblioteca.primerUser == null);
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

                NodoAdmin aux = ObjetosBiblioteca.primerAdmin;
                while (aux != null)
                {
                    if (aux.admin.getUser().equals(user) && aux.admin.getContrasenia().equals(contrasenia))
                    {
                        System.out.println("Bienvenido " + aux.admin.getNombre());
                        AccionAdmin.opcionesAdmin();
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

                NodoUser aux = ObjetosBiblioteca.primerUser;
                while (aux != null)
                {
                    if (aux.user.getUser().equals(user) && aux.user.getContrasenia().equals(contrasenia))
                    {
                        System.out.println("Bienvenido " + aux.user.getNombre());
                        AccionUsuario.opcionesUser();
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
            ObjectOutputStream oosAdmin = new ObjectOutputStream(new FileOutputStream("Datos/Administradores.dat"));
            oosAdmin.writeObject(ObjetosBiblioteca.primerAdmin);
            oosAdmin.close();

            ObjectOutputStream oosUser = new ObjectOutputStream(new FileOutputStream("Datos/Usuarios.dat"));
            oosUser.writeObject(ObjetosBiblioteca.primerUser);
            oosUser.close();

            ObjectOutputStream oosContAdmin = new ObjectOutputStream(new FileOutputStream("Datos/ids_Admin.dat"));
            oosContAdmin.writeObject(Administrador.getCons());
            oosContAdmin.close();

            ObjectOutputStream oosContUser = new ObjectOutputStream(new FileOutputStream("Datos/ids_User.dat"));
            oosContUser.writeObject(Usuario.getCons());
            oosContUser.close();

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
            File adminFile = new File("Datos/Administradores.dat");
            if (adminFile.exists())
            {
                ObjectInputStream oisAdmin = new ObjectInputStream(new FileInputStream(adminFile));
                ObjetosBiblioteca.primerAdmin = (NodoAdmin) oisAdmin.readObject();
                oisAdmin.close();
            } else
            {
                ObjetosBiblioteca.primerAdmin = null;
            }
        } catch (IOException | ClassNotFoundException e)
        {
            System.out.println("Error al cargar administradores: " + e.getMessage());
            ObjetosBiblioteca.primerAdmin = null;
        }

        try
        {
            File userFile = new File("Datos/Usuarios.dat");
            if (userFile.exists())
            {
                ObjectInputStream oisUser = new ObjectInputStream(new FileInputStream(userFile));
                ObjetosBiblioteca.primerUser = (NodoUser) oisUser.readObject();
                oisUser.close();
            } else
            {
                ObjetosBiblioteca.primerUser = null;
            }
        } catch (IOException | ClassNotFoundException e)
        {
            System.out.println("Error al cargar usuarios: " + e.getMessage());
            ObjetosBiblioteca.primerUser = null;
        }
        
        try{
            File idsUser = new File("Datos/ids_User.dat");
            if(idsUser.exists()){
                ObjectInputStream oisIdsUser = new ObjectInputStream(new FileInputStream(idsUser));
                int consUser = (Integer) oisIdsUser.readObject();
                Usuario.setCons(consUser);
                oisIdsUser.close();
            }
        }catch (IOException | ClassNotFoundException e)
        {
            System.out.println("Error al cargar ids de usuarios: " + e.getMessage());
            ObjetosBiblioteca.primerUser = null;
        }
        
        
        try{
            File idsAdmin = new File("Datos/ids_Admin.dat");
            if(idsAdmin.exists()){
                ObjectInputStream oisIdsUser = new ObjectInputStream(new FileInputStream(idsAdmin));
                int consAdmin = (Integer) oisIdsUser.readObject();
                Administrador.setCons(consAdmin);
                oisIdsUser.close();
            }
        }catch (IOException | ClassNotFoundException e)
        {
            System.out.println("Error al cargar ids de usuarios: " + e.getMessage());
            ObjetosBiblioteca.primerUser = null;
        }
        
        System.out.println("Sesión cargada correctamente (o inicializada si era nueva).");
    }

}
