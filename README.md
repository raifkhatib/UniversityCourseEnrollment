# University Course Enrollment System

This is a simple Java console application for managing students, courses, and course enrollments.

The project was built to practice Object-Oriented Programming in Java. It uses separate classes and packages instead of putting everything in one file.

## What the project does

The program allows the user to:

* add students
* view students
* add courses
* view courses
* enroll a student in a course
* view all enrollments
* save data
* load saved data
* exit the program

## Technologies used

* Java
* IntelliJ IDEA
* Git and GitHub
* Maven structure
* File saving/loading using serialization

## Project structure

```text
src/main/java/com/raif/enrollment
```

The code is divided into different packages:

```text
model      -> classes that represent data
service    -> classes that handle the main logic
ui         -> console menu
storage    -> saving and loading data
util       -> input validation
```

Main files:

```text
Main.java
Person.java
Student.java
Course.java
Enrollment.java
StudentService.java
CourseService.java
EnrollmentService.java
ConsoleMenu.java
InputValidator.java
DataSnapshot.java
FileDataStorage.java
```

## OOP concepts used

### Encapsulation

The classes use private variables and public methods. For example, student and course information is not accessed directly from outside the class.

### Inheritance

`Student` extends `Person`.

This means the student class reuses common person information like ID, name, and email.

### Abstraction

`Person` is an abstract class. It is used as a base class for student-related data.

### Composition

`Enrollment` contains a `Student` and a `Course`.

This makes sense because an enrollment connects one student with one course.

### Separation of responsibilities

The project is split into different classes instead of putting everything in one file.

For example:

* `ConsoleMenu` handles the menu
* `StudentService` handles student logic
* `CourseService` handles course logic
* `EnrollmentService` handles enrollment logic
* `FileDataStorage` handles saving and loading

## How to run the project

### Using IntelliJ IDEA

1. Open IntelliJ IDEA.
2. Open the project folder.
3. Go to:

```text
src/main/java/com/raif/enrollment/Main.java
```

4. Run the `main` method.
5. The menu will appear in the console.

### Using Maven

If Maven is installed, the project can also be run with:

```bash
mvn clean compile
mvn exec:java
```

## Menu options

When the program starts, it shows this menu:

```text
1. Add student
2. View students
3. Add course
4. View courses
5. Enroll student in course
6. View enrollments
7. Save data
8. Load data
9. Exit
```

## Example use

A normal test would be:

1. Add a student.
2. Add a course.
3. Enroll the student in the course.
4. View the enrollment.
5. Save the data.
6. Restart the program.
7. Load the data again.

## Saving and loading

The program saves data into a local `data` folder.

That folder is ignored by Git because it is generated when the program runs. It is not part of the source code.

## Challenges

One challenge was organizing the project correctly. Instead of using one large file, the code was separated into different classes and packages.

Another challenge was handling wrong user input. To solve this, an `InputValidator` class was added so the program does not crash easily when the user enters invalid values.

Saving and loading data was also separated into its own storage class to keep the menu code cleaner.

## Future improvements

Some possible improvements are:

* add a graphical interface
* use a database instead of file storage
* add course capacity limits
* allow students to drop courses
* add login roles for admin and students
