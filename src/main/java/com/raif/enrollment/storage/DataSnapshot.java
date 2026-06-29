package com.raif.enrollment.storage;

import com.raif.enrollment.model.Course;
import com.raif.enrollment.model.Enrollment;
import com.raif.enrollment.model.Student;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Stores a complete snapshot of the application's data.
 * This class is used when saving and loading data from a file.
 */
public class DataSnapshot implements Serializable {
    private static final long serialVersionUID = 1L;

    private final List<Student> students;
    private final List<Course> courses;
    private final List<Enrollment> enrollments;

    public DataSnapshot(List<Student> students, List<Course> courses, List<Enrollment> enrollments) {
        this.students = new ArrayList<>(students);
        this.courses = new ArrayList<>(courses);
        this.enrollments = new ArrayList<>(enrollments);
    }

    public List<Student> getStudents() {
        return Collections.unmodifiableList(students);
    }

    public List<Course> getCourses() {
        return Collections.unmodifiableList(courses);
    }

    public List<Enrollment> getEnrollments() {
        return Collections.unmodifiableList(enrollments);
    }
}