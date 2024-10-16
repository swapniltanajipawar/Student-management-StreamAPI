package com.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.student.entity.Student;
import java.util.*;
public interface StudentRepository extends JpaRepository<Student, Long>{

    Optional<Student> findByName(String name);
}
