package org.example;

import java.lang.annotation.*;

/**
 * Указывает, нужно ли игнорировать null-значения класса при сериализации в JSON
 * (если не указан, то такие поля сериализовываются как "null").
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreNull {
}
