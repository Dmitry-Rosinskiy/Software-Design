package org.example;

import java.util.List;

@JsonSerializable
public class Cat extends Animal {
    @JsonElement("nickname")
    public String name;

    @JsonElement
    public int age;

    @JsonElement
    public String color;

    @JsonElement
    public List<Toy> toys;
}
