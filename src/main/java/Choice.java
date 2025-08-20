import java.util.Scanner;

public class Choice {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello, Welcome to Choice!");
        System.out.println("What would you like to do?");
        System.out.println("---------------------------");
        while (true) {
            String command = sc.next();
            if (command.equals("bye")) {
                break;
            }
            System.out.println(command);
        }
        System.out.println("Thanks for stopping by! See you again!");
    }
}
