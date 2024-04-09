package entities;

import loader.input.FishInput;
import loader.input.RodInput;
import loader.input.Zone;
import lombok.Getter;

import java.util.*;

@Getter
public class Player {
    private static Player instance = null;
    private RodInput equippedRod;
    private Zone currentZone;
    private final List<FishInput> fishInventory = new ArrayList<>();

    private Player() {
        equippedRod = new RodInput("none", "N/A", 0, 0);
        currentZone = new Zone("unset", "unset", Collections.emptyList());
    }

    public static synchronized Player getInstance() {
        if (instance == null) {
            instance = new Player();
        }
        return instance;
    }

    public void printInventory() {
        System.out.println("Equipped rod: " + equippedRod.getName());
        System.out.println("Current zone: " + currentZone.getName());
        System.out.println("Fishes: " + fishInventory.stream().map(FishInput::getName).toList());
    }

    public void equipRod(RodInput rod) {
        equippedRod = rod;
    }

    public void changeZone(Zone zone) {
        currentZone = zone;
    }

    public void addFish(FishInput fish) {
        fishInventory.add(fish);
    }
}
