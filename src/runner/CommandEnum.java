package runner;

import lombok.Getter;

import java.util.Optional;

public enum CommandEnum {
    UNKNOWN(null),
    YES(null),
    NO(null),
    EXIT(null),
    PRINTLIBRARY("Displays all fishes and rods."),
    FISH("Tries to catch fish in the current zone with the current rod."),
    GOTO("Usage: goto <zoneName>. Changes current zone to <zoneName>"),
    EQUIP("Usage: equip <rodName>. Changes current rod to <rodName>"),
    HELP("Displays this wall of text.");

    final String helpMessage;
    @Getter
    public String argument;
    CommandEnum(String helpMessage) {
        this.helpMessage = helpMessage;
    }

    public void setArgument(String argument) {
        this.argument = argument;
    }

    public Optional<String> getHelpMessage() {
        return Optional.ofNullable(helpMessage);
    }

}
