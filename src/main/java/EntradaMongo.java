import modelo.Alumno;
import modelo.Profesor;
import DAO.AlumnoDAO;
import DAO.ProfesorDAO;

import java.util.ArrayList;
import java.util.Scanner;

public class EntradaMongo {
    public static void main(String[] args) {
        AlumnoDAO alumnoDAO = new AlumnoDAO();
        ProfesorDAO profesorDAO = new ProfesorDAO();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Seleccione una opción:");
            System.out.println("1- Insertar un profesor");
            System.out.println("2- Insertar un alumno");
            System.out.println("3- Mostrar todos los datos");
            System.out.println("4- Mostrar profesores");
            System.out.println("5- Mostrar alumnos");
            System.out.println("6- Buscar alumno por email");
            System.out.println("7- Buscar profesor por rango de edad");
            System.out.println("8- Actualizar rating de un profesor");
            System.out.println("9- Dar de baja alumnos con calificación mayor que 5");
            System.out.println("10- Salir");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> {
                    Profesor profesor = new Profesor();
                    System.out.print("Ingrese el nombre del profesor: ");
                    profesor.setName(scanner.nextLine());
                    System.out.print("Ingrese el género del profesor: ");
                    profesor.setGender(scanner.nextLine());
                    System.out.print("Ingrese el email del profesor: ");
                    profesor.setEmail(scanner.nextLine());
                    System.out.print("Ingrese el título del profesor: ");
                    profesor.setTitle(scanner.nextLine());

                    System.out.print("Ingrese la edad del profesor: ");
                    profesor.setAge(scanner.nextInt());
                    System.out.print("Ingrese el rating del profesor: ");
                    profesor.setRating(scanner.nextDouble());
                    scanner.nextLine();
                    profesor.setSubjects(new ArrayList<>());
                    System.out.println("Ingrese las materias del profesor (ingrese 'fin' para terminar):");
                    while (true) {
                        String subject = scanner.nextLine();
                        if (subject.equalsIgnoreCase("fin")) break;
                        profesor.getSubjects().add(subject);
                    }

                    profesorDAO.insertProfesor(profesor);
                }
                case 2 -> {
                    Alumno alumno = new Alumno();
                    System.out.print("Ingrese el nombre del alumno: ");
                    alumno.setName(scanner.nextLine());
                    System.out.print("Ingrese el género del alumno: ");
                    alumno.setGender(scanner.nextLine());
                    System.out.print("Ingrese el email del alumno: ");
                    alumno.setEmail(scanner.nextLine());
                    System.out.print("Ingrese el teléfono del alumno: ");
                    alumno.setPhone(scanner.nextLine());

                    System.out.print("Ingrese la edad del alumno: ");
                    alumno.setAge(scanner.nextInt());
                    System.out.print("Ingrese la calificación del alumno: ");
                    alumno.setCalification(scanner.nextInt());
                    System.out.print("Ingrese la calificación promedio del alumno: ");
                    alumno.setRating(scanner.nextDouble());
                    scanner.nextLine();

                    alumnoDAO.insertAlumno(alumno);
                }
                case 3 -> {
                    System.out.println("Alumnos:");
                    for (Alumno alumno : alumnoDAO.getAlumnos()) {
                        System.out.println(alumno);
                    }
                    System.out.println("Profesores:");
                    for (Profesor profesor : profesorDAO.getProfesores()) {
                        System.out.println(profesor);
                    }
                }
                case 4 -> {
                    ArrayList<Profesor> profesores = profesorDAO.getProfesores();
                    for (Profesor profesor : profesores) {
                        System.out.println(profesor);
                    }
                }
                case 5 -> {
                    ArrayList<Alumno> alumnos = alumnoDAO.getAlumnos();
                    for (Alumno alumno : alumnos) {
                        System.out.println(alumno);
                    }
                }
                case 6 -> {
                    System.out.print("Ingrese el email del alumno: ");
                    String email = scanner.nextLine();
                    Alumno alumno = alumnoDAO.getAlumnoByEmail(email);
                    if (alumno != null) {
                        System.out.println(alumno);
                    } else {
                        System.out.println("Alumno no encontrado");
                    }
                }
                case 7 -> {
                    System.out.print("Ingrese la edad mínima: ");
                    int minAge = scanner.nextInt();
                    System.out.print("Ingrese la edad máxima: ");
                    int maxAge = scanner.nextInt();
                    scanner.nextLine();
                    ArrayList<Profesor> profesores = profesorDAO.findProfesoresByAgeRange(minAge, maxAge);
                    if (profesores.isEmpty()) {
                        System.out.println("No se encontraron profesores en ese rango de edad");
                    } else {
                        for (Profesor profesor : profesores) {
                            System.out.println(profesor);
                        }
                    }
                }
                case 8 -> {
                    System.out.print("Ingrese el email del profesor: ");
                    String email = scanner.nextLine();
                    System.out.print("Ingrese el nuevo rating: ");
                    double nuevoRating = scanner.nextDouble();
                    scanner.nextLine();
                    boolean actualizado = profesorDAO.updateProfesorRatingByEmail(email, nuevoRating);
                    if (actualizado) {
                        System.out.println("Rating actualizado");
                    } else {
                        System.out.println("Profesor no encontrado");
                    }
                }
                case 9 -> {
                    alumnoDAO.deleteAlumnosByCalification(5);
                    System.out.println("Alumnos eliminados.");
                }
                case 10 -> exit = true;
                default -> System.out.println("Opción no válida.");
            }
        }

        scanner.close();
    }
}
