import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoanTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.print("Enter loan date (DD/MM/YYYY): ");
        String loanDate = scanner.nextLine();
        LocalDate dateOfLoan = LocalDate.parse(loanDate, dtf);

        System.out.print("Enter expected return date (DD/MM/YYYY): ");
        String returnDate = scanner.nextLine();
        LocalDate dateOfReturn = LocalDate.parse(returnDate, dtf);

        System.out.println("Available activities:");
        Activity.displayAllActivities();

        System.out.print("Enter activity number: ");
        int activityNumber = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Activity selectedActivity;
        try {
            selectedActivity = Activity.values()[activityNumber - 1];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid activity number selected.");
            scanner.close();
            return;
        }

        String filename = "equipment.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                Activity activityFromFile = extractActivities(line);
                if (activityFromFile != null) {
                    System.out.println("Extracted activity from file: " + activityFromFile.getDisplayName());
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        Loan loan = new Loan(1, dateOfLoan, dateOfReturn, 202, 505, 404, 4.0);

        // Display loan details
        loan.displayLoanDetails();

        // Save to file
        loan.saveToFile("loan.txt");

        scanner.close();
    }

    private static Activity extractActivities(String line) {
        try {
            // Find the start and end indices for the Activity substring
            int startIndex = line.indexOf("Activity: ") + "Activity: ".length();
            int endIndex = line.indexOf(",", startIndex); // Find the end of the activity substring
            if (startIndex >= "Activity: ".length() && endIndex > startIndex) {
                String activityStr = line.substring(startIndex, endIndex).trim();
                return Activity.fromDisplayName(activityStr); // Convert string to Activity enum
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid activity in line: " + line);
        }
        return null;
    }
}
