import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class Loan {    
    private int loanNo;
    private LocalDate dateOfLoan;
    private LocalDate dateOfReturn;
    private int equipmentNo;
    private int memberNo;
    private String gearOfficer;
    private int cost;

    public Loan(int loanNo, LocalDate dateOfLoan, LocalDate dateOfReturn,int equipmentNo, int memberNo, String gearOfficer, int cost) {
        this.loanNo = loanNo;
        this.dateOfLoan = dateOfLoan;
        this.dateOfReturn = dateOfReturn;
        this.equipmentNo = equipmentNo;
        this.memberNo = memberNo;
        this.gearOfficer = gearOfficer;
        this.cost = cost; 
    } 

    public void saveToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write("Loan.No: " + loanNo + ", LoanDate: " + dateOfLoan + ", ReturnDate: " + dateOfReturn + ", Item.No: " + equipmentNo + ", Member.No: " + memberNo + ", GearOfficer: " + gearOfficer + ", Cost: " + cost);
            writer.newLine(); 
        } catch (IOException e) {
            System.out.println("Error" + e.getMessage());
        }
    }

    public void displayLoanDetails() {
        System.out.println("Loan No: " + loanNo);
        System.out.println("Loan Date: " + dateOfLoan);
        System.out.println("Return Date: " + dateOfReturn);
        System.out.println("Equipment No: " + equipmentNo);
        System.out.println("Member No: " + memberNo);
        System.out.println("Gear Officer: " + gearOfficer);
        System.out.println("Cost: $" + cost);
    }
}
