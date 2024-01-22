package loader.input;

import lombok.Getter;

@Getter
public class FishInput {
    private String name;
    private String description;
    private Integer rarity;
    private Integer price;

    public FishInput() {

    }

    public FishInput(final String name, final String description,
                     final Integer rarity, final Integer price) {
        this.name = name;
        this.description = description;
        this.rarity = rarity;
        this.price = price;
    }

    public void print() {
        System.out.println(
                "Name: " + name +
                        "\nDescription: " + description +
                        "\nRarity: " + rarity +
                        "\nPrice: " + price);
    }
}
