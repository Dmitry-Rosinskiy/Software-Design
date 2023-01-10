/**
 * Функциональный интерфейс для условий.
 * @param <T>
 */
@FunctionalInterface
public interface Condition<T> {
    /**
     * Проверяет условие.
     * @param object аргумент, на котором проверяется условие
     * @return {@code true}, если аргумент удовлетворяет условию; {@code false} в противном случае
     */
    boolean check(T object);
}
