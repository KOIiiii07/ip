package jme.parser;

import jme.command.*;

/**
 * Parses user input strings and returns the corresponding Command object.
 */
public class Parser {

    /**
     * Parses the given user input and returns the appropriate Command.
     * The first word of the input is treated as the command keyword
     * (case-insensitive), and any remaining text is passed as arguments.
     *
     * @param userInput The full user input string.
     * @return The Command corresponding to the user's input,
     *         or an InvalidCommand if the input is not recognised.
     */
    public static Command parse(String userInput) {
        userInput = userInput.trim();

        if (userInput.isEmpty()) {
            return new InvalidCommand();
        }

        String[] parts = userInput.split("\\s", 2);
        String command = parts[0].toLowerCase().trim();
        String arguments = parts.length > 1 ? parts[1].trim() : "";

        return switch (command) {
            case "bye"      -> new ExitCommand();
            case "list"     -> new ListCommand();
            case "mark"     -> new MarkCommand(arguments, true);
            case "unmark"   -> new MarkCommand(arguments, false);
            case "delete"   -> new DeleteCommand(arguments);
            case "todo"     -> new TodoCommand(arguments);
            case "deadline" -> new DeadlineCommand(arguments);
            case "event"    -> new EventCommand(arguments);
            case "find"   -> new FindCommand(arguments);
            default         -> new InvalidCommand();
        };
    }
}