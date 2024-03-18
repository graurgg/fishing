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
            case "print", "see", "p", "printlibrary" -> {
                return CommandEnum.PRINTLIBRARY;
            }
            case "addrod", "add rod", "adrod", "ad rod" -> {
                return CommandEnum.ADDROD;
            }
            default -> {
                return CommandEnum.UNKNOWN;
            }
        }
    }

}
