import java.util.Scanner;

public class JMe {

    // constants or formats or static variables
    public static final String horizontalLine = "____________________________________________________________";
    public static Task[] Tasks = new Task[100];
    public static int itemCount = 0;

    public static void greetUser() {
        String logo = "      _ __  __\n"
                + "     | |  \\/  | ___\n"
                + "  _  | | |\\/| |/ _ \\\n"
                + " | |_| | |  | |  __/\n"
                + "  \\___/|_|  |_|\\___|\n";

        System.out.println(horizontalLine + "\nHello! I'm JMe!\n" + logo + "How may I assist you?\n" + horizontalLine);
    }

    public static void byeUser() {
        System.out.println(horizontalLine + "\nBye friend! See you soon!\n" + horizontalLine);
    }

    public static Boolean isThereDuplicates(String userInput) {
        for (int i = 0; i < itemCount; i++) {
            if (Tasks[i].isEqual(userInput)) {
                return true;
            }
        }
        return false;
    }

    public static void addTask(String userInput) {
        // 1. Check if tasks is full using the counter
        if (itemCount >= Tasks.length) {
            System.out.println(horizontalLine + "\nThe list is full\n" + horizontalLine);
            return;
        }

        // 2. Check for duplicates
        // We only loop up to 'itemCount' to avoid hitting null values
        if (isThereDuplicates(userInput)) {
            System.out.println(horizontalLine + "\nThere already exists such task.\n" + horizontalLine);
            return;
        }

        // 3. Store into list at the current index
        Tasks[itemCount] = new Task(userInput);

        // 4. Increment the counter so the next item goes to the next slot
        itemCount++;

        System.out.println(horizontalLine + "\nAdded: " + userInput + "\n" + horizontalLine);
    }

    public static void displayTasks() {
        System.out.println(horizontalLine + "\nHere are your tasks:\n");
        String doneMarker = "";

        // Only loop up to itemCount to avoid printing "null"
        for (int i = 0; i < itemCount; i++) {
            doneMarker = (Tasks[i].isDone? "X" : " ");
            System.out.println((i + 1) + ". " + "[" + doneMarker + "] "+ Tasks[i].description);
        }

        System.out.println(horizontalLine);
    }

    public static void markTask(int index) {
        if (index < 0 || index >= itemCount) {
            System.out.println(horizontalLine + "\nSorry! The index is out-of-bound.\n" + horizontalLine);
            return;
        }

        Tasks[index].isDone = true;
        System.out.println(horizontalLine + "\nNice! I've marked this task as done:\n" + "  [X] " +
                        Tasks[index].description + "\n" + horizontalLine);
    }

    public static void unmarkTask(int index) {
        if (index < 0 || index >= itemCount) {
            System.out.println(horizontalLine + "\nSorry! The index is out-of-bound.\n" + horizontalLine);
            return;
        }

        Tasks[index].isDone = false;
        System.out.println(horizontalLine + "\nOk! I've unmarked this task as done:\n" + "  [ ] " +
                Tasks[index].description + "\n" + horizontalLine);
    }

    public static void readUserInput() {
        String userInput;
        Scanner in = new Scanner(System.in);

        while (true) {
            userInput = in.nextLine();
            userInput = userInput.trim();

            if (userInput.isEmpty()) {
                System.out.println(horizontalLine + "\nInput cannot be empty!\n" + horizontalLine);
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
                        System.out.println(horizontalLine + "\nError: '" + command[1] + "' is not a valid number.\n"
                                + horizontalLine);
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
                        System.out.println(horizontalLine + "\nError: '" + command[1] + "' is not a valid number.\n"
                        + horizontalLine);
                    }
                    break;
                }

                default: {
                    // check whether the command is valid or the task description is descriptive.
                    if (command.length == 1 || command[1].isEmpty()) {
                        System.out.println(horizontalLine + "\nInvalid Format\n");
                        System.out.println("Command format: \"unmark/mark [number]\" or \"list\" or \"bye\".\n");
                        System.out.println("Add task format: *two words minimum.*\n" + horizontalLine);
                    } else {
                        addTask(userInput);
                    }
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