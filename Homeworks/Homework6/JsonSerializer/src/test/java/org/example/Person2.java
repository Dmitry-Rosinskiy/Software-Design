package org.example;

@JsonSerializable
@IgnoreNull
public class Person2 {
    @JsonElement("name")
    private String firstName;

    @JsonElement
    private String lastName;

    @JsonElement
    private int age;

    @JsonElement
    private String weight;

    Person2(String firstName, String lastName, int age, String weight) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.weight = weight;
    }
}
