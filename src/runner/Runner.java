package runner;

import entities.Player;
import loader.GlobalLibrary;
import loader.input.FishInput;
import loader.input.RodInput;
import loader.input.Zone;
import org.javatuples.Pair;

import java.util.*;

public class Runner {
    static final KeyboardInputDecoder decoder = new KeyboardInputDecoder();
    private static final GlobalLibrary library = GlobalLibrary.getInstance();
    private static final Player player = Player.getInstance();
    // String for clearing Windows terminal
    // TODO: make it work for other OSes
    public static final String ESCAPE = "\033[H\033[2J";

    public static void fish() {

        Zone zone = player.getCurrentZone();
        NavigableSet<Pair<String, Double>> weights = zone.getWeights();

        // Base chance to get no fish (75%) lowered by the power of the current rod
        Double nothingChance = Math.floor(300.0 / Math.log(player.getEquippedRod().getPower()) * 100) / 100;

        weights.add(new Pair<>("nothing", nothingChance));

        Optional<String> caughtFish = findFish(weights, nothingChance);

        caughtFish.ifPresentOrElse(fish -> {
            System.out.printf("Caught %s!%n", fish);
            library.getFish(fish).ifPresent(player::addFish);
            }, () -> {
            throw new NoSuchElementException();
        });
    }

    // Pass nothingChance as argument for ease of operations
    private static Optional<String> findFish(NavigableSet<Pair<String, Double>> weights, Double nothingChance) {
        Random r = new Random();

        double cumulativeProbability = 0.0;

        // Iterate weights set in descending order
        NavigableSet<Pair<String, Double>> reverse = weights.descendingSet();

        // Total weight = catch fish weight (100) + catch nothing weight (nothingChance)
        double upperLimit = 100.0 + nothingChance;

        double randomValue = Math.floor(upperLimit * r.nextDouble() * 100) / 100;

        for (Pair<String, Double> iter : reverse) {
            cumulativeProbability += iter.getValue1();
            if (randomValue <= cumulativeProbability) {
                return Optional.of(iter.getValue0());
            }
        }
        return Optional.empty();
    }

    public static void goTo(String zoneName) {
        library.getZone(zoneName).ifPresentOrElse(player::changeZone,
                () -> System.out.printf("Couldn't find the zone named %s...%n", zoneName));
    }

    public static void equipRod(String rodName) {
        library.getRod(rodName).ifPresentOrElse(player::equipRod,
                () -> System.out.printf("Couldn't find the rod named %s...%n", rodName));
    }

    public static void printLibrary() {
        System.out.print(ESCAPE);
        library.printLibrary();
        player.printInventory();
    }

    public static void displayHelp() {
        System.out.print(ESCAPE);

        Arrays.stream(CommandEnum.values())
                .filter(command -> command.getHelpMessage().isPresent())
                .forEach(option -> System.out.printf("%s :: %s%n%n", option.name(), option.getHelpMessage().get()));
    }

}
