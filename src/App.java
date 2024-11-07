import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        Loan loan = new Loan(1, LocalDate.of(2024, 11, 1), LocalDate.of(2024, 11, 8), 202, 505, 404);
        
        // Calculate cost for example
        loan.costTotal(10.0, 30.0);

        // Display loan details (for verification)
        loan.displayLoanDetails();
        
        // Save loan data to file
        loan.saveToFile("loan.txt");
    }
}
