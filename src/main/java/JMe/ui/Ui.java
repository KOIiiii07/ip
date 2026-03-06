package JMe.ui;

import JMe.task.Task;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Handles all user interaction for JMe, including reading input
 * and printing formatted output to the console.
 */
public class Ui {
    private static final String HORIZONTAL_LINE =
            "____________________________________________________________";
    private final Scanner scanner;

    /**
     * Constructs a Ui instance with a Scanner for reading standard input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads a line of input from the user.
     *
     * @return The trimmed user input string.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints a message enclosed by horizontal lines.
     *
     * @param message The message to display.
     */
    public static void printMessage(String message) {
        System.out.println(HORIZONTAL_LINE + "\n" + message + "\n"
                + HORIZONTAL_LINE);
    }

    /**
     * Prints the welcome message with the JMe logo.
     */
    public static void printWelcome() {
        String logo = "      _ __  __\n"
                + "     | |  \\/  | ___\n"
                + "  _  | | |\\/| |/ _ \\\n"
                + " | |_| | |  | |  __/\n"
                + "  \\___/|_|  |_|\\___|\n";
        printMessage("Hello! I'm JMe!\n" + logo + "How may I assist you?");
    }

    /**
     * Prints the goodbye message when the user exits.
     */
    public static void printGoodbye() {
        printMessage("Bye friend! See you soon!");
    }

    /**
     * Prints a help message listing all valid command formats.
     */
    public static void printInvalidFormatHelp() {
        printMessage("INVALID FORMAT\n"
                + "Command format: \n\"unmark/mark (number)\" \n\"delete (number)\" \n\"list\" \n" +
                "\"bye\".\n\"find (__)\".\n\n"
                + "Add task format: \n\"todo (__)\" \n\"deadline (__) /by (yyyy-MM-dd HHmm)\""
                + "\n\"event (__) /from (__) /to (__)\"");
    }

    /**
     * Prints a confirmation message after a task is added.
     *
     * @param task      The task that was added.
     * @param taskCount The total number of tasks after adding.
     */
    public static void printTaskAdded(Task task, int taskCount) {
        printMessage("Added: " + task.toString()
                + String.format("\nNow you have %d tasks in the list.", taskCount));
    }

    /**
     * Prints a confirmation message after a task is deleted.
     *
     * @param task      The task that was deleted.
     * @param taskCount The total number of tasks after deletion.
     */
    public static void printTaskDeleted(Task task, int taskCount) {
        printMessage("Deleted: " + task.toString()
                + String.format("\nNow you have %d tasks in the list.", taskCount));
    }

    /**
     * Prints the updated status of a task after marking or unmarking.
     *
     * @param task The task whose status was updated.
     */
    public static void printTaskStatus(Task task) {
        String msg = task.getStatus()? "Nice! I've marked this task as done:" : "Ok! I've unmarked this task as done:";
        printMessage(msg + "\n  " + task.toString());
    }

    /**
     * Prints all tasks in the task list with their 1-based index.
     *
     * @param tasks     The list of tasks to display.
     * @param taskCount The number of tasks in the list.
     */
    public static void printTaskList(ArrayList<Task> tasks, int taskCount) {
        System.out.println(HORIZONTAL_LINE + "\nHere are your tasks:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i+1) + "." + tasks.get(i).toString());
        }
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints the tasks that match a search keyword, with 1-based indices.
     *
     * @param tasks     The list of matching tasks.
     * @param taskCount The number of matching tasks.
     */
    public static void printFindTasks(ArrayList<Task> tasks, int taskCount) {
        System.out.println(HORIZONTAL_LINE + "\nHere are your matching tasks:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i+1) + "." + tasks.get(i).toString());
        }
        System.out.println(HORIZONTAL_LINE);
    }
}