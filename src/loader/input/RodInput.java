package loader.input;

import lombok.Getter;

@Getter
public class RodInput {
    private String name;
    private String description;
    private Integer power;
    private Integer price;

    public RodInput() {

    }

    public RodInput(final String name, final String description,
                     final Integer power, final Integer price) {
        this.name = name;
        this.description = description;
        this.power = power;
        this.price = price;
    }

    public void print() {
        System.out.println(
                "Name: " + name +
                "\nDescription: " + description +
                "\nPower: " + power +
                "\nPrice: " + price);
    }
}
