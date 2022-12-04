package org.example;

public final class Converter {
    public static String convertToPosition(int row, int col) {
        return "" + (char) ('a' + col) + (row + 1);
    }

    public static int convertToRow(String pos) {
        return pos.charAt(1) - '0' - 1;
    }

    public static int convertToCol(String pos) {
        return pos.charAt(0) - 'a';
    }
}
