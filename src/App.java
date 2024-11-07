import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        Loan loan = new Loan(1, LocalDate.of(2024, 11, 1), LocalDate.of(2024, 11, 8), 202, 505, 404);
        
        //ANI EXAMPLE SJA UNTUK CALCULATE COST. NANTI PAKAI SCANNER
        loan.costTotal(10.0, 30.0);

        //ANI  UNTUK DISPLAY
        loan.displayLoanDetails();
        
        // ANI SAVE KE FILE LOAN.TXT NAMANYA
        loan.saveToFile("loan.txt");
    }
}
