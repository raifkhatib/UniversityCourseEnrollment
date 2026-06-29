package com.raif.enrollment.model;

/**
 * Represents a university student.
 * This class demonstrates inheritance because it extends Person.
 */
public class Student extends Person {
    private static final long serialVersionUID = 1L;

    private String major;
    private int academicYear;

    public Student(String id, String name, String email, String major, int academicYear) {
        super(id, name, email);
        this.major = major;
        this.academicYear = academicYear;
    }

    public String getMajor() {
        return major;
    }

    public int getAcademicYear() {
        return academicYear;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setAcademicYear(int academicYear) {
        this.academicYear = academicYear;
    }

    @Override
    public String getRole() {
        return "Student";
    }

    @Override
    public String toString() {
        return "Student ID: " + getId()
                + " | Name: " + getName()
                + " | Email: " + getEmail()
                + " | Major: " + major
                + " | Year: " + academicYear;
    }
}