import java.util.Scanner;

public class ActivitySelectionExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select an activity:");
        Activity.displayAllActivities();
        System.out.print("Enter activity number: ");
        int choice = scanner.nextInt();

        try {
            Activity selectedActivity = Activity.values()[choice - 1];
            System.out.println("You selected: " + selectedActivity.getDisplayName());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid choice, please try again.");
        }

        scanner.close();
    }
}
