package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("Input: ");
        Scanner in = new Scanner(System.in);
        int number = in.nextInt();
        System.out.println("Output:");
        int an_1 = 1;
        int an = 1;
        if (number >= 1) {
            System.out.println(an_1);
        }
        if (number >= 2) {
            System.out.println(an);
        }
        if (number >= 3) {
            for (int i = 3; i <= number; ++i) {
                int an_1Copy = an_1;
                an_1 = an;
                an += an_1Copy;
                System.out.println(an);
            }
        }

    }
}
