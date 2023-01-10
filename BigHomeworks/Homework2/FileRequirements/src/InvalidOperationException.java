/**
 * Сигнализирует, если операция не может быть выполнена.
 */
public final class InvalidOperationException extends Exception {
    /**
     * Информация об ошибке в операции.
     */
    private String details = "";

    /**
     * Конструктор исключения с сообщением
     * @param message сообщение об исключении
     */
    public InvalidOperationException(String message) {
        super(message);
    }

    /**
     * Конструктор исключения с сообщением и информацией
     * @param message сообщение об исключении
     * @param details информация об ошибке в операции
     */
    public InvalidOperationException(String message, String details) {
        super(message);
        this.details = details;
    }

    /**
     * Возввращает информацию об ошибке в операции.
     * @return информация об ошибке в операции
     */
    public String getDetails() {
        return details;
    }
}
