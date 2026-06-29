package com.raif.enrollment.service;

import com.raif.enrollment.model.Course;
import com.raif.enrollment.model.Enrollment;
import com.raif.enrollment.model.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Handles enrollment-related business logic.
 * This class connects students and courses without putting that logic in the UI.
 */
public class EnrollmentService {
    private final List<Enrollment> enrollments = new ArrayList<>();

    public boolean enrollStudent(Student student, Course course) {
        if (isAlreadyEnrolled(student, course)) {
            return false;
        }

        enrollments.add(new Enrollment(student, course));
        return true;
    }

    public boolean isAlreadyEnrolled(Student student, Course course) {
        for (Enrollment enrollment : enrollments) {
            boolean sameStudent = enrollment.getStudent().getId().equalsIgnoreCase(student.getId());
            boolean sameCourse = enrollment.getCourse().getCode().equalsIgnoreCase(course.getCode());

            if (sameStudent && sameCourse) {
                return true;
            }
        }

        return false;
    }

    public List<Enrollment> getAllEnrollments() {
        return Collections.unmodifiableList(enrollments);
    }

    public boolean hasEnrollments() {
        return !enrollments.isEmpty();
    }

    public void replaceEnrollments(List<Enrollment> loadedEnrollments) {
        enrollments.clear();
        enrollments.addAll(loadedEnrollments);
    }
}