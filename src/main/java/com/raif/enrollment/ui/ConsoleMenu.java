package com.raif.enrollment.ui;

import com.raif.enrollment.model.Course;
import com.raif.enrollment.model.Enrollment;
import com.raif.enrollment.model.Student;
import com.raif.enrollment.service.CourseService;
import com.raif.enrollment.service.EnrollmentService;
import com.raif.enrollment.service.StudentService;
import com.raif.enrollment.storage.DataSnapshot;
import com.raif.enrollment.storage.FileDataStorage;
import com.raif.enrollment.util.InputValidator;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Handles the console user interface.
 * This class is responsible for showing the menu, reading user choices,
 * and calling the correct service or storage classes.
 */
public class ConsoleMenu {
    private final Scanner scanner;
    private final StudentService studentService;
    private final CourseService courseService;
    private final EnrollmentService enrollmentService;
    private final FileDataStorage fileDataStorage;

    public ConsoleMenu() {
        this.scanner = new Scanner(System.in);
        this.studentService = new StudentService();
        this.courseService = new CourseService();
        this.enrollmentService = new EnrollmentService();
        this.fileDataStorage = new FileDataStorage();
    }

    public void start() {
        boolean running = true;

        printHeader();

        while (running) {
            printMenu();

            int choice = InputValidator.readIntInRange(scanner, "Choose an option: ", 1, 9);

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewStudents();
                    break;
                case 3:
                    addCourse();
                    break;
                case 4:
                    viewCourses();
                    break;
                case 5:
                    enrollStudent();
                    break;
                case 6:
                    viewEnrollments();
                    break;
                case 7:
                    saveData();
                    break;
                case 8:
                    loadData();
                    break;
                case 9:
                    running = false;
                    System.out.println("Exiting application. Goodbye.");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private void printHeader() {
        System.out.println("=====================================");
        System.out.println(" University Course Enrollment System ");
        System.out.println("=====================================");
    }

    private void printMenu() {
        System.out.println();
        System.out.println("1. Add student");
        System.out.println("2. View students");
        System.out.println("3. Add course");
        System.out.println("4. View courses");
        System.out.println("5. Enroll student in course");
        System.out.println("6. View enrollments");
        System.out.println("7. Save data");
        System.out.println("8. Load data");
        System.out.println("9. Exit");
        System.out.println();
    }

    private void addStudent() {
        System.out.println();
        System.out.println("--- Add Student ---");

        String id = InputValidator.readRequiredText(scanner, "Student ID: ");
        String name = InputValidator.readRequiredText(scanner, "Student name: ");
        String email = InputValidator.readRequiredText(scanner, "Student email: ");
        String major = InputValidator.readRequiredText(scanner, "Major: ");
        int academicYear = InputValidator.readIntInRange(scanner, "Academic year (1-6): ", 1, 6);

        Student student = new Student(id, name, email, major, academicYear);
        boolean added = studentService.addStudent(student);

        if (added) {
            System.out.println("Student added successfully.");
        } else {
            System.out.println("A student with this ID already exists.");
        }
    }

    private void viewStudents() {
        System.out.println();
        System.out.println("--- Students ---");

        if (!studentService.hasStudents()) {
            System.out.println("No students found.");
            return;
        }

        List<Student> students = studentService.getAllStudents();

        for (Student student : students) {
            System.out.println(student);
        }
    }

    private void addCourse() {
        System.out.println();
        System.out.println("--- Add Course ---");

        String code = InputValidator.readRequiredText(scanner, "Course code: ");
        String title = InputValidator.readRequiredText(scanner, "Course title: ");
        int credits = InputValidator.readIntInRange(scanner, "Credits (1-10): ", 1, 10);

        Course course = new Course(code, title, credits);
        boolean added = courseService.addCourse(course);

        if (added) {
            System.out.println("Course added successfully.");
        } else {
            System.out.println("A course with this code already exists.");
        }
    }

    private void viewCourses() {
        System.out.println();
        System.out.println("--- Courses ---");

        if (!courseService.hasCourses()) {
            System.out.println("No courses found.");
            return;
        }

        List<Course> courses = courseService.getAllCourses();

        for (Course course : courses) {
            System.out.println(course);
        }
    }

    private void enrollStudent() {
        System.out.println();
        System.out.println("--- Enroll Student ---");

        if (!studentService.hasStudents()) {
            System.out.println("No students available. Add a student first.");
            return;
        }

        if (!courseService.hasCourses()) {
            System.out.println("No courses available. Add a course first.");
            return;
        }

        String studentId = InputValidator.readRequiredText(scanner, "Student ID: ");
        Optional<Student> student = studentService.findStudentById(studentId);

        if (student.isEmpty()) {
            System.out.println("Student not found.");
            return;
        }

        String courseCode = InputValidator.readRequiredText(scanner, "Course code: ");
        Optional<Course> course = courseService.findCourseByCode(courseCode);

        if (course.isEmpty()) {
            System.out.println("Course not found.");
            return;
        }

        boolean enrolled = enrollmentService.enrollStudent(student.get(), course.get());

        if (enrolled) {
            System.out.println("Student enrolled successfully.");
        } else {
            System.out.println("This student is already enrolled in this course.");
        }
    }

    private void viewEnrollments() {
        System.out.println();
        System.out.println("--- Enrollments ---");

        if (!enrollmentService.hasEnrollments()) {
            System.out.println("No enrollments found.");
            return;
        }

        List<Enrollment> enrollments = enrollmentService.getAllEnrollments();

        for (Enrollment enrollment : enrollments) {
            System.out.println(enrollment);
        }
    }

    private void saveData() {
        System.out.println();
        System.out.println("--- Save Data ---");

        DataSnapshot snapshot = new DataSnapshot(
                studentService.getAllStudents(),
                courseService.getAllCourses(),
                enrollmentService.getAllEnrollments()
        );

        try {
            fileDataStorage.save(snapshot);
            System.out.println("Data saved successfully to: " + fileDataStorage.getDataFilePath());
        } catch (IOException error) {
            System.out.println("Failed to save data: " + error.getMessage());
        }
    }

    private void loadData() {
        System.out.println();
        System.out.println("--- Load Data ---");

        try {
            Optional<DataSnapshot> snapshot = fileDataStorage.load();

            if (snapshot.isEmpty()) {
                System.out.println("No saved data file found.");
                return;
            }

            studentService.replaceStudents(snapshot.get().getStudents());
            courseService.replaceCourses(snapshot.get().getCourses());
            enrollmentService.replaceEnrollments(snapshot.get().getEnrollments());

            System.out.println("Data loaded successfully from: " + fileDataStorage.getDataFilePath());
        } catch (IOException error) {
            System.out.println("Failed to load data: " + error.getMessage());
        } catch (ClassNotFoundException error) {
            System.out.println("Saved data format is invalid.");
        }
    }
}