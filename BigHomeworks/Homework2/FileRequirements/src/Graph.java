import java.util.ArrayList;

/**
 * Граф.
 */
public final class Graph {
    /**
     * Матрица смежности графа.
     */
    private final int[][] ADJACENCY_MATRIX;
    /**
     * Названия вершин графа.
     */
    private final String[] VERTEX_NAMES;

    /**
     * Контруктор графа.
     * @param names названия вершин
     */
    public Graph(String[] names) {
        ADJACENCY_MATRIX = new int[names.length][names.length];
        VERTEX_NAMES = names.clone();
    }

    /**
     * Добавляет дугу в графе.
     * @param from название вершины, из которой исходит дуга
     * @param to название вершины, в которую входит дуга
     */
    public void addArc(String from, String to) {
        int from_number = ArrayOperations.find(VERTEX_NAMES, from);
        int to_number = ArrayOperations.find(VERTEX_NAMES, to);
        if ((from_number >= 0) && (to_number >= 0)) {
            ADJACENCY_MATRIX[from_number][to_number] = 1;
        }
    }

    /**
     * Выводит представление графа (матрица смежности).
     */
    public void show() {
        if (ADJACENCY_MATRIX.length == 0) {
            return;
        }
        for (int i = 0; i < ADJACENCY_MATRIX.length; ++i) {
            for (int j = 0; j < ADJACENCY_MATRIX.length; ++j) {
                System.out.print(ADJACENCY_MATRIX[i][j] + "\t");
            }
            System.out.println(VERTEX_NAMES[i]);
        }
    }

    /**
     * Возвращает список вершин в обратном топологическом порядке.
     * @return массив названий вершин
     * @throws InvalidOperationException если нельзя получить обратный топологический порядок вершин
     * (есть циклическая зависимость)
     */
    public String[] reverseTopologicalOrder() throws InvalidOperationException {
        if (ADJACENCY_MATRIX.length == 0) {
            return new String[0];
        }

        // Изменённый алгоритм Кана
        ArrayList<String> vertices = new ArrayList<>();
        int[][] matrixCopy = ArrayOperations.getCopy(ADJACENCY_MATRIX);
        while (hasVertices(matrixCopy)) {
            int currentIndex = ArrayOperations.findZeroRow(matrixCopy);
            if (currentIndex >= 0) {
                vertices.add(VERTEX_NAMES[currentIndex]);
                matrixCopy[currentIndex][0] = -1;
                ArrayOperations.setColumnTo(matrixCopy, currentIndex, 0, new int[]{-1});
            } else {
                String[] verticesCycle = getCycle(matrixCopy);
                StringBuilder cycle = new StringBuilder();
                for (String vertex : verticesCycle) {
                    cycle.append(vertex).append('\n');
                }
                throw new InvalidOperationException("Нельзя получить обратный топологический "
                                                    + "порядок вершин, т.к. в графе есть цикл:",
                                                    cycle.toString());
            }
        }
        return vertices.toArray(new String[0]);
    }

    /**
     * Проверяет, есть ли непомеченные вершины в графе.
     * @param adjacencyMatrix матрица смежности графа
     * @return {@code true}, если есть хотя бы одна непомеченная вершина;
     * {@code false} в противном случае
     */
    private boolean hasVertices(int[][] adjacencyMatrix) {
        for (int[] row : adjacencyMatrix) {
            if (row[0] != -1) {
                return true;
            }
        }
        return false;
    }

    /**
     * Возвращает вершины, которые образуют цикл в графе.
     * @param adjacencyMatrix матрица смежности графа
     * @return массив названий вершин
     */
    private String[] getCycle(int[][] adjacencyMatrix) {
        ArrayList<String> cycle = new ArrayList<>();
        int cyclePos = 0;
        Condition<int[]> condition = (array) -> !ArrayOperations.isZero(array) && (array[0] != -1);
        int index = ArrayOperations.findRow(adjacencyMatrix, condition);
        while (index >= 0) {
            if (!cycle.contains(VERTEX_NAMES[index])) {
                cycle.add(VERTEX_NAMES[index]);
            } else {
                cyclePos = cycle.indexOf(VERTEX_NAMES[index]);
                break;
            }
            index = ArrayOperations.find(adjacencyMatrix[index], 1);
        }
        if (cyclePos > 0) {
            cycle.subList(0, cyclePos).clear();
        }
        return cycle.toArray(new String[0]);
    }
}
