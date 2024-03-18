package runner;

import java.util.Optional;

public enum CommandEnum {
    UNKNOWN(null),
    YES(null),
    NO(null),
    EXIT(null),
    ADDFISH("Adds a fish to the database."),
    ADDROD("Adds a rod to the database."),
    ADDZONE("Adds a zone to the database."),
    PRINTLIBRARY("Displays all fishes and rods."),
    HELP("Displays this wall of text.");

    final String helpMessage;
    CommandEnum(String helpMessage) {
        this.helpMessage = helpMessage;
    }

    public Optional<String> getHelpMessage() {
        return Optional.ofNullable(helpMessage);
    }

}
