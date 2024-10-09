
import loader.GlobalLibrary;
import runner.CommandEnum;
import runner.KeyboardInputDecoder;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        loader.Loader.loadData();

        run();
    }

    private static void run() {
        Scanner keyboard = new Scanner(System.in);

        KeyboardInputDecoder decoder = new KeyboardInputDecoder();

        System.out.println("Hello, here is a list of available commands:");
        runner.Runner.displayHelp();

        while (true) {
            System.out.println("Please input your command:");
            CommandEnum command = decoder.decode(keyboard.nextLine());
            switch (command) {
                case HELP -> runner.Runner.displayHelp();
                case PRINTLIBRARY -> runner.Runner.printLibrary();
                case FISH -> runner.Runner.fish();
                case GOTO -> runner.Runner.goTo(command.getArgument());
                case EQUIP -> runner.Runner.equipRod(command.getArgument());
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
