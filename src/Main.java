
import loader.GlobalLibrary;
import runner.CommandEnum;
import runner.KeyboardInputDecoder;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static final GlobalLibrary library = new GlobalLibrary();
    public static void main(String[] args) throws IOException {
        loader.Loader.loadData(library);

        run();

        library.updateDatabase();
    }

    private static void run() {
        Scanner keyboard = new Scanner(System.in);

        KeyboardInputDecoder decoder = new KeyboardInputDecoder();

        System.out.println("Hello, please input your command:");

        while ((keyboard.hasNextLine())) {
            switch (decoder.decode(keyboard.nextLine())) {
                case ADDFISH -> runner.Runner.addFish(library);
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
