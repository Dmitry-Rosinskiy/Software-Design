package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static private final ArrayList<Student> students = new ArrayList<>();
    static private final Teacher teacher = new Teacher();

    public static void main(String[] args) {
        System.out.println("Student picker app");
        makeStudents();
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
        System.out.println("See you next time!");
    }

    private static void makeStudents() {
        students.add(new Student("Steve"));
        students.add(new Student("George"));
        students.add(new Student("Tim"));
        students.add(new Student("Jack"));
        students.add(new Student("Tom"));
        students.add(new Student("Cody"));
        students.add(new Student("Zack"));
        students.add(new Student("Jake"));
        students.add(new Student("Linda"));
        students.add(new Student("Fred"));
    }

    private static void showStudentsWithGradeList() {
        boolean foundStudent = false;
        for (Student student : students) {
            if (student.getGrade().isPresent()) {
                foundStudent = true;
                System.out.println(student);
            }
        }
        if (!foundStudent) {
            System.out.println("***No students are graded***");
        }
    }

    private static void showHelp() {
        System.out.println("This app picks for you a random student, asks if he/she is presented and if so grades chosen student.");
        System.out.println("Lesson starts when app starts and lesson finishes when app finishes.");
        System.out.println("Commands:");
        System.out.println("\t/r – selects random student, asks if present");
        System.out.println("\t/l – list of students who received a grade");
        System.out.println("\t/h – help, lists commands and how to use them");
        System.out.println("\t/e - exit app");
    }
}
