# JsonSerializer

Для сериализации объектов используется единственный статический метод `JsonSerializer.serialize(Object object)`. Полученный объект является `JSONObject` (`org.json.JSONObject`).

Для JSON-сериализации используются три аннотации:
1. `@JsonSerializable`. Чтобы объекты класса можно было сериализовать, необходимо, чтобы класс бы помечен этой аннотацией. Пример использования:

```java
@JsonSerializable
public class SomeClass {
}
```
2. `@JsonElement`. Поля, которые помечены этой аннотацией, будут добавляться в объект JSON при сериализации и только они. Пример использования:

```java
@JsonElement
public String someField;
```
При этом можно указать название поля, которое оно получит при сериализации (по умолчанию оно такое же, как и у поля). Выглядит это так:

```java
@JsonElement("my_field")
public String someField;
```
3. `@IgnoreNull`. Если класс помечен этой аннотацией, то при сериализации значения его сериализуемых полей и объектов, равных `null`, игнорируются, т.е. записываются как `""` (пустые значения). Если аннотация не указана, то такие значения сериализуются в `"null"`. Пример использования:

```java
@IgnoreNull
public class SomeClass {
}
```

## Пример
Пусть есть класс `Person`:
```java
public final class Person {
    private String firstName;
    
    private String lastName;
    
    private int age;
    
    private String weight;

    Person1(String firstName, String lastName, int age, String weight) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.weight = weight;
    }
}
```
Его объекты требуется уметь сериализовывать. Тогда класс `Person` можно, например, так дополнить аннтоациями:

```java
@JsonSerializable
public final class Person1 {
    @JsonElement
    private String firstName;

    @JsonElement("name")
    private String lastName;

    @JsonElement
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
```
Сериализация объектов происходит следующим образом (`person` является объектом класса `Person`):
```java
JSONObject jsonObject = JsonSerializer.serialize(person);
```
Тогда возможным JSON-объектом класса `Person`, полученный JsonSerializer, может быть (`jsonObject.toString(2)`):
```json
{
  "firstName": "Aleksandr",
  "name": "Kuchuk",
  "weight": "null",
  "age": 14
}
```
Если бы `Person` был ещё и помечен `@IgnoreNull`, т.е.

```java
@JsonSerializable
@IgnoreNull
public final class Person1 {
    ...
}
```
Тогда получился бы следующий JSON-объект:
```json
{
  "firstName": "Aleksandr",
  "name": "Kuchuk",
  "weight": "",
  "age": 14
}
```

>Важно!
>
>Сериализуются все поля, помеченные `@JsonElement`, вне зависимости от их модификаторов доступа. Также могут быть сериализованы статические поля класса, наравне с экземплярными.
>
>Если класс является наследником другого класса, то сериализовываться могут и поля его родительского класса (при этом они обязательно должны быть помечены `@JsonElement`). При этом не имеет значения, помечен ли родительский класс `@JsonSerializable`, возможность сериализации даёт его наследник (т.е. тот класс, объекты которого сериализовываются).
>
>`@JsonSerializable` и `@IgnoreNull` не наследуются (т.е., например, возможность сериализовать объект родительского класса не влияет на возможность сериализации объектов его наследников).
>
>Если поле является объектом другого сериализуемого класса, то его сериализация (поля) происходит по тем же правилам. Если класс может содержать несколько таких объектов, они будут правильно сериализованы, только если будут храниться в некоторой коллекции (например, `ArrayList`) Простой массив для хранения этих объектов не подойдёт.

Также к этому проекту приложены тесты с несколькими классами.
