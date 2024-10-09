package loader.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

    public void debug() {
        String n = System.lineSeparator();
        System.out.println(
                "Name: " + getName() + n +
                "Description: " + getDescription() + n +
                "Price: " + getPrice() + n +
                "Rarity: " + getPower());
    }

    @Override
    public InputType getType() {
        return InputType.ROD;
    }
}
