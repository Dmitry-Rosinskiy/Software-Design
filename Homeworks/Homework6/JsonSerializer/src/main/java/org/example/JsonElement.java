package org.example;

import java.lang.annotation.*;

/**
 * Указывает, нужно ли использовать это поле при сериализации в JSON.
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonElement {
    /**
     * Задаёт название поля (по умолчанию такое же, что и само поле).
     * @return название поля
     */
    String value() default "";
}
