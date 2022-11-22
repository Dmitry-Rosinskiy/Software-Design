package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static private final String FILE_NAME = "students.txt";
    static private ArrayList<Student> students;
    static private final Teacher teacher = new Teacher();

    public static void main(String[] args) {
        System.out.println("Student picker app");

        var  fileManager = new FileManager(FILE_NAME);
        try {
            students = fileManager.readStudents();
        }
        catch (IOException ex) {
            System.out.println("Could not open file with students: " + ex.getMessage());
            return;
        }

        System.out.println("\nType /h for help");
        Scanner in = new Scanner(System.in);
        String command = "";
        while (!"/e".equals(command)) {
            command = in.nextLine();
            switch (command) {
                case ("/r"):
                    teacher.selectRandomStudent(students);
                    break;
                case ("/l"):
                    showStudentsWithGradeList();
                    break;
                case ("/h"):
                    showHelp();
                    break;
                case ("/e"):
                    break;
                default:
                    System.out.println("Unknown command. Type /h for help");
                    break;
            }
        }
        try {
            fileManager.writeStudents(students);
        }
        catch (IOException ex) {
            System.out.println("Could not write students to file: " + ex.getMessage());
            return;
        }
        System.out.println("See you next time!");
    }

    private static void showStudentsWithGradeList() {
        boolean foundStudent = false;
        ArrayList<String> gradedStudents = teacher.getGradedStudents();
        for (Student student : students) {
            if (gradedStudents.contains(student.getName())) {
                foundStudent = true;
                System.out.println(student.getName() + ": " + student.getLastGrade());
            }
        }
        if (!foundStudent) {
            System.out.println("***No students are graded***");
        }
    }

    private static void showHelp() {
        System.out.println("This app picks for you a random student, asks if he/she is presented and if so grades chosen student.");
        System.out.println("Lesson starts when app starts and lesson finishes when app finishes.");
        System.out.println("All students are written in file \"students.txt\" in such format:");
        System.out.println("<name> <grade1> <grade2> ... <grade_n> 0 (\"0\" only tells when grade sequence ends and is not included in it).");
        System.out.println("After app finishes new grades of students are added in the same file (\"students.txt\").");
        System.out.println("\nCommands:");
        System.out.println("\t/r – selects random student, asks if present");
        System.out.println("\t/l – list of students who received a grade during the lesson");
        System.out.println("\t/h – help, lists commands and how to use them");
        System.out.println("\t/e - exit app");
        System.out.println();
    }
}
