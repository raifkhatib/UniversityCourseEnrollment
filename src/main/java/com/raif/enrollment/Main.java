package com.raif.enrollment;

import com.raif.enrollment.ui.ConsoleMenu;

/**
 * Entry point of the University Course Enrollment System.
 * This class only starts the application and delegates the user interface
 * to the ConsoleMenu class.
 */
public class Main {
    public static void main(String[] args) {
        ConsoleMenu menu = new ConsoleMenu();
        menu.start();
    }
}