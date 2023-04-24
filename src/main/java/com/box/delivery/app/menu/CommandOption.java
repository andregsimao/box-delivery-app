package com.box.delivery.app.menu;

import java.util.Optional;

public enum CommandOption {
    EXIT(0, "Finish the program"),
    INSERT_FLIGHTS(1, "Insert flight schedule"),
    CHECK_FLIGHTS(2, "Check flight schedule"),
    GENERATE_ITINERARIES(3, "Generate itineraries");

    final int value;
    final String description;

    CommandOption(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public static Optional<CommandOption> getOptionByValue(int optionValue) {
        for(CommandOption commandOption: CommandOption.values()) {
            if(commandOption.value == optionValue) {
                return Optional.of(commandOption);
            }
        }
        return Optional.empty();
    }

    public int getValue() {
        return value;
    }
}
