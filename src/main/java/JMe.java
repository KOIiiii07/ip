import java.util.Scanner;

public class JMe {

    // constants or formats or static variables
    public static final String HORIZONTAL_LINE = "____________________________________________________________";
    public static Task[] tasks = new Task[100];
    public static int itemCount = 0;

    public static void greetUser() {
        String logo = "      _ __  __\n"
                + "     | |  \\/  | ___\n"
                + "  _  | | |\\/| |/ _ \\\n"
                + " | |_| | |  | |  __/\n"
                + "  \\___/|_|  |_|\\___|\n";

        System.out.println(HORIZONTAL_LINE + "\nHello! I'm JMe!\n" + logo + "How may I assist you?\n" + HORIZONTAL_LINE);
    }

    public static void byeUser() {
        System.out.println(HORIZONTAL_LINE + "\nBye friend! See you soon!\n" + HORIZONTAL_LINE);
    }

    public static boolean areThereDuplicates(String userInput) {
        for (int i = 0; i < itemCount; i++) {
            if (tasks[i].isEqual(userInput)) {
                return true;
            }
        }
        return false;
    }

    public static void addTodo(String userInput) {
        // 1. Check if tasks is full using the counter
        if (itemCount >= tasks.length) {
            System.out.println(HORIZONTAL_LINE + "\nThe list is full\n" + HORIZONTAL_LINE);
            return;
        }

        // 2. Check for duplicates
        // We only loop up to 'itemCount' to avoid hitting null values
        if (areThereDuplicates(userInput)) {
            System.out.println(HORIZONTAL_LINE + "\nThere already exists such task.\n" + HORIZONTAL_LINE);
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

    public static void addDeadline(String userInput) {
        // 1. Check if tasks is full using the counter
        if (itemCount >= tasks.length) {
            System.out.println(HORIZONTAL_LINE + "\nThe list is full\n" + HORIZONTAL_LINE);
            return;
        }

        // 2. Check for duplicates
        // We only loop up to 'itemCount' to avoid hitting null values
        if (areThereDuplicates(userInput)) {
            System.out.println(HORIZONTAL_LINE + "\nThere already exists such task.\n" + HORIZONTAL_LINE);
            return;
        }

        // 3. Store into list at the current index
        String[] deadline = userInput.split("/by", 2);

        if (deadline.length != 2) {
            System.out.println(HORIZONTAL_LINE + "\nInvalid format: \"deadline __ /by __\"\n" + HORIZONTAL_LINE);
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

    public static void addEvent(String userInput) {
        // 1. Check if tasks is full using the counter
        if (itemCount >= tasks.length) {
            System.out.println(HORIZONTAL_LINE + "\nThe list is full\n" + HORIZONTAL_LINE);
            return;
        }

        // 2. Check for duplicates
        // We only loop up to 'itemCount' to avoid hitting null values
        if (areThereDuplicates(userInput)) {
            System.out.println(HORIZONTAL_LINE + "\nThere already exists such task.\n" + HORIZONTAL_LINE);
            return;
        }

        // 3. Store into list at the current index
        String[] event = userInput.split("/", 3);

        // check the validity of the format
        if (event.length != 3) {
            System.out.println(HORIZONTAL_LINE + "\nInvalid format: \"event __ /from __ /to __\"\n" + HORIZONTAL_LINE);
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

    public static void displayTasks() {
        System.out.println(HORIZONTAL_LINE + "\nHere are your tasks:");

        // Only loop up to itemCount to avoid printing "null"
        for (int i = 0; i < itemCount; i++) {
            System.out.println((i+1) + "." + tasks[i].toString());
        }

        System.out.println(HORIZONTAL_LINE);
    }

    public static void markTask(int index) {
        if (index < 0 || index >= itemCount) {
            System.out.println(HORIZONTAL_LINE + "\nSorry! The index is out-of-bound.\n" + HORIZONTAL_LINE);
            return;
        }

        tasks[index].isDone = true;
        System.out.println(HORIZONTAL_LINE + "\nNice! I've marked this task as done:\n" +
                tasks[index].toString() + "\n" + HORIZONTAL_LINE);
    }

    public static void unmarkTask(int index) {
        if (index < 0 || index >= itemCount) {
            System.out.println(HORIZONTAL_LINE + "\nSorry! The index is out-of-bound.\n" + HORIZONTAL_LINE);
            return;
        }

        tasks[index].isDone = false;
        System.out.println(HORIZONTAL_LINE + "\nOk! I've unmarked this task as done:\n" +
                tasks[index].toString() + "\n" + HORIZONTAL_LINE);
    }

    public static void readUserInput() {
        String userInput;
        Scanner in = new Scanner(System.in);

        while (true) {
            userInput = in.nextLine();
            userInput = userInput.trim();

            if (userInput.isEmpty()) {
                System.out.println(HORIZONTAL_LINE + "\nInput cannot be empty!\n" + HORIZONTAL_LINE);
                continue;
            }

            String[] command = userInput.split("\\s", 2);
            command[0] = command[0].toLowerCase().trim();

            switch (command[0]) {
                // check for exit command
                case "bye": {
                    byeUser();
                    return;
                }

                // check for displaying the to-do-list
                case "list": {
                    displayTasks();
                    break;
                }

                // check for marking command
                case "mark": {
                    //check whether the format is correct
                    try {
                        int index = Integer.parseInt(command[1]);
                        markTask(index - 1);
                    } catch (NumberFormatException e) {
                        System.out.println(HORIZONTAL_LINE + "\nError: '" + command[1] + "' is not a valid number.\n"
                                + HORIZONTAL_LINE);
                    }
                    break;
                }

                // check for unmarking command
                case "unmark": {
                    // check whether the format is correct
                    try {
                        int index = Integer.parseInt(command[1]);
                        unmarkTask(index - 1);
                    } catch (NumberFormatException e) {
                        System.out.println(HORIZONTAL_LINE + "\nError: '" + command[1] + "' is not a valid number.\n"
                        + HORIZONTAL_LINE);
                    }
                    break;
                }

                case "todo": {
                    if (command[1].trim().isEmpty()) {
                        System.out.println(HORIZONTAL_LINE + "\nInvalid format: \"todo __\"\n"
                                + HORIZONTAL_LINE);
                    } else {
                        addTodo(command[1]);
                    }
                    break;
                }

                case "deadline": {
                    if (command[1].trim().isEmpty()) {
                        System.out.println(HORIZONTAL_LINE + "\nInvalid format: \"deadline __ /by __\"\n"
                                + HORIZONTAL_LINE);
                    } else {
                        addDeadline(command[1]);
                    }
                    break;
                }

                case "event": {
                    if (command[1].trim().isEmpty()) {
                        System.out.println(HORIZONTAL_LINE + "\nInvalid format: \"event __ /from __ /to __\"\n"
                                + HORIZONTAL_LINE);
                    } else {
                        addEvent(command[1]);
                    }
                    break;
                }

                default: {
                    // check whether the command is valid or the task description is descriptive.
                    System.out.println(HORIZONTAL_LINE + "\nInvalid Format\n");
                    System.out.println("Command format: \"unmark/mark (number)\" or \"list\" or \"bye\".\n");
                    System.out.println("Add task format:");
                    System.out.println("\"todo __\"");
                    System.out.println("\"deadline __ /by __\"");
                    System.out.println("\"event __ /from __ /to __\"");
                    System.out.println(HORIZONTAL_LINE);
                    break;
                }

            }
        }
    }

    public static void main(String[] args) {
        greetUser();
        readUserInput();
    }
}