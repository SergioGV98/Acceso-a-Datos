package repasoacdt2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class RepasoACDT2 {

    public static void main(String[] args) {
        String basedatos = "repaso";
        String user = "usupruebasprog";
        String pwd = "usupruebasprog";
        String host = "localhost";
        String port = "3306";
        String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + basedatos;

        String[] sentenciasTablasBD = {"CREATE TABLE Autores (AutorID INT PRIMARY KEY AUTO_INCREMENT, Nombre VARCHAR(50), Nacionalidad VARCHAR(50));", "CREATE TABLE Generos (GeneroID INT PRIMARY KEY, Nombre VARCHAR(50));",
            "CREATE TABLE Libros (LibroID INT PRIMARY KEY, Titulo VARCHAR(100), AutorID INT, GeneroID INT, FOREIGN KEY (AutorID) REFERENCES Autores(AutorID), FOREIGN KEY (GeneroID) REFERENCES Generos(GeneroID));",
            "CREATE TABLE Prestamos (PrestamoID INT PRIMARY KEY, LibroID INT, FechaPrestamo DATE, FechaDevolucion DATE, FOREIGN KEY (LibroID) REFERENCES Libros(LibroID));"};

        Autor[] autores = {new Autor("Brandon Sanderson", "Estadounidense"), new Autor("J.K. Rowling", "Britanica"), new Autor("George R.R. Martin", "Estadounidense")};

        try (Connection c = DriverManager.getConnection(urlConnection, user, pwd); var tablas = c.createStatement()) {

            // Crear las tablas en la base de datos y si estan repetidas imprimo el error
            System.out.println("--------------------------CREAR LAS TABLAS EN LA BASE DE DATOS--------------------------");
            for (String sentencias : sentenciasTablasBD) {
                try {
                    tablas.execute(sentencias);
                } catch (SQLException e) {
                    System.out.printf("ERROR: %s\n", e.getMessage());
                }
            }
            System.out.println();

            // Crear los autores en la base de datos, si existen te devuelve el autor, sus datos.
            System.out.println("--------------------------CREAR LOS AUTORES EN LA BASE DE DATOS--------------------------");
            try {
                for (Autor autor : autores) {
                    Autor autorActual = autor.insertDB(c, autor);
                    if (autorActual != null) {
                        System.out.println(autorActual.toString());
                    }
                }
            } catch (SQLException e) {
                System.out.printf("ERROR: %s\n", e.getMessage());
            }
            System.out.println();

            // Me devuelve un arrayList de autores ordenados ASC por su nombre
            System.out.println("--------------------------ORDENADOR POR NOMBRE ASC--------------------------");
            try {
                Autor autor = new Autor();
                ArrayList<String> arr = autor.autoresOrdenadosAlfabeticamente(c);
                System.out.println(arr.toString());
            } catch (SQLException e) {
                System.out.printf("ERROR: %s\n", e.getMessage());
            }
            System.out.println();

            // Me devuelve un arrayList de autores ordenados ASC por lo que quiera el usuario
            System.out.println("--------------------------ORDENADOR POR LO QUE ELIGA EL USUARIO--------------------------");
            try {
                Autor autor = new Autor();
                ArrayList<String> arr = autor.autoresOrdenadosAlfabeticamente(c, "Nacionalidad");
                System.out.println(arr.toString());
            } catch (SQLException e) {
                System.out.printf("ERROR: %s\n", e.getMessage());
            }
            System.out.println();

            // Insertar manualmente los generos en la base de datos | Para hacerlo mas elegante coger y meterlo todo en un array.
            System.out.println("--------------------------INSERTAR A MANO CADA GENERO--------------------------");
            try {
                System.out.println(Genero.insertDB(c, 1, "Fantasía"));
                System.out.println(Genero.insertDB(c, 2, "Ciencia Ficción"));
                System.out.println(Genero.insertDB(c, 3, "Magia"));
                System.out.println(Genero.insertDB(c, 4, "Aventura"));
            } catch (SQLException e) {
                System.out.printf("ERROR: %s\n", e.getMessage());
            }
            System.out.println();

            // Buscar el nombre de un genero por su ID
            System.out.println("--------------------------BUSCAR EL NOMBRE DE UN GENERO POR ID--------------------------");
            try {
                Scanner sc = new Scanner(System.in);
                System.out.println(Genero.consultarGenero(c, Integer.parseInt(sc.nextLine())));
            } catch (SQLException e) {
                System.out.printf("ERROR: %s\n", e.getMessage());
            }
            System.out.println();

        } catch (SQLException e) {
            muestraErrorSQL(e);
        }

    }

    public static void muestraErrorSQL(SQLException e) {
        System.out.printf("SQL ERROR mensaje: %s\n", e.getMessage());
        System.out.printf("SQL Estado: %s\n", e.getSQLState());
        System.out.printf("SQL código específico: %s\n", e.getErrorCode());
    }

}
