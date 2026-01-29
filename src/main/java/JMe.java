import java.util.Scanner;

public class JMe {

    //constants or formats
    public static final String horizontalLine = "____________________________________________________________";

    public static void greetUser() {
        String logo = "      _ __  __\n"
                + "     | |  \\/  | ___\n"
                + "  _  | | |\\/| |/ _ \\\n"
                + " | |_| | |  | |  __/\n"
                + "  \\___/|_|  |_|\\___|\n";

        System.out.println(horizontalLine + "\nHello! I'm JMe!\n" + logo + "What can I do for you?\n" + horizontalLine);
    }

    public static void byeUser() {
        System.out.println(horizontalLine + "\nBye. Hope to see you again soon!\n" + horizontalLine);
    }

    public static void readUserInput() {
        String userInput;
        Scanner in = new Scanner(System.in);

        while (true) {
            userInput = in.nextLine();

            // 1. Check for empty input (whitespace included)
            if (userInput.trim().isEmpty()) {
                throw new IllegalArgumentException("Input cannot be empty!");
            }

            // 2. Check for exit command
            if (userInput.equalsIgnoreCase("bye")) {
                byeUser();
                break;
            }

            // 3. Echo the input
            System.out.println(horizontalLine + "\n" + userInput + "\n" + horizontalLine);
        }
    }

    public static void main(String[] args) {
        greetUser();
        readUserInput();
    }
}
