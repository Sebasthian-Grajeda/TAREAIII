import exceptions.StudentNotFoundException;
import models.Student;
import services.StudentManager;
import services.StudentManagerImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        StudentManager studentManager = (StudentManager) new StudentManagerImpl();

        studentManager.addStudent(new Student(1, "Carlos Garcia", 27));
        studentManager.addStudent(new Student(2, "Shuko Garcia Rodriguez", 100));
        studentManager.addStudent(new Student(3, "Fernanda Rodriguez", 19));

        System.out.println("Lista completa de estudiantes:");
        List<Student> allStudents = studentManager.getAllStudents();
        allStudents.forEach(System.out::println);

        try {
            Student student = studentManager.findStudent(2);
            System.out.println("\nEstudiante encontrado: " + student);
        } catch (StudentNotFoundException e) {
            System.err.println(e.getMessage());
        }

        System.out.println("\nEstudiantes mayores de 21 años:");
        allStudents.stream()
                .filter(s -> s.getAge() > 21)
                .forEach(System.out::println);

        System.out.println("\nEstudiantes ordenados por nombre:");
        allStudents.stream()
                .sorted((s1, s2) -> s1.getName().compareTo(s2.getName()))
                .forEach(System.out::println);

        // Eliminar estudiante por ID
        studentManager.removeStudent(1);
        System.out.println("\nLista de estudiantes después de eliminar al estudiante con ID 1:");
        studentManager.getAllStudents().forEach(System.out::println);
    }
}