package org.example;

@JsonSerializable
public final class Person1 {
    @JsonElement
    private String firstName;

    @JsonElement("name")
    private String lastName;

    @JsonElement()
    private int age;

    @JsonElement
    private String weight;

    Person1(String firstName, String lastName, int age, String weight) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.weight = weight;
    }
}
