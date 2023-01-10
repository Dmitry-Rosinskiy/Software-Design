import java.io.IOException;
import java.util.Scanner;

/**
 * Основной класс программы.
 */
public class Main {
    /**
     * Расширение искомых файлов.
     */
    private static final String EXTENSION = "txt";
    /**
     * Файловый менеджер.
     */
    private static final FileManager FILE_MANAGER = new FileManager();
    /**
     * Объект для ввода данных.
     */
    private static final Scanner IN = new Scanner(System.in);

    /**
     * Точка входа в программу.
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        System.out.println("\t\t\tФайловые зависимости\n");
        String root = inputRoot();
        try {
            FileRequirements fileRequirements = getFileRequirements(root);
            String[] sortedFiles = fileRequirements.getSortedFiles();
            System.out.println("Сортированный список файлов:");
            for (String file : sortedFiles) {
                System.out.println(FILE_MANAGER.trimRootAndExtension(file, root, EXTENSION));
            }
            System.out.println();
            String separator = inputSeparator();
            String resultText = FILE_MANAGER.concatenateFiles(sortedFiles, separator);
            makeFile(resultText);
            System.out.println();
        } catch (IOException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (InvalidOperationException e) {
            System.out.println(e.getMessage() + '\n' + e.getDetails());
        }
    }

    /**
     * Ввод корневой папки.
     * @return путь к корневой директории
     */
    private static String inputRoot() {
        System.out.println("Введите путь к корневой папке: ");
        String root = IN.nextLine();
        while (!FILE_MANAGER.fileExists(root) || !FILE_MANAGER.isDirectory(root)) {
            System.out.println("Директивы \"" + root + "\" не существует. Попробуйте снова");
            root = IN.nextLine();
        }
        return root;
    }

    /**
     * Ввод разделителя содержимых файлов.
     * @return разделитель содержимых файлов
     */
    private static String inputSeparator() {
        System.out.print("При склеивании текстовых файлов в один отделять содержимые файлов "
                         + "пустой строкой между собой? (y/n)\t");
        Answer answer = inputYesNo();
        return (answer == Answer.YES) ? "\n" : "";
    }

    /**
     * Ввод "Да" или "Нет".
     * @return ответ "Да" или "Нет"
     */
    private static Answer inputYesNo() {
        String answer = IN.nextLine();
        while (true) {
            if ("y".equals(answer)) {
                return Answer.YES;
            } else if ("n".equals(answer)) {
                return Answer.NO;
            } else {
                System.out.print("Введите \"y\" для \"да\" и \"n\" для \"нет\":\t");
                answer = IN.nextLine();
            }
        }
    }

    /**
     * Создание результирующего файла.
     * @param text текст файла
     */
    private static void makeFile(String text) {
        System.out.println("\nВведите путь к текстовому файлу, в котором будут склеены "
                           + "содержимые всех текстовых файлов:");
        String resultFile = IN.nextLine();
        while (true) {
            try {
                while (true) {
                    if (FILE_MANAGER.fileExists(resultFile)) {
                        System.out.print("Такой файл уже существует. Перезаписать? (y/n):\t");
                        Answer answer = inputYesNo();
                        if (answer == Answer.YES) {
                            break;
                        } else {
                            System.out.println("Введите путь к другому файлу:");
                            resultFile = IN.nextLine();
                        }
                    } else {
                        break;
                    }
                }
                FILE_MANAGER.writeFile(resultFile, text);
                System.out.println("Файл " + resultFile + " успешно записан");
                break;
            } catch (IOException e) {
                System.out.println("Ошибка: " + e.getMessage());
                System.out.println("Попробуйте снова:");
                resultFile = IN.nextLine();
            }
        }
    }

    /**
     * Получение файловых зависимостей.
     * @param root путь к корневой директории
     * @return объект файловых завимостей
     * @throws IOException если происходит ошибка при работе с файлами
     * @throws InvalidOperationException если нельзя построить файловые зависимости
     */
    private static FileRequirements getFileRequirements(String root)
            throws IOException, InvalidOperationException {
        String[] files = FILE_MANAGER.getAllFiles(root, EXTENSION);
        var fileRequirements = new FileRequirements(files);
        boolean hasInvalidFiles = false;
        for (String file : files) {
            String[] requirements = FILE_MANAGER.getRequirements(root, file, "require",
                                                        '‘', '’', EXTENSION);
            for (String requirement : requirements) {
                if (FILE_MANAGER.fileExists(requirement)) {
                    fileRequirements.addRequirement(file, requirement);
                } else {
                    hasInvalidFiles = true;
                    System.out.println("Ошибка в файле " + file+ ":");
                    System.out.println(
                            FILE_MANAGER.trimRootAndExtension(requirement, root, EXTENSION) +
                                    " не существует\n");
                }
            }
        }
        if (hasInvalidFiles) {
            throw new InvalidOperationException("Невозможно построить зависимости, т.к. "
                                                + "некоторые файлы зависят от несуществующих");
        }
        return fileRequirements;
    }
}