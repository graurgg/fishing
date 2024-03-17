package loader.input;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@JsonIgnoreProperties
public class FishInput extends AbstractInput {
    private Integer rarity;
    private Integer price;

    public FishInput() {

    }
    public FishInput(final String name, final String description,
                     final Integer rarity, final Integer price) {
        super(name, description);
        this.rarity = rarity;
        this.price = price;
    }

    public void print() {
        System.out.println(
                "Name: " + getName());
    }

    @Override
    public InputType getType() {
        return InputType.FISH;
    }
}
