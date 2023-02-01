package org.example;

import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Класс для JSON-сериализации.
 */
public final class JsonSerializer {
    /**
     * Сериализует переданный объект.
     * @param object объект для сериализации
     * @return json-объект (JSONObject)
     */
    public static JSONObject serialize(Object object) {
        var jsonObject = new JSONObject();
        if (!object.getClass().isAnnotationPresent(JsonSerializable.class)) {
            return jsonObject;
        }
        boolean ignoreNull = object.getClass().isAnnotationPresent(IgnoreNull.class);
        for (Field field : getAllJsonFields(object.getClass())) {
            field.setAccessible(true);
            try {
                JsonElement annotation = field.getAnnotation(JsonElement.class);
                String fieldName;
                if (!"".equals(annotation.value())) {
                    fieldName = annotation.value();
                } else {
                    fieldName = field.getName();
                }
                if (field.get(object) == null) {
                    if (ignoreNull) {
                        jsonObject.put(fieldName, "");
                    } else {
                        jsonObject.put(fieldName, "null");
                    }
                } else {
                    if (field.get(object).getClass().isAnnotationPresent(JsonSerializable.class)) {
                        jsonObject.put(fieldName, serialize(field.get(object)));
                    } else {
                        if (field.get(object) instanceof Collection<?> collection) {
                            for (Object element : collection) {
                                jsonObject.append(fieldName, serialize(element));
                            }
                        } else {
                            jsonObject.put(fieldName, field.get(object));
                        }
                    }
                }
            } catch (IllegalAccessException ignored) {
            }
        }
        return jsonObject;
    }

    /**
     * Возвращает все поля класса (в том числе и всех его суперклассов), которые моут быть сериализованы.
     * @param cls класс
     * @return список полей
     */
    private static List<Field> getAllJsonFields(Class<?> cls) {
        if (cls == null) {
            return new ArrayList<>();
        }
        List<Field> result = new ArrayList<>(getAllJsonFields(cls.getSuperclass()));
        for (Field field : cls.getDeclaredFields()) {
            if (field.isAnnotationPresent(JsonElement.class)) {
                result.add(field);
            }
        }
        return result;
    }
}
