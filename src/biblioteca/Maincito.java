/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package biblioteca;

/**
 *
 * @author Mar
 */
public class Maincito
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
//        Manipulacion.cargarDatos();
//        
//        Libro uno = new Libro("Frankenstein", "Mary Shelley", 2009, 1, 'D');
//        Libro dos = new Libro("Veinte mil leguas de viaje submarino", "Julio Verne", 1870, 2, 'D');
//        Libro tres = new Libro("1984", "George Orwell", 1949, 3, 'D');
//        Libro cuatro = new Libro("Yo, Robot", "Issac Asimov", 1950, 4, 'D');
//        Libro cinco = new Libro("Dune", "Frank Herbert", 1965, 5, 'D');
//        Libro seis = new Libro("La guerra interminable", "Joe Haldeman", 1974, 6, 'D');
//        Libro siete = new Libro("El problema de los tres cuerpos", "Cixin Liu", 2006, 7, 'D');
//        Libro ocho = new Libro("Ready Player One", "Ernest Cline", 2011, 8, 'P');
//        Libro nueve = new Libro("Orgullo y prejuicio", "Jane Austen", 1813, 9, 'D');
//        Libro diez = new Libro("Bajo la misma estrella", "John Green", 2012, 10, 'D');
//
//        Manipulacion.agregar(diez);
//        Manipulacion.agregar(uno);
//        Manipulacion.agregar(dos); 
//       Manipulacion.agregar(tres);
//        Manipulacion.agregar(cuatro);
//        Manipulacion.agregar(cinco);
//        Manipulacion.agregar(seis);
//        Manipulacion.agregar(siete);
//        Manipulacion.agregar(ocho);
//        Manipulacion.agregar(nueve);
//        
//        Manipulacion.guardarDatos();
        
        Sesion.cargarSesion();
        
        Usuario uno = new Usuario("Juan", "Juanito", "1234");
        Usuario dis = new Usuario("Pedro", "pedrito", "1234");
        Administrador adminUno = new Administrador("admin", "admin", "1234");
        Administrador adminDos = new Administrador("AdminDos", "admindos", "1234");
        
        Sesion.guardarSesion();
        
    }

}
