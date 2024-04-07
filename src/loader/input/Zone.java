package loader.input;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.tools.javac.Main;
import lombok.Getter;
import org.javatuples.Pair;

import java.text.DecimalFormat;
import java.util.*;

@Getter
public class Zone extends AbstractInput{
    private List<FishInput> fishes = new ArrayList<>();
    @JsonIgnore
    private NavigableSet<Pair<String, Double>> weights = new TreeSet<>(Comparator.comparing(Pair::getValue1));

    public Zone() {}

    public Zone(String name, String description, List<FishInput> fishes) {
        super(name, description);
        this.fishes = fishes;
    }

    public void debug() {
        String n = System.lineSeparator();
        System.out.println(
                "Name: " + getName() + n +
                "Description: " + getDescription() + n +
                "Fish: " + fishes);
    }

    public NavigableSet<Pair<String, Double>> getWeights() {
        if (weights.isEmpty()) {
            weights = calculateWeights(fishes);
        }
        return weights;
    }

    private NavigableSet<Pair<String, Double>> calculateWeights(List<FishInput> fish) {

        NavigableSet<Pair<String, Double>> weightsList = new TreeSet<>(Comparator.comparing(Pair::getValue1));
        double totalRarity = fish.stream().mapToDouble(FishInput::getRarity).sum();
        for (FishInput iter : fish) {
            //  List with (Name of the fish), (Percent chance out of 100 to get said fish)
            weightsList.add(new Pair<>(iter.getName(), Math.floor((iter.getRarity().doubleValue() / totalRarity) * 10000) / 100));
        }
        return weightsList;
    }

    public void addFish(FishInput fish) {
        fishes.add(fish);
    }

    @Override
    public InputType getType() {
        return InputType.ZONE;
    }
}
