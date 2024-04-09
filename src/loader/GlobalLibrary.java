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

    public void addFish(FishInput fish) {
        fishInputList.add(fish);
        fishUpdates++;
    }

    public void addRod(RodInput rod) {
        rodInputList.add(rod);
        rodUpdates++;
    }

    public void addZone(Zone zone) {
        zoneList.add(zone);
    }

    public void removeFish(String fishName) {
        Optional<FishInput> fish = getFish(fishName);
        fish.ifPresent(fishInputList::remove);
    }
    public void removeRod(String rodName) {
        Optional<RodInput> rod = getRod(rodName);
        rod.ifPresent(rodInputList::remove);
    }
    public void removeZone(String zoneName) {
        Optional<Zone> zone = getZone(zoneName);
        zone.ifPresent(zoneList::remove);
        System.out.println("REMOVED ZONE");
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

    public static synchronized void updateDatabase() throws IOException {
        Loader loader = new Loader();
        if (fishUpdates != 0) {
            loader.updateFishes();
        } else {
            System.out.println("No need to update fishes.");
        }

        if (rodUpdates != 0) {
            loader.updateRods();
        } else {
            System.out.println("No need to update rods.");
        }

            loader.updateZones();

    }


}
