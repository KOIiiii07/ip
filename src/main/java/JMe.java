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

        userInput = in.nextLine();
        userInput.replace("\n", "");

        while(!(userInput.toLowerCase().equals("bye")) ) {
            System.out.println(horizontalLine + "\n" + userInput + "\n" + horizontalLine);
            userInput = in.nextLine();
        }
        byeUser();
    }

    public static void main(String[] args) {
        greetUser();
        readUserInput();
    }
}
