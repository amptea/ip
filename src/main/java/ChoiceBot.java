import java.awt.desktop.SystemSleepEvent;
import java.lang.management.BufferPoolMXBean;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class ChoiceBot {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final String BOT_NAME = "ChoiceBot";
        final String USER_NAME = "User";
        ArrayList<String> taskList = new ArrayList<>();

        System.out.println(BOT_NAME + ": Hello, Welcome to ChoiceBot!");
        System.out.println(BOT_NAME + ": What would you like to do?");
        System.out.println("---------------------------");
        while (true) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println("\t" + (i + 1) + ". " + taskList.get(i));
                    System.out.println("---------------------------");
                }
            } else {
                taskList.add(command);
                System.out.println("\tadded: " + command);
                System.out.println("---------------------------");
            }
        }
        System.out.println(BOT_NAME + ": Thanks for stopping by! See you again!");
    }
}
