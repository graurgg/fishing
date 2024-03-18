package loader.input;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Zone extends AbstractInput{
    private List<String> fishes = new ArrayList<>();

    public Zone() {}

    public Zone(List<String> fishes) {
        this.fishes = fishes;
    }

    public void debug() {
        String n = System.lineSeparator();
        System.out.println(
                "Name: " + getName() + n +
                "Description: " + getDescription() + n +
                "Fish: " + fishes);
    }

    public void addFish(String fish) {
        fishes.add(fish);
    }

    @Override
    public InputType getType() {
        return InputType.ZONE;
    }
}
