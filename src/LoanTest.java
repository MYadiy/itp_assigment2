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

        Loan loan = new Loan(1, dateOfLoan, dateOfReturn, 202, 505, 404, 4.0);
        
        loan.displayLoanDetails();
        
        // Save to file named loan.txt
        loan.saveToFile("loan.txt");

        scanner.close();
    }
}
