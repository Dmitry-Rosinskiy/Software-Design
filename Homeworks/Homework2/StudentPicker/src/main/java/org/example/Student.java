package org.example;

import java.util.ArrayList;
import java.util.Random;

public final class Student {
    private static final long presentSeed;
    private static final Random randomPresenceGenerator;

    private final String name;
    private final ArrayList<Integer> grades;
    private final boolean isPresent;


    public String getName() {
        return name;
    }

    public boolean isPresent() {
        return isPresent;
    }


    static {
        presentSeed = System.currentTimeMillis();
        randomPresenceGenerator = new Random(presentSeed);
    }

    Student(String name, ArrayList<Integer> grades) {
        this.name = name;
        this.grades = grades;
        isPresent = randomPresenceGenerator.nextBoolean();
    }

    public void addGrade(int grade) {
        if (grade >= 1 && grade <= 10) {
            grades.add(grade);
        }
    }

    public int getLastGrade() {
        if (grades.size() > 0) {
            return grades.get(grades.size() - 1);
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
         var str = new StringBuilder(name);
         for (int grade : grades) {
             str.append(" ").append(grade);
         }
         return str.toString();
    }
}
