package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {
    private final String fileName;


    FileManager(String fileName) {
        this.fileName = fileName;
    }

    public ArrayList<Student> readStudents() throws IOException {
        var students = new ArrayList<Student>();

        try (var fileReader = new Scanner(new File(fileName))) {
            while (fileReader.hasNext()) {
                String name = fileReader.next();
                var grades = new ArrayList<Integer>();
                int grade = fileReader.nextInt();
                while (grade != 0) {
                    grades.add(grade);
                    grade = fileReader.nextInt();
                }
                var student = new Student(name, grades);
                students.add(student);
            }
        }

        return students;
    }

    public void writeStudents(ArrayList<Student> students) throws IOException {
        try (var fileWriter = new FileWriter(fileName)) {
            for (Student student : students) {
                fileWriter.write(student.toString() + " 0\n");
            }
            fileWriter.flush();
        }
    }
}
