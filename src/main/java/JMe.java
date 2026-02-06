import java.util.Scanner;

public class JMe {

    // constants or formats or static variables
    public static final String HORIZONTAL_LINE = "____________________________________________________________";
    public static final int MAX_TASKS = 100;
    public static Task[] tasks = new Task[MAX_TASKS];
    public static int itemCount = 0;

    private static void printInvalidFormatHelp() {
        System.out.println(HORIZONTAL_LINE + "\nInvalid Format\n");
        System.out.println("Command format: \"unmark/mark (number)\" or \"list\" or \"bye\".\n");
        System.out.println("Add task format:");
        System.out.println("\"todo __\"");
        System.out.println("\"deadline __ /by __\"");
        System.out.println("\"event __ /from __ /to __\"");
        System.out.println(HORIZONTAL_LINE);
    }

    private static void greetUser() {
        String logo = "      _ __  __\n"
                + "     | |  \\/  | ___\n"
                + "  _  | | |\\/| |/ _ \\\n"
                + " | |_| | |  | |  __/\n"
                + "  \\___/|_|  |_|\\___|\n";

        System.out.println(HORIZONTAL_LINE + "\nHello! I'm JMe!\n" + logo + "How may I assist you?\n" + HORIZONTAL_LINE);
    }

    private static void byeUser() {
        System.out.println(HORIZONTAL_LINE + "\nBye friend! See you soon!\n" + HORIZONTAL_LINE);
    }

    private static void printMessage (String message) {
        System.out.println(HORIZONTAL_LINE + "\n" + message + "\n" + HORIZONTAL_LINE);
    }

    public static boolean isDuplicate(String userInput) {
        for (int i = 0; i < itemCount; i++) {
            if (tasks[i].isEqual(userInput)) {
                return true;
            }
        }
        return false;
    }

    private static void addTodo(String userInput) {
        // 1. Check if tasks is full using the counter
        if (itemCount >= MAX_TASKS) {
            printMessage("The list is full");
            return;
        }

        // 2. Check for duplicates
        // We only loop up to 'itemCount' to avoid hitting null values
        if (isDuplicate(userInput)) {
            printMessage("There already exists such task.");
            return;
        }

        // 3. Store into list at the current index
        tasks[itemCount] = new Todo(userInput);

        // 4. Increment the counter so the next item goes to the next slot
        itemCount++;

        System.out.println(HORIZONTAL_LINE + "\nAdded: " + tasks[itemCount-1].toString());
        System.out.printf("Now you have %d tasks in the list.%n", itemCount);
        System.out.println(HORIZONTAL_LINE);
    }

    private static void addDeadline(String userInput) {
        // 1. Check if tasks is full using the counter
        if (itemCount >= MAX_TASKS) {
            printMessage("The list is full");
            return;
        }

        // 2. Check for duplicates
        // We only loop up to 'itemCount' to avoid hitting null values
        if (isDuplicate(userInput)) {
            printMessage("There already exists such task.");
            return;
        }

        // 3. Store into list at the current index
        String[] deadline = userInput.split("/by", 2);

        if (deadline.length != 2) {
            printMessage("Invalid format: \"deadline __ /by __\"");
            return;
        }
        String description = deadline[0].trim();
        String dueTime = deadline[1].trim();
        tasks[itemCount] = new Deadline(description, dueTime);

        // 4. Increment the counter so the next item goes to the next slot
        itemCount++;

        System.out.println(HORIZONTAL_LINE + "\nAdded: " + tasks[itemCount-1].toString());
        System.out.printf("Now you have %d tasks in the list.%n", itemCount);
        System.out.println(HORIZONTAL_LINE);
    }

    private static void addEvent(String userInput) {
        // 1. Check if tasks is full using the counter
        if (itemCount >= MAX_TASKS) {
            printMessage("The list is full");
            return;
        }

        // 2. Check for duplicates
        // We only loop up to 'itemCount' to avoid hitting null values
        if (isDuplicate(userInput)) {
            printMessage("There already exists such task.");
            return;
        }

        // 3. Store into list at the current index
        String[] event = userInput.split("/", 3);

        // check the validity of the format
        if (event.length != 3) {
            printMessage("Invalid format: \"event __ /from __ /to __\"");
            return;
        }

        String description = event[0].trim();
        String startTime = event[1].replace("from" , "").trim();
        String endTime = event[2].replace("to" , "").trim();
        tasks[itemCount] = new Event(description, startTime, endTime);

        // 4. Increment the counter so the next item goes to the next slot
        itemCount++;

        System.out.println(HORIZONTAL_LINE + "\nAdded: " + tasks[itemCount-1].toString());
        System.out.printf("Now you have %d tasks in the list.%n", itemCount);
        System.out.println(HORIZONTAL_LINE);
    }

    private static void displayTasks() {
        System.out.println(HORIZONTAL_LINE + "\nHere are your tasks:");

        // Only loop up to itemCount to avoid printing "null"
        for (int i = 0; i < itemCount; i++) {
            System.out.println((i+1) + "." + tasks[i].toString());
        }

        System.out.println(HORIZONTAL_LINE);
    }

    private static void updateTaskStatus(String arguments, boolean isDone) {
        try {
            int index = Integer.parseInt(arguments) - 1;
            if (index < 0 || index >= itemCount) {
                printMessage("Sorry! The index is out-of-bound.");
                return;
            }
            tasks[index].setDone(isDone);
            String msg = isDone ? "Nice! I've marked this task as done:" : "Ok! I've unmarked this task as done:";
            printMessage(msg + "\n  " + tasks[index]);
        } catch (NumberFormatException e) {
            printMessage("Error: '" + arguments + "' is not a valid number.");
        }
    }

    private static void processCommand(String command, String arguments) {
        switch (command) {
            // check for displaying the to-do-list
            case "list": {
                displayTasks();
                break;
            }

            // check for marking command
            case "mark": {
                updateTaskStatus(arguments, true);
                break;
            }

            // check for unmarking command
            case "unmark": {
                updateTaskStatus(arguments, false);
                break;
            }

            case "todo": {
                addTodo(arguments);
                break;
            }

            case "deadline": {
                addDeadline(arguments);
                break;
            }

            case "event": {
                addEvent(arguments);
                break;
            }

            default: {
                // check whether the command is valid or the task description is descriptive.
                printInvalidFormatHelp();
                break;
            }
        }
    }

    public static void runCommandInput() {
        Scanner in = new Scanner(System.in);

        while (true) {
            String userInput = in.nextLine();
            userInput = userInput.trim();

            if (userInput.isEmpty()) {
                System.out.println(HORIZONTAL_LINE + "\nInput cannot be empty!\n" + HORIZONTAL_LINE);
                continue;
            }

            String[] parts = userInput.split("\\s", 2);
            String command = parts[0].toLowerCase().trim();
            String arguments = parts.length > 1? parts[1].trim() : "";

            if (command.equals("bye")) {
                byeUser();
                break;
            }
            processCommand(command, arguments);
        }
    }

    public static void main(String[] args) {
        greetUser();
        runCommandInput();
    }
}