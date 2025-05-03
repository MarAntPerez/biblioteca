package biblioteca;

import java.util.List;

public class Main
{

    public static void main(String[] args)
    {
        Libro uno = new Libro("Frankenstein", "Mary Shelley", 2009, 1, 'D');
        Libro dos = new Libro("Veinte mil leguas de viaje submarino", "Julio Verne", 1870, 2, 'D');
        Libro tres = new Libro("1984", "George Orwell", 1949, 3, 'D');
        Libro cuatro = new Libro("Yo, Robot", "Issac Asimov", 1950, 4, 'D');
        Libro cinco = new Libro("Dune", "Frank Herbert", 1965, 5, 'D');
        Libro seis = new Libro("La guerra interminable", "Joe Haldeman", 1974, 6, 'D');
        Libro siete = new Libro("El problema de los tres cuerpos", "Cixin Liu", 2006, 7, 'D');
        Libro ocho = new Libro("Ready Player One", "Ernest Cline", 2011, 8, 'P');
        Libro nueve = new Libro("Orgullo y prejuicio", "Jane Austen", 1813, 9, 'D');
        Libro diez = new Libro("Bajo la misma estrella", "John Green", 2012, 10, 'D');

        Manipulacion.agregar(diez);
        Manipulacion.agregar(uno);
        Manipulacion.agregar(dos);
        Manipulacion.agregar(tres);
        Manipulacion.agregar(cuatro);
        Manipulacion.agregar(cinco);
        Manipulacion.agregar(seis);
        Manipulacion.agregar(siete);
        Manipulacion.agregar(ocho);
        Manipulacion.agregar(nueve);
        
        Manipulacion.mostrarLibros();
        
        Manipulacion.eliminarLibro(siete);
        
        System.out.println("----------------------------------------------------");
        System.out.println("Muestra despues de eliminar libro 7");
        
        Manipulacion.mostrarLibros();
        
        Manipulacion.leerDatos();
        
        System.out.println("----------------------------------------------------");
        System.out.println("Muestra despues de agregar libro 7");
        Manipulacion.mostrarLibros();
        
        
        System.out.println("Muestra por HashMap");
        Manipulacion.mostrarHashMap();
        
        System.out.println("Obtner libro en isbn x");
        Libro obtenido = Manipulacion.busquedaISBN(15);
        if(obtenido == null){
            System.out.println("Error, libro no encontrado");
        }else{
            System.out.println(obtenido.toString());
        }
        
        System.out.println("\nObtener por titulo");
        Nodo obtenidoDos = Manipulacion.busquedaTitulo("Frankenstein");
        if(obtenidoDos == null){
            System.out.println("Error, libro no encontrado");
        }else{
            System.out.println(obtenidoDos.lib.toString());
        }
        
        System.out.println("\nObtener por autor");
        List<Libro> librosAutor = Manipulacion.busquedaAutor("Jane Austen");
        if(librosAutor == null){
            System.out.println("Error, autor sin libros o libro no encontrado");
        }else{
            for (int i = 0; i < librosAutor.size(); i++)
            {
                System.out.println(librosAutor.toString());
            }
        }
        
    }
}