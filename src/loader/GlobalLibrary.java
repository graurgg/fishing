package loader;

import loader.input.FishInput;
import loader.input.RodInput;
import lombok.Getter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GlobalLibrary {
    @Getter
    private final List<FishInput> fishInputList = new ArrayList<>();
    @Getter
    private final List<RodInput> rodInputList = new ArrayList<>();
    private Integer fishUpdates;
    private Integer rodUpdates;

    public GlobalLibrary() {
        fishUpdates = 0;
        rodUpdates = 0;
    }


    public void addFish(List<FishInput> fishes) {
        fishInputList.addAll(fishes);
    }

    public void addRod(List<RodInput> rods) {
        rodInputList.addAll(rods);
    }

    public void addFish(FishInput fish) {
        fishInputList.add(fish);
        fishUpdates++;
    }

    public void addRod(RodInput rod) {
        rodInputList.add(rod);
        rodUpdates++;
    }

    public void printLibrary() {
        fishInputList.forEach(FishInput::print);
        rodInputList.forEach(RodInput::print);
    }

    public boolean hasFish(String name) {
        return fishInputList.stream()
                .map(FishInput::getName)
                .anyMatch(fish -> Objects.equals(fish, name));
    }

    public boolean hasRod(String name) {
        return rodInputList.stream()
                .map(RodInput::getName)
                .anyMatch(rod -> Objects.equals(rod, name));
    }

    public synchronized void updateDatabase() throws IOException {
        Loader loader = new Loader();
        if (fishUpdates != 0) {
            loader.updateFishes(this);
        } else {
            System.out.println("No need to update fishes.");
        }

        if (rodUpdates != 0) {
            loader.updateRods(this);
        } else {
            System.out.println("No need to update rods.");
        }

    }


}
