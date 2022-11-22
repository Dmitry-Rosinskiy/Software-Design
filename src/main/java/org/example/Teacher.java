package org.example;

import java.util.ArrayList;
import java.util.Random;

public class Teacher {
    private static final long selectSeed;
    private static final Random randomSelectionGenerator;
    private static final long gradeSeed;
    private static final Random randomGradeGenerator;

    private final ArrayList<String> flaggedStudents = new ArrayList<>();

    static {
        selectSeed = System.currentTimeMillis();
        randomSelectionGenerator = new Random(selectSeed);
        gradeSeed = System.currentTimeMillis();
        randomGradeGenerator = new Random(gradeSeed);
    }

    public void selectRandomStudent(ArrayList<Student> students) {
        ArrayList<Student> notFlaggedStudents = getNotFlaggedStudents(students);

        if (notFlaggedStudents.size() == 0) {
            System.out.println("No students can be selected");
            return;
        }

        int index;
        if (notFlaggedStudents.size() > 1) {
            index = randomSelectionGenerator.nextInt(0, notFlaggedStudents.size());
        } else {
            index = 0;
        }

        Student student = notFlaggedStudents.get(index);
        System.out.println("Selected student: " + student.getName());
        if (student.isPresent()) {
            System.out.println(student.getName() + " is present");
            int grade = randomGradeGenerator.nextInt(1, 10);
            System.out.println("Grade: " + grade);
            int studentIndex = getStudentIndex(student.getName(), students);
            students.get(studentIndex).setGrade(grade);
        } else {
            System.out.println(student.getName() + " is not present");
        }

        flaggedStudents.add(student.getName());
    }

    private ArrayList<Student> getNotFlaggedStudents(ArrayList<Student> students) {
        ArrayList<Student> notFlaggedStudents = new ArrayList<>();
        for (Student student : students) {
            if (!flaggedStudents.contains(student.getName())) {
                notFlaggedStudents.add(student);
            }
        }

        return notFlaggedStudents;
    }

    private int getStudentIndex(String name, ArrayList<Student> students) {
        for (int i = 0; i < students.size(); ++i) {
            if (students.get(i).getName().equals(name)) {
                return i;
            }
        }

        return 0;
    }
}
