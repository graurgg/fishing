package loader;

import loader.input.FishInput;
import loader.input.RodInput;

import java.util.ArrayList;
import java.util.List;

public class GlobalLibrary {
    private List<FishInput> fishInputList = new ArrayList<>();
    private List<RodInput> rodInputList = new ArrayList<>();
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


    public void updateDatabase() {
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
