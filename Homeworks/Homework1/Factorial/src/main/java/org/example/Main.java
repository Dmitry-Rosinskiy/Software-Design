package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("Input: ");
        Scanner in = new Scanner(System.in);
        int number = in.nextInt();
        System.out.println("Output: " + factorial(number));
    }

    public static long factorial(int number) {
        if (number == 0) {
            return 1;
        }
        long fact = 1;
        for (int i = 1; i <= number; ++i) {
            fact *= i;
        }

        return fact;
    }
}
