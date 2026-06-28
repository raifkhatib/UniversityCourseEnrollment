package com.raif.enrollment.service;

import com.raif.enrollment.model.Course;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Handles all course-related business logic.
 * This keeps course management separate from the console menu.
 */
public class CourseService {
    private final List<Course> courses = new ArrayList<>();

    public boolean addCourse(Course course) {
        if (findCourseByCode(course.getCode()).isPresent()) {
            return false;
        }

        courses.add(course);
        return true;
    }

    public Optional<Course> findCourseByCode(String code) {
        for (Course course : courses) {
            if (course.getCode().equalsIgnoreCase(code)) {
                return Optional.of(course);
            }
        }

        return Optional.empty();
    }

    public List<Course> getAllCourses() {
        return Collections.unmodifiableList(courses);
    }

    public boolean hasCourses() {
        return !courses.isEmpty();
    }
}