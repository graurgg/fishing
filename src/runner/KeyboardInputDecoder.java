package runner;

public final class KeyboardInputDecoder {
    //TODO: write this as a map

    public CommandEnum decode(String input) {
        switch (input.toLowerCase()) {
            case "yes", "y", "yeah", "ye", "ys", "yea" -> {
                return CommandEnum.YES;
            }
            case "no", "n", "nah", "na" -> {
                return CommandEnum.NO;
            }
            case "end", "exit", "quit", "q" -> {
                return CommandEnum.EXIT;
            }
            case "addfish", "add fish", "adfish", "ad fish" -> {
                return CommandEnum.ADDFISH;
            }
            default -> {
                return CommandEnum.UNKNOWN;
            }
        }
    }

}
