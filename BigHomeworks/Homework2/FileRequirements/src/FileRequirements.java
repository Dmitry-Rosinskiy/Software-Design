/**
 * Файловые зависимости.
 */
public final class FileRequirements {
    /**
     * Граф файловых зависимостей.
     */
    private final Graph REQUIREMENTS_GRAPH;

    /**
     * Конструктор файловых зависимостей.
     * @param files массив путей к файлам
     */
    public FileRequirements(String[] files) {
        REQUIREMENTS_GRAPH = new Graph(files);
    }

    /**
     * Добавляет зависимость указанному файлу.
     * @param file путь к файлу
     * @param requirement путь к файлу, от которого зависит указанный
     */
    public void addRequirement(String file, String requirement) {
        REQUIREMENTS_GRAPH.addArc(file, requirement);
    }

    /**
     * Возвращает сортированный список файлов с условием:
     * если один файл зависит от другого, то он находится ниже последнего.
     * @return массив путей к файлам
     * @throws InvalidOperationException если нельзя получить сортированный список файлов
     * (есть циклическая зависимость)
     */
    public String[] getSortedFiles() throws InvalidOperationException {
        try {
            return REQUIREMENTS_GRAPH.reverseTopologicalOrder();
        } catch (InvalidOperationException e) {
            throw new InvalidOperationException("Нельзя построить список зависимостей файлов, "
                                                + "т.к. существует циклическая зависимость:",
                                                e.getDetails());
        }
    }

    /**
     * Выводит предаставление файловых зависимостей в виде графа смежности.
     */
    public void show() {
        REQUIREMENTS_GRAPH.show();
    }
}
