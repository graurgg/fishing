package runner;

import loader.GlobalLibrary;
import loader.Loader;
import loader.input.FishInput;

import java.util.Scanner;

public class Runner {
    static KeyboardInputDecoder decoder = new KeyboardInputDecoder();

    public static void addFish(GlobalLibrary library) {
        System.out.flush();
        Scanner keyboard = new Scanner(System.in);
        // TODO: move logic to Loader class
        // TODO: make it so new fishes are added to data jsons
        // TODO: do the same for rods
        do {
            FishInput fish = new FishInput();
            System.out.println("Enter the fishes' name:");
            fish.setName(keyboard.nextLine()); // TODO: handle the case if name already exists
            System.out.flush();// TODO: see if its better to use the constructor instead of setters
            System.out.printf("Enter a description for %s:%n", fish.getName());
            fish.setDescription(keyboard.nextLine());
            System.out.flush();
            System.out.printf("Enter the %ss' rarity:%n", fish.getName());
            // Using nextLine() instead of nextInt() to avoid newline problems
            fish.setRarity(Integer.parseInt(keyboard.nextLine()));
            System.out.flush();
            System.out.printf("Set the price for %s:%n", fish.getName());
            fish.setPrice(Integer.parseInt(keyboard.nextLine()));
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
                    keyboard.close();
                    return;
                }
                default: {
                    System.out.println("Not an option, returning to main screen.");
                    keyboard.close();
                    return;
                }
            }
        } while (keyboard.hasNext());

    }
}
