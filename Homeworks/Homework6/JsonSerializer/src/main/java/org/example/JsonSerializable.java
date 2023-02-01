package org.example;

import java.lang.annotation.*;

/**
 * Указывает, является ли класс сериализуемым в JSON.
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonSerializable {
}
