/**
 * Вспомогательные операции с масиивами и матрицами.
 */
public final class ArrayOperations {
    /**
     * Ищет вхождение строки в массиве.
     * @param array массив строк
     * @param target искомая строка
     * @return позиция искомой строки в массиве, если она есть; -1 в противном случае
     */
    public static int find(String[] array, String target) {
        for (int i = 0; i < array.length; ++i) {
            if (array[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Ищет вхождение целого числа в массиве.
     * @param array массив целых чисел
     * @param value искомое целое число
     * @return позиция искомого целого числа в массиве, если оно есть; -1 в противном случае
     */
    public static int find(int[] array, int value) {
        for (int i = 0; i < array.length; ++i) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Ищет нулевую строку в матрице.
     * @param matrix матрица целых чисел.
     * @return позиция искомой строки в матрице, если она есть; -1 в противном случае
     */
    public static int findZeroRow(int[][] matrix) {
        for (int i = 0; i < matrix.length; ++i) {
            if (isZero(matrix[i])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Проверяет, является ли массив нулевым.
     * @param array массив целых чисел
     * @return {@code true}, если массив целиком состоит из нулей; {@code false} в противном случае
     */
    public static boolean isZero(int[] array) {
        for (int elem : array) {
            if (elem != 0) {
                return false;
            }
        }
        return  true;
    }

    /**
     * Устанавливает все элементы в столбце на указанное значение.
     * @param matrix матрица целых чисел
     * @param col позиция столбца
     * @param value новое значение
     * @param exceptions массив значений, которые игнорируются
     * (т.е. если значение элемента в столбце входит в этот массив, то оно не изменяется на новое)
     */
    public static void setColumnTo(int[][] matrix, int col, int value, int[] exceptions) {
        for (int i = 0; i < matrix.length; ++i) {
            if (find(exceptions, matrix[i][col]) < 0) {
                matrix[i][col] = value;
            }
        }
    }

    /**
     * Возвращает копию матрицы.
     * @param matrix копируемая матрица целых чисел
     * @return копия матрицы
     */
    public static int[][] getCopy(int[][] matrix) {
        if (matrix.length == 0) {
            return new int[0][0];
        }
        var copy = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; ++i) {
            System.arraycopy(matrix[i], 0, copy[i], 0, matrix[i].length);
        }
        return copy;
    }

    /**
     * Ищет строку матрицы с заданным условием.
     * @param matrix матрица целых чисел
     * @param condition условие для строки
     * @return позиция искомой строки в матрице, если она есть; -1 в противном случае
     */
    public static int findRow(int[][] matrix, Condition<int[]> condition) {
        for (int i = 0; i < matrix.length; ++i) {
            if (condition.check(matrix[i])) {
                return i;
            }
        }
        return -1;
    }
}
