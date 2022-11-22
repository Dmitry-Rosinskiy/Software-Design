package org.example;

import java.util.Optional;
import java.util.Random;

public final class Student {
    private static final long presentSeed;
    private static final Random randomPresenceGenerator;

    private final String name;
    private Optional<Integer> grade = Optional.empty();
    private final boolean isPresent;


    public String getName() {
        return name;
    }

    public boolean isPresent() {
        return isPresent;
    }

    public Optional<Integer> getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = Optional.of(grade);
    }


    static {
        presentSeed = System.currentTimeMillis();
        randomPresenceGenerator = new Random(presentSeed);
    }

    Student(String name) {
        this.name = name;
        isPresent = randomPresenceGenerator.nextBoolean();
    }

    @Override
    public String toString() {
        return name + (grade.isPresent() ? ": " + grade.get() : "");
    }
}
