package org.example;

@JsonSerializable
public class Box {
    @JsonElement("box_shape")
    static String shape = "Square";

    String id;

    @JsonElement
    final String color;

    @JsonElement
    Toy toy;

    Box(String id, String color) {
        this.id = id;
        this.color = color;
    }
}
