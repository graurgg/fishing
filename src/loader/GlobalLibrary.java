package loader;

import loader.input.FishInput;
import loader.input.RodInput;

import java.util.ArrayList;
import java.util.List;

public class GlobalLibrary {
    private List<FishInput> fishInputList = new ArrayList<>();
    private List<RodInput> rodInputList = new ArrayList<>();

    public GlobalLibrary() {
    }


    public void addFishes(List<FishInput> fishes) {
        fishInputList.addAll(fishes);
    }

    public void addRods(List<RodInput> rods) {
        rodInputList.addAll(rods);
    }

    public void addFish(FishInput fish) {
        fishInputList.add(fish);
    }

    public void addRod(RodInput rod) {
        rodInputList.add(rod);
    }

    public void printLibrary() {
        fishInputList.forEach(FishInput::print);
        rodInputList.forEach(RodInput::print);
    }


}
