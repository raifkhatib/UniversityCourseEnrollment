package com.raif.enrollment.model;

import java.io.Serializable;

/**
 * Abstract base class for people in the system.
 * This class demonstrates abstraction and will be extended by Student.
 */
public abstract class Person implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String id;
    private String name;
    private String email;

    protected Person(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public abstract String getRole();
}