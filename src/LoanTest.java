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
        scanner.nextLine(); 

        Activity selectedActivity;
        try {
            selectedActivity = Activity.values()[activityNumber - 1];
            // System.out.println("Selected activity: " + selectedActivity.getDisplayName());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid activity number selected.");
            scanner.close();
            return;
        }

        String filename = "equipment.txt";
        System.out.println("Equipment no, Equipment name, Description Cost weekend, Cost per week");

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean equipmentFound = false;

            while ((line = br.readLine()) != null) {
                if (line.contains("Activity: " + selectedActivity.getDisplayName())) {
                    System.out.println("" + line);
                    equipmentFound = true;
                }
            }

            if (!equipmentFound) {
                System.out.println("No equipment found for the selected activity.");
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        Loan loan = new Loan(1, dateOfLoan, dateOfReturn, 202, 505, 404, 4.0);

        // loan.displayLoanDetails();

        loan.saveToFile("loan.txt");

        scanner.close();
    }
}
