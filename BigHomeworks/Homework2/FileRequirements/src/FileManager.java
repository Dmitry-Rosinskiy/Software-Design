import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * Файловый менеджер.
 */
public final class FileManager {
    /**
     * Выводит файлы и папки в указанной директории.
     * @param root директория
     * @throws FileNotFoundException если указанной директории не существует
     */
    public void showFilesDirectories(String root) throws FileNotFoundException {
        File fileInfo = getDirectoryInfo(root);
        File[] content = fileInfo.listFiles();
        for (File item : Objects.requireNonNull(content)) {
            System.out.print(item.getName());
            if (item.isDirectory()) {
                System.out.println("\t(папка)");
            } else {
                System.out.println("\t(файл)");
            }
        }
    }

    /**
     * Возвращает файлы в указанной директории.
     * @param root директория
     * @return массив путей к файлам
     * @throws FileNotFoundException если указанной директории не существует
     */
    public String[] getFiles(String root) throws FileNotFoundException {
        File fileInfo = getDirectoryInfo(root);
        File[] content = fileInfo.listFiles();
        ArrayList<String> files = new ArrayList<>();
        for (File item : Objects.requireNonNull(content)) {
            if (item.isFile()) {
                files.add(item.getPath());
            }
        }
        return files.toArray(new String[0]);
    }

    /**
     * Возвращает файлы с указанным расширением в указанной директории.
     * @param root директория
     * @param extension расширение файлов
     * @return массив путей к файлам
     * @throws FileNotFoundException если указанной директории не существует
     */
    public String[] getFiles(String root, String extension) throws FileNotFoundException {
        File fileInfo = getDirectoryInfo(root);
        File[] content = fileInfo.listFiles();
        ArrayList<String> files = new ArrayList<>();
        for (File item : Objects.requireNonNull(content)) {
            if (item.isFile() && extension.equals(getExtension(item))) {
                files.add(item.getPath());
            }
        }
        return files.toArray(new String[0]);
    }

    /**
     * Возвращает все файлы в указанной директории с учётом их вложенности.
     * @param root директория
     * @return массив путей к файлам
     * @throws FileNotFoundException если указанной директории не существует
     * @throws IllegalArgumentException если указанная директория не является папкой
     */
    public String[] getAllFiles(String root) throws FileNotFoundException, IllegalArgumentException {
        File fileInfo = getDirectoryInfo(root);
        ArrayList<String> files = new ArrayList<>();
        ArrayList<File> directories = new ArrayList<>();
        directories.add(fileInfo);
        while (!directories.isEmpty()) {
            ArrayList<File> buffer = new ArrayList<>(directories);
            directories.clear();
            for (File directory : buffer) {
                File[] content = directory.listFiles();
                for (File item : Objects.requireNonNull(content)) {
                    if (item.isFile()) {
                        files.add(item.getPath());
                    } else {
                        directories.add(item);
                    }
                }
            }
        }
        return files.toArray(new String[0]);
    }

    /**
     * Возвращает все файлы указанного расширения в указанной директории с учётом их вложенности.
     * @param root директория
     * @param extension расширение файлов
     * @return массив путей к файлам
     * @throws FileNotFoundException если указанной директории не существует
     * @throws IllegalArgumentException если указанная директория не является папкой
     */
    public String[] getAllFiles(String root, String extension)
            throws FileNotFoundException, IllegalArgumentException {
        File fileInfo = getDirectoryInfo(root);
        ArrayList<String> files = new ArrayList<>();
        ArrayList<File> directories = new ArrayList<>();
        directories.add(fileInfo);
        while (!directories.isEmpty()) {
            ArrayList<File> buffer = new ArrayList<>(directories);
            directories.clear();
            for (File directory : buffer) {
                File[] content = directory.listFiles();
                for (File item : Objects.requireNonNull(content)) {
                    if (item.isFile() && extension.equals(getExtension(item))) {
                        files.add(item.getPath());
                    } else if (item.isDirectory()) {
                        directories.add(item);
                    }
                }
            }
        }
        return files.toArray(new String[0]);
    }

    /**
     * Вовзвращает вспе папки в указанной директории.
     * @param root директория
     * @return массив путей к папкам
     * @throws FileNotFoundException если указанной директории не существует
     */
    public String[] getDirectories(String root) throws FileNotFoundException {
        File fileInfo = getDirectoryInfo(root);
        File[] content = fileInfo.listFiles();
        ArrayList<String> directories = new ArrayList<>();
        for (File item : Objects.requireNonNull(content)) {
            if (item.isDirectory()) {
                directories.add(item.getPath());
            }
        }
        return directories.toArray(new String[0]);
    }

    /**
     * Проверяет, существует ли файл.
     * @param file путь к файлу
     * @return {@code true}, если файл существует; {@code false} в противном случае
     */
    public boolean fileExists(String file) {
        var fileInfo = new File(file);
        return fileInfo.exists();
    }

    /**
     * Проверяет, указывает ли переданный путь на папку
     * @param path путь
     * @return {@code true}, если путь указывает на директорию; {@code false} в противном случае
     */
    public boolean isDirectory(String path) {
        var fileInfo = new File(path);
        return fileInfo.isDirectory();
    }

    /**
     * Выводит содержимое указанного файла.
     * @param fileName путь к файлу
     * @throws IOException если происходит ошибка при чтении файла
     */
    public void showFile(String fileName) throws IOException {
        try (var reader = new FileReader(fileName)) {
            var scan = new Scanner(reader);
            while (scan.hasNextLine()) {
                System.out.println(scan.nextLine());
            }
        }
    }

    /**
     * Возвращает файлы, от которых зависит указанный файл.
     * @param root корневая дирекория
     * @param fileName путь к файлу
     * @param keyword ключевое слово для указания зависимости
     * @param separator1 начальный разделитель пути к файлу, от которого зависит указанный файл
     * @param separator2 конечный разделитель пути к файлу, от которого зависит указанный файл
     * @param extension расширение файлов
     * @return массив пути к файлам, от которых зависит указанный файл
     * @throws IOException если происходит ошибка при работе с файлами
     */
    public String[] getRequirements(String root, String fileName, String keyword,
                                    char separator1, char separator2, String extension)
            throws IOException {
        ArrayList<String> requirements = new ArrayList<>();
        try (var reader = new FileReader(fileName)) {
            var scan = new Scanner(reader);
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                if ((line.length() >= keyword.length() + 4) && line.startsWith(keyword)
                        && (line.charAt(keyword.length() + 1) == separator1)
                        && (line.charAt(line.length() - 1) == separator2)) {
                    requirements.add(root + '/' + line.substring(line.indexOf(separator1) + 1,
                                     line.indexOf(separator2)) + "." + extension);
                }
            }
        }
        return requirements.toArray(new String[0]);
    }

    /**
     * Конкатенирует переданные файлы в один текст.
     * @param files массив путей к файлам
     * @param separator разделитель содержимых файлов
     * @return текст, полученный из указанный файлов
     * @throws IOException если происходит ошибка при работе с файлами
     */
    public String concatenateFiles(String[] files, String separator) throws IOException {
        var text = new StringBuilder();
        boolean isFirst = true;
        for (String file : files) {
            String textPart = readFile(file);
            if (!isFirst) {
                text.append(separator);
            } else {
                isFirst = false;
            }
            text.append(textPart);
        }
        return text.toString();
    }

    /**
     * Читает файл.
     * @param file путь к файлу
     * @return текст указанного файла
     * @throws IOException если происходит ошибка при чтении файла
     */
    public String readFile(String file) throws IOException {
        var text = new StringBuilder();
        try (var reader = new FileReader(file)) {
            var scan = new Scanner(reader);
            while (scan.hasNextLine()) {
                String line = scan.nextLine() + '\n';
                text.append(line);
            }
        }
        return text.toString();
    }

    /**
     * Записывает указанный текст в файл.
     * @param file путь к файлу
     * @param text текст
     * @throws IOException если происходит ошибка при записи файла
     */
    public void writeFile(String file, String text) throws IOException {
        try (var writer = new FileWriter(file)) {
            writer.write(text);
        }
    }

    /**
     * Отрезает от пути к файлу указанный путь к корневой папке и указанное расширение.
     * @param path путь к файлу
     * @param root корневая директория
     * @param extension расширение файла
     * @return укаороченный путь к файлу
     */
    public String trimRootAndExtension(String path, String root, String extension) {
        return path.substring(root.length() + 1, path.length() - extension.length() - 1);
    }

    /**
     * Возвращает объект директории указанной папки.
     * @param directory директория
     * @return объект директории
     * @throws FileNotFoundException если указанной директории не существует
     * @throws IllegalArgumentException если указанная директория не является папкой
     */
    private File getDirectoryInfo(String directory)
            throws FileNotFoundException, IllegalArgumentException {
        var fileInfo = new File(directory);
        if (!fileInfo.exists()) {
            throw new FileNotFoundException("Директивы \"" + directory + "\" не существует!");
        }
        if (fileInfo.isFile()) {
            throw new IllegalArgumentException("Переданный путь не указывает на директорию!");
        }
        return fileInfo;
    }

    /**
     * Возвращает расширение файла.
     * @param file путь к файлу
     * @return расширение файла
     */
    private String getExtension(File file) {
        String fileName = file.getName();
        int dotPos = fileName.lastIndexOf('.');
        return fileName.substring(dotPos + 1);
    }
}
