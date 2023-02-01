package org.example;

@JsonSerializable
public class Toy {
    @JsonElement("toy_name")
    public String name;

    @JsonElement("toy_color")
    public String color;
}
