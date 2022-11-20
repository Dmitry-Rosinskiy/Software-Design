package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
         System.out.print("Input: ");
         Scanner in = new Scanner(System.in);
         String str = in.nextLine();
         int[] charInfo = getConsonantsAndVowelsCount(str);
         System.out.println("Consonants: " + charInfo[0]);
         System.out.println("Vowels: " + charInfo[1]);
    }

    public static int[] getConsonantsAndVowelsCount (String str) {
        String consonantsList = "qwrtpsdfghjklzxcvbnmQWRTPSDFGHJKLZXCVBNM";
        String vowelsList = "eyuioaEYUIOA";

        int consonantsCount = 0;
        int vowelsCount = 0;

        for (int i = 0; i < str.length(); ++i) {
            char c = str.charAt(i);
            if (consonantsList.contains(c + "")) {
                ++consonantsCount;
            }
            if (vowelsList.contains(c + "")) {
                ++vowelsCount;
            }
        }

        return new int[] {consonantsCount, vowelsCount};
    }
}
