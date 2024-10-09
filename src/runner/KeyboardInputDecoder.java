package runner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class KeyboardInputDecoder {
    //TODO: write this as a map

    public CommandEnum decode(String input) {
        String[] command = input.split(" ");

        switch (command[0].toLowerCase()) {
            case "yes", "y", "yeah", "ye", "ys", "yea" -> {
                return CommandEnum.YES;
            }
            case "no", "n", "nah", "na" -> {
                return CommandEnum.NO;
            }
            case "end", "exit", "quit", "q" -> {
                return CommandEnum.EXIT;
            }
            case "print", "see", "p", "printlibrary" -> {
                return CommandEnum.PRINTLIBRARY;
            }
            case "help", "h", "hlep" -> {
                return CommandEnum.HELP;
            }
            case "fish", "cast", "f", "fsih" -> {
                return CommandEnum.FISH;
            }
            case "goto", "go" -> {
                CommandEnum goTo = CommandEnum.GOTO;
                String zoneName;
                try {
                    if (command[1].equals("to")) {
                        zoneName = extractArgument(command, 2);
                    } else {
                        zoneName = extractArgument(command, 1);
                    }
                    goTo.setArgument(zoneName);
                    return goTo;
                } catch (Exception e) {
                    System.out.println("Please provide the name of a zone.");
                    return CommandEnum.UNKNOWN;
                }
            }
            case "equip", "eq", "equio" -> {
                CommandEnum equip = CommandEnum.EQUIP;
                try {
                    String rodName = extractArgument(command, 1);
                    equip.setArgument(rodName);
                    return equip;
                } catch (Exception e) {
                    System.out.println("Please provide the name of a fishing rod.");
                    return CommandEnum.UNKNOWN;
                }
            }
            default -> {
                return CommandEnum.UNKNOWN;
            }
        }
    }

    private String extractArgument(String[] command, Integer startIndex) throws IndexOutOfBoundsException {
        List<String> commandList = new ArrayList<>(Arrays.asList(command).subList(startIndex, command.length));
        return String.join(" ", commandList);
    }

}
