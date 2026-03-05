package JMe.ui;

import JMe.task.Task;

import java.util.Scanner;
import java.util.ArrayList;

public class Ui {
    private static final String HORIZONTAL_LINE =
            "____________________________________________________________";
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public static void printMessage(String message) {
        System.out.println(HORIZONTAL_LINE + "\n" + message + "\n"
                + HORIZONTAL_LINE);
    }

    public static void printWelcome() {
        String logo = "      _ __  __\n"
                + "     | |  \\/  | ___\n"
                + "  _  | | |\\/| |/ _ \\\n"
                + " | |_| | |  | |  __/\n"
                + "  \\___/|_|  |_|\\___|\n";
        printMessage("Hello! I'm JMe!\n" + logo + "How may I assist you?");
    }

    public static void printGoodbye() {
        printMessage("Bye friend! See you soon!");
    }

    public static void printInvalidFormatHelp() {
        printMessage("INVALID FORMAT\n"
                + "Command format: \n\"unmark/mark (number)\" \n\"delete (number)\" \n\"list\" \n" +
                "\"bye\".\n\"find (__)\".\n\n"
                + "Add task format: \n\"todo (__)\" \n\"deadline (__) /by (__)\""
                + "\n\"event (__) /from (__) /to (__)\"");
    }

    public static void printTaskAdded(Task task, int taskCount) {
        printMessage("Added: " + task.toString()
                + String.format("\nNow you have %d tasks in the list.", taskCount));
    }

    public static void printTaskDeleted(Task task, int taskCount) {
        printMessage("Deleted: " + task.toString()
                + String.format("\nNow you have %d tasks in the list.", taskCount));
    }

    public static void printTaskStatus(Task task) {
        String msg = task.getStatus()? "Nice! I've marked this task as done:" : "Ok! I've unmarked this task as done:";
        printMessage(msg + "\n  " + task.toString());
    }

    public static void printTaskList(ArrayList<Task> tasks, int taskCount) {
        System.out.println(HORIZONTAL_LINE + "\nHere are your tasks:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i+1) + "." + tasks.get(i).toString());
        }
        System.out.println(HORIZONTAL_LINE);
    }

    public static void printFindTasks(ArrayList<Task> tasks, int taskCount) {
        System.out.println(HORIZONTAL_LINE + "\nHere are your matching tasks:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println((i+1) + "." + tasks.get(i).toString());
        }
        System.out.println(HORIZONTAL_LINE);
    }
}