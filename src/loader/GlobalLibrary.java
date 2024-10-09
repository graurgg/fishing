package loader;

import loader.input.FishInput;
import loader.input.RodInput;
import loader.input.Zone;
import lombok.Getter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class GlobalLibrary {
    private static GlobalLibrary instance = null;
    @Getter
    private final List<FishInput> fishInputList = new ArrayList<>();
    @Getter
    private final List<RodInput> rodInputList = new ArrayList<>();
    @Getter
    private final List<Zone> zoneList = new ArrayList<>();
    private static Integer fishUpdates;
    private static Integer rodUpdates;

    private GlobalLibrary() {
        fishUpdates = 0;
        rodUpdates = 0;
    }

    public static synchronized GlobalLibrary getInstance() {
        if (instance == null) {
            instance = new GlobalLibrary();
        }
        return instance;
    }

    public void addFish(List<FishInput> fishes) {
        fishInputList.addAll(fishes);
    }
    public void addRod(List<RodInput> rods) {
        rodInputList.addAll(rods);
    }
    public void addZone(List<Zone> zones) {
        zoneList.addAll(zones);
    }

    public void printLibrary() {
        fishInputList.forEach(FishInput::print);
        rodInputList.forEach(RodInput::print);
        zoneList.forEach(Zone::print);
    }

    public Optional<FishInput> getFish(String name) {
        return fishInputList.stream()
                .filter(fish -> Objects.equals(fish.getName(), name))
                .findAny();
    }

    public Optional<RodInput> getRod(String name) {
        return rodInputList.stream()
                .filter(rod -> Objects.equals(rod.getName(), name))
                .findAny();
    }

    public Optional<Zone> getZone(String name) {
        return zoneList.stream()
                .filter(zone -> Objects.equals(zone.getName(), name))
                .findAny();
    }

    // Returns the default zone (currently the first one in the JSON)
    public Zone getDefaultZone() {
        return zoneList.get(0);
    }

    // Returns the default rod (currently no rod, empty hands)
    public RodInput getDefaultRod() {
        return new RodInput("hands", "empty hands", 1, 0);
    }

}
