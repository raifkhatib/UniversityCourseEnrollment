package com.raif.enrollment.model;

/**
 * Represents a university course.
 * This class uses encapsulation by keeping fields private
 * and exposing controlled access through methods.
 */
public class Course {
    private final String code;
    private String title;
    private int credits;

    public Course(String code, String title, int credits) {
        this.code = code;
        this.title = title;
        this.credits = credits;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public int getCredits() {
        return credits;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    @Override
    public String toString() {
        return "Course Code: " + code
                + " | Title: " + title
                + " | Credits: " + credits;
    }
}