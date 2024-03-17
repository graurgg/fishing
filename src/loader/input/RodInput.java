package loader.input;

import lombok.Getter;

@Getter
public class RodInput extends AbstractInput {
    private Integer power;
    private Integer price;

    public RodInput() {

    }

    public RodInput(final String name, final String description,
                     final Integer power, final Integer price) {
        super(name, description);
        this.power = power;
        this.price = price;
    }

    public void print() {
        System.out.println(
                "Name: " + getName() +
                "\nDescription: " + getDescription() +
                "\nPower: " + power +
                "\nPrice: " + price);
    }

    @Override
    public InputType getType() {
        return InputType.ROD;
    }
}
