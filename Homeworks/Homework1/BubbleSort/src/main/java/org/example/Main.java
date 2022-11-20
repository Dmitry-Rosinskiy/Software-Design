package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("Input length of array: ");
        Scanner in = new Scanner(System.in);
        int length = in.nextInt();
        int[] array = new int[length];
        System.out.print("Input array elements: ");
        for (int i = 0; i < length; ++i) {
            array[i] = in.nextInt();
        }
        if (length == 0) {
            System.out.println();
        }
        bubbleSort(array);
        System.out.print("Output: ");
        for (int i = 0; i < length; ++i) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static void bubbleSort(int[] array) {
        int length = array.length;
        for (int i = 0; i < length - 1; ++i) {
            for (int j = 0; j < length - 1 - i; ++j) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }
}
