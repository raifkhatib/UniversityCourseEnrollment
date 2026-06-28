package com.raif.enrollment.model;

import java.time.LocalDate;

/**
 * Represents a student's enrollment in a course.
 * This class demonstrates composition because it contains
 * a Student object and a Course object.
 */
public class Enrollment {
    private final Student student;
    private final Course course;
    private final LocalDate enrollmentDate;

    public Enrollment(Student student, Course course) {
        this.student = student;
        this.course = course;
        this.enrollmentDate = LocalDate.now();
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    @Override
    public String toString() {
        return "Student: " + student.getName()
                + " | Course: " + course.getTitle()
                + " | Date: " + enrollmentDate;
    }
}