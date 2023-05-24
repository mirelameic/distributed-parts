import java.util.Scanner;

public class UserInterface {
    private Scanner scanner;

    public UserInterface() {
        scanner = new Scanner(System.in);
    }

    public String getUserCommand() {
        System.out.print("> ");
        return scanner.nextLine().trim();
    }

    public static void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayError(String errorMessage, Exception exception) {
        System.err.println(errorMessage);
        exception.printStackTrace();
    }
}