package org.example;

public class Main {
    public static void main(String[] args) {
        for (int i = 1; i <= 100; ++i) {
            if (i % 3 == 0 && i % 5 == 0) {
                System.out.println("FizzBuzz");
            } else if (i % 3 == 0) {
                System.out.println("Fizz");
            } else if (i % 5 == 0) {
                System.out.println("Buzz");
            } else {
                printNumberAsString(i);
            }
        }
    }

    public static void printNumberAsString(int number) {
        int digit1 = number / 10;
        int digit2 = number % 10;

        String[] strSmallDigits = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        String[] strBigDigits = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
        String[] str10_19 = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
        String[] strTens = {"Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

        String strNumber = "";
        if (digit1 == 0) {
            strNumber += strBigDigits[digit2 - 1];
            System.out.println(strNumber);
            return;
        } else if (digit1 == 1) {
            strNumber += str10_19[digit2];
            System.out.println(strNumber);
            return;
        } else {
            strNumber += strTens[digit1 - 2];
        }

        if (digit2 != 0) {
            strNumber += " " + strSmallDigits[digit2 - 1];
        }

        System.out.println(strNumber);
    }
}
