import java.util.Scanner;

public class JMe {

    // constants or formats or static variables
    public static final String HORIZONTAL_LINE = "____________________________________________________________";
    public static final int MAX_TASKS = 100;
    public static Task[] tasks = new Task[MAX_TASKS];
    public static int itemCount = 0;

    private static void printInvalidFormatHelp() {
        System.out.println(HORIZONTAL_LINE + "\nINVALID FORMAT\n");
        System.out.println("Command format: \n\"unmark/mark (number)\" \n\"list\" \n\"bye\".\n");
        System.out.println("Add task format: \n\"todo (__)\" \n\"deadline (__) /by (__)\"" +
                "\n\"event (__) /from (__) /to (__)\"");
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

    public static void printMessage (String message) {
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

    private static void addTodo(String userInput) throws JMeException.OutOfBounds, JMeException.InvalidFormat,
            JMeException.Duplicates {
        // 1. Check if tasks is full using the counter
        if (itemCount >= MAX_TASKS) {
            throw new JMeException.OutOfBounds();
        }

        // 2. Check for duplicates
        // We only loop up to 'itemCount' to avoid hitting null values
        if (isDuplicate(userInput)) {
            throw new JMeException.Duplicates();
        }

        // 3. Check for empty input
        if (userInput.isEmpty()) {
            throw new JMeException.InvalidFormat();
        }

        // 4. Store into list at the current index
        tasks[itemCount] = new Todo(userInput);

        // 5. Increment the counter so the next item goes to the next slot
        itemCount++;

        System.out.println(HORIZONTAL_LINE + "\nAdded: " + tasks[itemCount-1].toString());
        System.out.printf("Now you have %d tasks in the list.%n", itemCount);
        System.out.println(HORIZONTAL_LINE);
    }

    private static void addDeadline(String userInput) throws JMeException.OutOfBounds, JMeException.InvalidFormat,
            JMeException.Duplicates {
        // 1. Check if tasks is full using the counter
        if (itemCount >= MAX_TASKS) {
            throw new JMeException.OutOfBounds();
        }

        // 2. Check for duplicates
        // We only loop up to 'itemCount' to avoid hitting null values
        if (isDuplicate(userInput)) {
            throw new JMeException.Duplicates();
        }

        // 3. Store into list at the current index
        String[] deadline = userInput.split("/by", 2);

        if (deadline.length != 2) {
            throw new JMeException.InvalidFormat();
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

    private static void addEvent(String userInput) throws JMeException.OutOfBounds, JMeException.InvalidFormat,
            JMeException.Duplicates {
            // 1. Check if tasks is full using the counter
            if (itemCount >= MAX_TASKS) {
                throw new JMeException.OutOfBounds();
            }

            // 2. Check for duplicates
            // We only loop up to 'itemCount' to avoid hitting null values
            if (isDuplicate(userInput)) {
                throw new JMeException.Duplicates();
            }

            String[] event = userInput.split("/", 3);

            // check the validity of the format
            if (event.length != 3) {
                throw new JMeException.InvalidFormat();
            }

            // 3. Store into list at the current index
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

    private static void updateTaskStatus(String arguments, boolean isDone) throws JMeException.OutOfBounds {

        int index = Integer.parseInt(arguments) - 1;
        if (index < 0 || index >= itemCount) {
            throw new JMeException.OutOfBounds();
        }
        tasks[index].setDone(isDone);
        String msg = isDone ? "Nice! I've marked this task as done:" : "Ok! I've unmarked this task as done:";
        printMessage(msg + "\n  " + tasks[index]);

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
                try {
                    updateTaskStatus(arguments, true);
                } catch (NumberFormatException e) {
                    printMessage("Error: '" + arguments + "' is not a valid number.");
                } catch (JMeException.OutOfBounds e) {
                    printMessage("Your index is out-of-bound.");
                }
                break;
            }

            // check for unmarking command
            case "unmark": {
                try {
                    updateTaskStatus(arguments, false);
                } catch (NumberFormatException e) {
                    printMessage("Error: '" + arguments + "' is not a valid number.");
                } catch (JMeException.OutOfBounds e) {
                    printMessage("Your index is out-of-bound.");
                }
                break;
            }

            case "todo": {
                try {
                    addTodo(arguments);
                } catch (JMeException.OutOfBounds e) {
                    printMessage("The list is full");
                } catch (JMeException.Duplicates e) {
                    printMessage("There already exists such task.");
                } catch (JMeException.InvalidFormat e) {
                    printMessage("Invalid format: \"todo (__) \"");
                }
                break;
            }

            case "deadline": {
                try {
                    addDeadline(arguments);
                } catch (JMeException.OutOfBounds e) {
                    printMessage("The list is full");
                } catch (JMeException.Duplicates e) {
                    printMessage("There already exists such task.");
                } catch (JMeException.InvalidFormat e) {
                    printMessage("Invalid format: \"deadline (__) /by (__)\"");
                }
                break;
            }

            case "event": {
                try {
                    addEvent(arguments);
                } catch (JMeException.OutOfBounds e) {
                    printMessage("The list is full");
                } catch (JMeException.Duplicates e) {
                    printMessage("There already exists such task.");
                } catch (JMeException.InvalidFormat e) {
                    printMessage("Invalid format: \"event (__) /from (__) /to (__)\"");
                }
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
                printMessage("Input cannot be empty.");
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