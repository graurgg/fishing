
import loader.GlobalLibrary;
import runner.KeyboardInputDecoder;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static GlobalLibrary library;
    public static void main(String[] args) throws IOException {
        library = loader.Loader.loadData();

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

        System.out.println("Hello, please input your command:");

        while ((keyboard.hasNextLine())) {
            switch (decoder.decode(keyboard.nextLine())) {
                case PRINTLIBRARY -> runner.Runner.printLibrary(library);
                case ADDFISH -> runner.Runner.addFish(library);
                case ADDROD -> runner.Runner.addRod(library);
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
