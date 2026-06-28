package com.raif.enrollment.util;

import java.util.Scanner;

/**
 * Provides safe input reading methods for the console application.
 * This prevents the application from crashing when users enter invalid input.
 */
public class InputValidator {

    private InputValidator() {
        // Utility class should not be instantiated.
    }

    public static String readRequiredText(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();

            if (!value.isEmpty()) {
                return value;
            }

            System.out.println("Input cannot be empty. Please try again.");
        }
    }

    public static int readIntInRange(Scanner scanner, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            try {
                int value = Integer.parseInt(input);

                if (value >= min && value <= max) {
                    return value;
                }

                System.out.println("Please enter a number between " + min + " and " + max + ".");
            } catch (NumberFormatException error) {
                System.out.println("Invalid number. Please try again.");
            }
        }
    }
}