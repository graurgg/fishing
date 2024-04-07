package entities;

import loader.input.FishInput;
import loader.input.RodInput;
import loader.input.Zone;
import lombok.Getter;

import java.util.*;

@Getter
public class Player {
    private RodInput equippedRod;
    private Zone currentZone;
    private final Set<FishInput> fishInventory = new HashSet<>();

    public Player() {
        equippedRod = new RodInput("none", "N/A", 0, 0);
        currentZone = new Zone("unset", "unset", Collections.emptyList());
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
