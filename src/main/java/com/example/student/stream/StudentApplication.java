package com.example.student.stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import com.student.entity.Student;
import java.util.*;
import com.student.service.StudentsService;


@SpringBootApplication
@ComponentScan(basePackages = {"com.example.student.stream", "com.student.service"})
@EnableJpaRepositories(basePackages = "com.student.repository")
@EntityScan(basePackages = "com.student.entity")
public class StudentApplication implements CommandLineRunner {

    @Autowired
    private StudentsService studentsService;

    public static void main(String[] args) {
        SpringApplication.run(StudentApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        try {
            Student student1 = new Student("Shubhangi", 37);
            Student student2 = new Student("Swaraj", 27);

            studentsService.createStudent(student1);
            studentsService.createStudent(student2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Get all students
        List<Student> allStudents = studentsService.getAllStudents();
        System.out.println("All Students: " + allStudents);

        // Filter students by age
        List<Student> filteredStudents = studentsService.filterStudentsByAge(18);
        System.out.println("Students older than 18: " + filteredStudents);

        // Update student
/*
        Student updatedStudent = new Student("John Doe Updated", 21);
        Student updated = studentsService.updateStudent(student1.getId(), updatedStudent);
        System.out.println("Updated Student: " + updated);
*/

        // Get students sorted by name
        List<Student> sortedStudents = studentsService.getStudentsSortedByName();
        System.out.println("Students Sorted by Name: " + sortedStudents);

        // Get student names in uppercase
        List<String> studentNamesUppercase = studentsService.getStudentNamesUpperCase();
        System.out.println("Student Names in Uppercase: " + studentNamesUppercase);

       /* // Delete student
        studentsService.deleteStudent(student1.getId());
        System.out.println("Deleted student with ID: " + student1.getId());*/
    }

}
