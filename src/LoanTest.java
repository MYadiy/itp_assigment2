import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

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

        System.out.print("Enter activity no: ");
        String activityName = scanner.nextLine();

        

        Loan loan = new Loan(1, dateOfLoan, dateOfReturn, 202, 505, 404, 4.0 );

        // Display loan details
        loan.displayLoanDetails();

        // Save to file
        loan.saveToFile("loan.txt");

        scanner.close();
    }
}
