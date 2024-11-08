import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Loan {    
    private int loanNo;
    private LocalDate dateOfLoan;
    private LocalDate dateOfReturn;
    private int itemNo;
    private int memberNo;
    private int gearOfficerNo;
    private double cost;

    public Loan(int loanNo, LocalDate dateOfLoan, LocalDate dateOfReturn,int itemNo, int memberNo, int gearOfficerNo, double cost) {
        this.loanNo = loanNo;
        this.dateOfLoan = dateOfLoan;
        this.dateOfReturn = dateOfReturn;
        this.itemNo = itemNo;
        this.memberNo = memberNo;
        this.gearOfficerNo = gearOfficerNo;
        this.cost = 0.0; 
    }

    public double costTotal(double costPerWeekend, double costPerWeek) {
        long days = ChronoUnit.DAYS.between(dateOfLoan, dateOfReturn);
        if (days > 7) {
            this.cost = (days / 7) * costPerWeek;
        } else {
            this.cost = (days / 2) * costPerWeekend;
        }
        return this.cost;
    }

    public void saveToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write("Loan.No: " + loanNo + ", LoanDate: " + dateOfLoan + ", ReturnDate: " + dateOfReturn + ", Item.No: " + itemNo + ", Member.No: " + memberNo + ", GearOfficer.No: " + gearOfficerNo + ", Cost: " + cost);
            writer.newLine(); 
        } catch (IOException e) {
            System.out.println("Error" + e.getMessage());
        }
    }

    // Displaying the data 
    public void displayLoanDetails() {
        // System.out.println("Loan No: " + loanNo);
        System.out.println("Loan Date: " + dateOfLoan);
        System.out.println("Return Date: " + dateOfReturn);
        // System.out.println("Item No: " + itemNo);
        // System.out.println("Member No: " + memberNo);
        // System.out.println("Gear Officer No: " + gearOfficerNo);
        // System.out.println("Cost: $" + cost);
    }

    //if overdue
    // public boolean isOverdue() {
    //     return LocalDate.now().isAfter(dateOfReturn);
    // }

}
