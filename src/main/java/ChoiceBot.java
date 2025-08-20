import java.util.Scanner;

public class ChoiceBot {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final String BOT_NAME = "ChoiceBot";
        final String USER_NAME = "User";
        System.out.println(BOT_NAME + ": Hello, Welcome to Choice!");
        System.out.println(BOT_NAME + ": What would you like to do?");
        System.out.println("---------------------------");
        while (true) {
            String command = sc.next();
            if (command.equals("bye")) {
                break;
            }
            System.out.println(BOT_NAME + ": " + command);
        }
        System.out.println("Thanks for stopping by! See you again!");
    }
}
