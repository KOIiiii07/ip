package JMe.parser;

import JMe.command.*;

public class Parser {

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
            default         -> new InvalidCommand();
        };
    }
}