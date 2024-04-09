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
    private static GlobalLibrary library = GlobalLibrary.getInstance();
    // String for clearing Windows terminal
    // TODO: make it work for other OSes
    public static final String ESCAPE = "\033[H\033[2J";

    public static void addFish() {
        System.out.print(ESCAPE);
        Scanner keyboard = new Scanner(System.in);
        do {
            FishInput fish = new FishInput();
            System.out.println("Enter the fishes' name:");
            fish.setName(keyboard.nextLine());
            if (library.getFish(fish.getName()).isPresent()) {
                if (resolveCollision()) continue;
            }
            library.removeFish(fish.getName());
            System.out.printf("Enter a description for %s:%n", fish.getName());
            fish.setDescription(keyboard.nextLine());
            System.out.printf("Enter the %ss' rarity:%n", fish.getName());
            // Using nextLine() instead of nextInt() to avoid newline problems
            try {
                fish.setRarity(Integer.parseInt(keyboard.nextLine()));
            } catch (Exception e) {
                fish.setRarity(0);
            }
            System.out.printf("Set the price for %s:%n", fish.getName());
            try {
                fish.setPrice(Integer.parseInt(keyboard.nextLine()));
            } catch (Exception e) {
                fish.setPrice(0);
            }
            System.out.print(ESCAPE);

            fish.debug();

            System.out.println("Are you sure you want to add this fish? (Y/N)");
            switch (decoder.decode(keyboard.nextLine())) {
                case YES -> {
                    System.out.printf("Adding %s to database...%n", fish.getName());
                    library.addFish(fish);
                }
                case NO -> System.out.printf("Discarding %s...%n", fish.getName());

                case EXIT -> {
                    System.out.println("Returning to main screen.");
                    return;
                }
                default -> {
                    System.out.println("Not an option, adding fish anyway...");
                    library.addFish(fish);
                }
            }
            System.out.print(ESCAPE);

            System.out.println("Would you like to add another fish to the library? (Y/N)");
            switch (decoder.decode(keyboard.nextLine())) {
                case YES:
                    continue;
                case NO, EXIT: {
                    System.out.println("Returning to main screen.");
                    return;
                }
                default: {
                    System.out.println("Not an option, returning to main screen.");
                    return;
                }
            }
        } while (true);

    }

    public static void addRod() {
        System.out.print(ESCAPE);
        Scanner keyboard = new Scanner(System.in);
        do {
            RodInput rod = new RodInput();
            System.out.println("Enter the rods' name:");
            rod.setName(keyboard.nextLine()); // TODO: handle the case if name already exists
            if (library.getRod(rod.getName()).isPresent()) {
                if (resolveCollision()) continue;
            }
            library.removeRod(rod.getName());
            System.out.printf("Enter a description for %s:%n", rod.getName());
            rod.setDescription(keyboard.nextLine());
            System.out.printf("Enter the %ss' power:%n", rod.getName());
            // Using nextLine() instead of nextInt() to avoid newline problems
            try {
                rod.setPower(Integer.parseInt(keyboard.nextLine()));
            } catch (Exception e) {
                rod.setPower(0);
            }
            System.out.printf("Set the price for %s:%n", rod.getName());
            try {
                rod.setPrice(Integer.parseInt(keyboard.nextLine()));
            } catch (Exception e) {
                rod.setPrice(0);
            }
            System.out.print(ESCAPE);

            rod.debug();

            System.out.println("Are you sure you want to add this rod? (Y/N)");
            switch (decoder.decode(keyboard.nextLine())) {
                case YES -> {
                    System.out.printf("Adding %s to database...%n", rod.getName());
                    library.addRod(rod);
                }
                case NO -> System.out.printf("Discarding %s...%n", rod.getName());

                case EXIT -> {
                    System.out.println("Returning to main screen.");
                    return;
                }
                default -> {
                    System.out.println("Not an option, adding rod anyway...");
                    library.addRod(rod);
                }
            }
            System.out.print(ESCAPE);

            System.out.println("Would you like to add another rod to the library? (Y/N)");
            switch (decoder.decode(keyboard.nextLine())) {
                case YES:
                    continue;
                case NO, EXIT: {
                    System.out.println("Returning to main screen.");
                    return;
                }
                default: {
                    System.out.println("Not an option, returning to main screen.");
                    return;
                }
            }
        } while (true);

    }
    public static void addZone() {
        System.out.print(ESCAPE);
        Scanner keyboard = new Scanner(System.in);
        do {
            Zone zone = new Zone();
            System.out.println("Enter the zones' name:");
            zone.setName(keyboard.nextLine());
            if (library.getZone(zone.getName()).isPresent()) {
                if (resolveCollision()) {
                    continue;
                }
            }
            System.out.printf("Enter a description for the %s:%n", zone.getName());
            zone.setDescription(keyboard.nextLine());
            System.out.printf("What kind of fish live in this zone?%n");

            String fishName = keyboard.nextLine();
            do {
                Optional<FishInput> fish = library.getFish(fishName);
                if (fish.isPresent()) {
                    System.out.printf("Adding %s to the %s.%n", fishName, zone.getName());
                    zone.addFish(fish.get());
                } else {
                    System.out.printf("I don't know what kind of fish a %s is...%n", fishName);
                }

                System.out.printf("Does anything else live here?%n");
                fishName = keyboard.nextLine();
            } while (!decoder.decode(fishName).equals(CommandEnum.NO));

            System.out.print(ESCAPE);

            zone.debug();

            System.out.println("Are you sure you want to add this zone? (Y/N)");
            switch (decoder.decode(keyboard.nextLine())) {
                case YES -> {
                    System.out.printf("Adding %s to database...%n", zone.getName());
                    library.removeZone(zone.getName());
                    library.addZone(zone);
                }
                case NO -> System.out.printf("Discarding %s...%n", zone.getName());

                case EXIT -> {
                    System.out.println("Returning to main screen.");
                    return;
                }
                default -> {
                    System.out.println("Not an option, adding zone anyway...");
                    library.addZone(zone);
                }
            }
            System.out.print(ESCAPE);

            System.out.println("Would you like to add another zone to the library? (Y/N)");
            switch (decoder.decode(keyboard.nextLine())) {
                case YES:
                    continue;
                case NO, EXIT: {
                    System.out.println("Returning to main screen.");
                    return;
                }
                default: {
                    System.out.println("Not an option, returning to main screen.");
                    return;
                }
            }
        } while (true);
    }

    public static void fish(Player player) {
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

    public static void goTo(Player player) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Where to?");
        String zoneName = keyboard.nextLine().trim().toLowerCase();

        library.getZone(zoneName).ifPresentOrElse(player::changeZone,
                () -> System.out.printf("Couldn't find the zone named %s...%n", zoneName));
    }

    public static void equipRod(Player player) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("What rod?");
        String rodName = keyboard.nextLine().trim().toLowerCase();

        library.getRod(rodName).ifPresentOrElse(player::equipRod,
                () -> System.out.printf("Couldn't find the rod named %s...%n", rodName));
    }

    public static void printLibrary(Player player) {
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

    private static boolean resolveCollision() {
        Scanner keyboard = new Scanner(System.in);
        System.out.printf("Name already exists in database, overwrite?%n");
        switch (decoder.decode(keyboard.nextLine())) {
            case YES -> {
                System.out.printf("Okay, overwriting...%n");
                return false;
            }
            case NO -> {
                System.out.printf("Not overwriting...%n");
                return true;
            }
            case EXIT -> {
                return true;
            }
        }
        return true;
    }
}
