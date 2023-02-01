package org.example;

import org.json.JSONObject;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Класс для тестирования JsonSerializer.
 */
class JsonSerializerTest {
    @ParameterizedTest(name = "{0} {1} {2}")
    @CsvSource( {"Aleksandr, Kuchuk, 14", "NeAlexander, Kuchuk, 20",
            "Alexander, NeKuchuk, 37", "NeAlexander, NeKuchuk, 3"} )
    void person1_1(String firstName, String lastName, int age) {
        var person = new Person1(firstName, lastName, age, null);
        JSONObject jsonObject1 = JsonSerializer.serialize(person);

        var jsonObject2 = new JSONObject();
        jsonObject2.put("age", age);
        jsonObject2.put("firstName", firstName);
        jsonObject2.put("name", lastName);
        jsonObject2.put("weight", "null");

        assertTrue(jsonObject1.similar(jsonObject2), () ->
                "Object was not serialized correctly!\n Expected:\n"
                        + jsonObject2.toString(2) + "\nActual:\n"
                        + jsonObject1.toString(2));
    }

    @ParameterizedTest(name = "{0} {1}")
    @CsvSource( {"14, 60", "20, 70", "60, 82", "75, 75"} )
    void person1_2(int age, String weight) {
        var person = new Person1(null, null, age, weight);
        JSONObject jsonObject1 = JsonSerializer.serialize(person);

        var jsonObject2 = new JSONObject();
        jsonObject2.put("age", age);
        jsonObject2.put("firstName", "null");
        jsonObject2.put("name", "null");
        jsonObject2.put("weight", weight);

        assertTrue(jsonObject1.similar(jsonObject2), () ->
                "Object was not serialized correctly!\n Expected:\n"
                        + jsonObject2.toString(2) + "\nActual:\n"
                        + jsonObject1.toString(2));
    }

    @ParameterizedTest(name = "{0} {1} {2}")
    @CsvSource( {"Aleksandr, Kuchuk, 14", "NeAlexander, Kuchuk, 20",
            "Alexander, NeKuchuk, 37", "NeAlexander, NeKuchuk, 3"} )
    void person2_1(String firstName, String lastName, int age) {
        var person = new Person2(firstName, lastName, age, null);
        JSONObject jsonObject1 = JsonSerializer.serialize(person);

        var jsonObject2 = new JSONObject();
        jsonObject2.put("age", age);
        jsonObject2.put("name", firstName);
        jsonObject2.put("lastName", lastName);
        jsonObject2.put("weight", "");

        assertTrue(jsonObject1.similar(jsonObject2), () ->
                "Object was not serialized correctly!\n Expected:\n"
                        + jsonObject2.toString(2) + "\nActual:\n"
                        + jsonObject1.toString(2));
    }

    @ParameterizedTest(name = "{0} {1}")
    @CsvSource( {"14, 60", "20, 70", "-5, 0", "0, -5"} )
    void person2_2(int age, String weight) {
        var person = new Person2(null, null, age, weight);
        JSONObject jsonObject1 = JsonSerializer.serialize(person);

        var jsonObject2 = new JSONObject();
        jsonObject2.put("age", age);
        jsonObject2.put("name", "");
        jsonObject2.put("lastName", "");
        jsonObject2.put("weight", weight);

        assertTrue(jsonObject1.similar(jsonObject2), () ->
                "Object was not serialized correctly!\n Expected:\n"
                        + jsonObject2.toString(2) + "\nActual:\n"
                        + jsonObject1.toString(2));
    }

    @ParameterizedTest(name = "{0} {1}")
    @CsvSource( {"Ball, Red", "Mouse, White", "Stick with ball, Black"} )
    void toy(String name, String color) {
        var toy = new Toy();
        toy.name = name;
        toy.color = color;
        JSONObject jsonObject1 = JsonSerializer.serialize(toy);

        var jsonObject2 = new JSONObject();
        jsonObject2.put("toy_name", name);
        jsonObject2.put("toy_color", color);

        assertTrue(jsonObject1.similar(jsonObject2), () ->
                "Object was not serialized correctly!\n Expected:\n"
                        + jsonObject2.toString(2) + "\nActual:\n"
                        + jsonObject1.toString(2));
    }

    @ParameterizedTest(name = "{0} {1}")
    @CsvSource( {"0, Red", "1, Green", "2, Blue"} )
    void box(String id, String color) {
        String toyName = "Car";
        String toyColor = "Yellow";
        var box = new Box(id, color);
        box.toy = new Toy();
        box.toy.name = toyName;
        box.toy.color = toyColor;
        JSONObject jsonObject1 = JsonSerializer.serialize(box);

        var jsonObject2 = new JSONObject();
        jsonObject2.put("box_shape", Box.shape);
        jsonObject2.put("color", color);
        var jsonObjectToy = new JSONObject();
        jsonObjectToy.put("toy_name", toyName);
        jsonObjectToy.put("toy_color", toyColor);
        jsonObject2.put("toy", jsonObjectToy);

        assertTrue(jsonObject1.similar(jsonObject2), () ->
                "Object was not serialized correctly!\n Expected:\n"
                        + jsonObject2.toString(2) + "\nActual:\n"
                        + jsonObject1.toString(2));
    }

    @ParameterizedTest(name = "{0}")
    @CsvSource( {"Big", "Medium", "Small"} )
    void animal(Size size) {
        var animal = new Animal();
        animal.size = size;
        JSONObject jsonObject1 = JsonSerializer.serialize(animal);

        var jsonObject2 = new JSONObject();
        jsonObject2.put("size", size);

        assertTrue(jsonObject1.similar(jsonObject2), () ->
                "Object was not serialized correctly!\n Expected:\n"
                        + jsonObject2.toString(2) + "\nActual:\n"
                        + jsonObject1.toString(2));
    }

    @ParameterizedTest(name = "{0} {1} {2}")
    @CsvSource( {"Barsik, 10, Gray", "Musya, 5, Ginger", "Murka, 13, Black"} )
    void cat(String name, int age, String color) {
        Size size = Size.Small;
        String toy1Name = "Ball";
        String toy1Color = "Red";
        String toy2Name = "Mouse";
        String toy2Color = "White";
        String toy3Name = "Stick with ball";
        String toy3Color = "Black";

        var cat = new Cat();
        cat.name = name;
        cat.age = age;
        cat.color = color;
        cat.size = size;
        List<Toy> toys = new ArrayList<>();
        var toy1 = new Toy();
        toy1.name = toy1Name;
        toy1.color = toy1Color;
        var toy2 = new Toy();
        toy2.name = toy2Name;
        toy2.color = toy2Color;
        var toy3 = new Toy();
        toy3.name = toy3Name;
        toy3.color = toy3Color;
        toys.add(toy1);
        toys.add(toy2);
        toys.add(toy3);
        cat.toys = toys;
        JSONObject jsonObject1 = JsonSerializer.serialize(cat);

        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("size", size);
        jsonObject2.put("nickname", name);
        jsonObject2.put("age", age);
        jsonObject2.put("color", color);
        var jsonObjectToy1 = new JSONObject();
        jsonObjectToy1.put("toy_name", toy1Name);
        jsonObjectToy1.put("toy_color", toy1Color);
        jsonObject2.append("toys", jsonObjectToy1);
        var jsonObjectToy2 = new JSONObject();
        jsonObjectToy2.put("toy_name", toy2Name);
        jsonObjectToy2.put("toy_color", toy2Color);
        jsonObject2.append("toys", jsonObjectToy2);
        var jsonObjectToy3 = new JSONObject();
        jsonObjectToy3.put("toy_name", toy3Name);
        jsonObjectToy3.put("toy_color", toy3Color);
        jsonObject2.append("toys", jsonObjectToy3);

        assertTrue(jsonObject1.similar(jsonObject2), () ->
                "Object was not serialized correctly!\n Expected:\n"
                        + jsonObject2.toString(2) + "\nActual:\n"
                        + jsonObject1.toString(2));
    }
}
