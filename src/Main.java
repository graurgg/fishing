
import entities.Player;
import loader.GlobalLibrary;
import loader.input.Zone;
import runner.KeyboardInputDecoder;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static GlobalLibrary library;
    private static Player player = new Player();
    public static void main(String[] args) throws IOException {
        library = loader.Loader.loadData();
        library.getZoneList().forEach(Zone::getWeights);

        run();

        try {
            library.updateDatabase();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void run() {
        Scanner keyboard = new Scanner(System.in);

        KeyboardInputDecoder decoder = new KeyboardInputDecoder();

        System.out.println("Hello, here is a list of available commands:");
        runner.Runner.displayHelp();

        while (true) {
            System.out.println("Please input your command:");
            switch (decoder.decode(keyboard.nextLine())) {
                case HELP -> runner.Runner.displayHelp();
                case PRINTLIBRARY -> runner.Runner.printLibrary(library);
                case ADDFISH -> runner.Runner.addFish(library);
                case ADDROD -> runner.Runner.addRod(library);
                case ADDZONE -> runner.Runner.addZone(library);
                case FISH -> runner.Runner.fish(player);
                case GOTO -> runner.Runner.goTo(player, library);
//                case EQUIP -> runner.Runner.equipRod(player, library);
                case EXIT -> {
                    keyboard.close();
                    System.out.println("Thank you for playing!");
                    return;
                }
                default -> System.out.println("Command not found...");
            }
        }
    }
}
