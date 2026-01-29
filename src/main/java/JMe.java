import java.util.Scanner;

public class JMe {

    // constants or formats or static variables
    public static final String horizontalLine = "____________________________________________________________";
    public static String[] list = new String[100];
    public static int itemCount = 0;

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

    public static void addList(String userInput) {
        // 1. Check if list is full using the counter
        if (itemCount >= list.length) {
            System.out.println(horizontalLine + "\nThe list is full\n" + horizontalLine);
            return;
        }

        // 2. Check for duplicates
        // We only loop up to 'itemCount' to avoid hitting null values
        for (int i = 0; i < itemCount; i++) {
            if (list[i].equals(userInput)) {
                System.out.println(horizontalLine + "\nThere already exists such string.\n" + horizontalLine);
                return;
            }
        }

        // 3. Store into list at the current index
        list[itemCount] = userInput;

        // 4. Increment the counter so the next item goes to the next slot
        itemCount++;

        System.out.println(horizontalLine + "\nAdded: " + userInput + "\n" + horizontalLine);
    }

    public static void displayList() {
        System.out.println(horizontalLine);

        // Only loop up to itemCount to avoid printing "null"
        for (int i = 0; i < itemCount; i++) {
            System.out.println((i + 1) + ". " + list[i]);
        }

        System.out.println(horizontalLine);
    }

    public static void readUserInput() {
        String userInput;
        Scanner in = new Scanner(System.in);

        while (true) {
            userInput = in.nextLine();
            String command = userInput.trim();

            // 1. Check for empty input (whitespace included)
            if (command.isEmpty()) {
                System.out.println(horizontalLine + "\nInput cannot be empty!\n" + horizontalLine);
                continue;
            }

            // 2. Check for exit command
            if (command.equalsIgnoreCase("bye")) {
                byeUser();
                break;
            }

            // 3. Check for list command
            if (command.equalsIgnoreCase("list")) {
                displayList();
            } else {
                //4. Add into the list
                addList(command);
            }
        }
    }

    public static void main(String[] args) {
        greetUser();
        readUserInput();
    }
}