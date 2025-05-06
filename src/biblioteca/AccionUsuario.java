package biblioteca;

import static biblioteca.Manipulacion.menu;

/**
 *
 * @author Mar
 */
public class AccionUsuario
{
    static private String menuUser[] =
    {
        "Buscar libro", "Salir"
    };
    
    public static void opcionesUser()
    {
        int opc;

        do
        {
            opc = menu(menuUser, "Menu de usuario");
            switch (opc)
            {
                case 1:
                    AccionPersona.buscar();
                    break;

                case 2:
                    System.out.println("Saliendo");
                    break;

                default:
                    System.out.println("Opcion incorrecta");
                    break;
            }
        } while (opc != menuUser.length);
    }
}
