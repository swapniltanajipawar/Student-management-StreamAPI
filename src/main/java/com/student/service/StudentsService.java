package com.student.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.student.entity.Student;
import com.student.repository.StudentRepository;
import jakarta.transaction.Transactional;


@Service
@Component
public class StudentsService {

    @Autowired
    private StudentRepository studentRepository;

    @Transactional
    public Student createStudent(@NotNull Student student) throws Exception {
        // Check if a student with the same name already exists
        Optional<Student> existingStudent = studentRepository.findByName(student.getName());
        if (existingStudent.isPresent()) {
            throw new Exception("Student with name " + student.getName() + " already exists");
        }

        // If no duplicate is found, save the new student
        return studentRepository.save(student);
    }

    @Transactional
    public void deleteStudent(Long id) {
        // Deletes a student and ensures that the operation is part of the transaction
        studentRepository.deleteById(id);
    }

    @Transactional
    public Student updateStudent(Long id, Student updatedStudent) {
        // Transaction is started here
        return studentRepository.findById(id).map(student -> {
            student.setName(updatedStudent.getName());
            student.setAge(updatedStudent.getAge());
            return studentRepository.save(student);
        }).orElse(null);
        // Transaction is committed or rolled back based on success or failure
    }

    public List<Student> getAllStudents() {
        // This is a read-only operation, no need for @Transactional here
        return studentRepository.findAll();
    }

    public List<Student> filterStudentsByAge(int minAge) {
        return studentRepository.findAll().stream()
                .filter(student -> student.getAge() > minAge)
                .collect(Collectors.toList());
    }

    public List<Student> getStudentsSortedByName() {
        return studentRepository.findAll().stream()
                .sorted((s1, s2) -> s1.getName().compareToIgnoreCase(s2.getName()))
                .collect(Collectors.toList());
    }

    public List<String> getStudentNamesUpperCase() {
        return studentRepository.findAll().stream()
                .map(student -> student.getName().toUpperCase())
                .collect(Collectors.toList());
    }
}
