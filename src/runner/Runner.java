package runner;

import loader.GlobalLibrary;
import loader.input.FishInput;
import loader.input.RodInput;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Scanner;
import java.util.Set;

public class Runner {
    static final KeyboardInputDecoder decoder = new KeyboardInputDecoder();

    public static void addFish(GlobalLibrary library) {
        System.out.flush();
        Scanner keyboard = new Scanner(System.in);
        do {
            FishInput fish = new FishInput();
            System.out.println("Enter the fishes' name:");
            fish.setName(keyboard.nextLine());
            if (library.hasFish(fish.getName())) {
                if (resolveCollision()) continue;
            }
            System.out.flush();// TODO: see if its better to use the constructor instead of setters
            System.out.printf("Enter a description for %s:%n", fish.getName());
            fish.setDescription(keyboard.nextLine());
            System.out.flush();
            System.out.printf("Enter the %ss' rarity:%n", fish.getName());
            // Using nextLine() instead of nextInt() to avoid newline problems
            try {
                fish.setRarity(Integer.parseInt(keyboard.nextLine()));
            } catch (Exception e) {
                fish.setRarity(0);
            }
            System.out.flush();
            System.out.printf("Set the price for %s:%n", fish.getName());
            try {
                fish.setPrice(Integer.parseInt(keyboard.nextLine()));
            } catch (Exception e) {
                fish.setPrice(0);
            }
            System.out.flush();

            fish.print();

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

    public static void addRod(GlobalLibrary library) {
        Scanner keyboard = new Scanner(System.in);
        do {
            RodInput rod = new RodInput();
            System.out.println("Enter the rods' name:");
            rod.setName(keyboard.nextLine()); // TODO: handle the case if name already exists
            if (library.hasRod(rod.getName())) {
                if (resolveCollision()) continue;
            }
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

            rod.print();

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

    public static void printLibrary(GlobalLibrary library) {
        library.printLibrary();
    }

    public static void displayHelp() {
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
