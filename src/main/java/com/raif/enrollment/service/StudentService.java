package com.raif.enrollment.service;

import com.raif.enrollment.model.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Handles all student-related business logic.
 * This class keeps student management separate from the console menu.
 */
public class StudentService {
    private final List<Student> students = new ArrayList<>();

    public boolean addStudent(Student student) {
        if (findStudentById(student.getId()).isPresent()) {
            return false;
        }

        students.add(student);
        return true;
    }

    public Optional<Student> findStudentById(String id) {
        for (Student student : students) {
            if (student.getId().equalsIgnoreCase(id)) {
                return Optional.of(student);
            }
        }

        return Optional.empty();
    }

    public List<Student> getAllStudents() {
        return Collections.unmodifiableList(students);
    }

    public boolean hasStudents() {
        return !students.isEmpty();
    }
}