package com.studnet.Controller;

import com.student.entity.Student;
import com.student.service.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Component
@RestController
@RequestMapping("/students")
public class StudentsController {

    @Autowired
    private StudentsService studentsService;

    @GetMapping
    public List<Student> getAllStudents() {

        return studentsService.getAllStudents();
    }

    @PostMapping("/create")
    public ResponseEntity<String> createStudent(@RequestBody Student student) {
        try {
            studentsService.createStudent(student);
            return ResponseEntity.ok("Student created successfully");
        } catch (Exception e) {
            // If a duplicate name exists, return a bad request response
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/edit/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent) {
        return studentsService.updateStudent(id, updatedStudent);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentsService.deleteStudent(id);
    }

    @GetMapping("/uppercase")
    public List<String> getStudentNamesUpperCase() {
        return studentsService.getStudentNamesUpperCase();
    }

    @GetMapping("/sorted")
    public List<Student> getStudentsSortedByName() {
        return studentsService.getStudentsSortedByName();
    }

    @GetMapping("/filter/age/{minAge}")
    public List<Student> filterStudentsByAge(@PathVariable int minAge) {
        return studentsService.filterStudentsByAge(minAge);
    }
}
