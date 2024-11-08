import java.time.DayOfWeek;
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
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid activity number selected.");
            scanner.close();
            return;
        }

        System.out.println("Equipment no, Equipment name, Description Cost weekend, Cost per week");

        try (BufferedReader br = new BufferedReader(new FileReader("equipment.txt"))) {
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

        System.out.print("Enter equipment number: ");
        int equipmentNumber = scanner.nextInt();

        System.out.println("Person who is hiring:");
        try (BufferedReader br = new BufferedReader(new FileReader("members.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("Member")) {
                    System.out.println("" + line);
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        int selectedMember = scanner.nextInt();

        String gearOfficer = null;

        try (BufferedReader br = new BufferedReader(new FileReader("members.txt"))) {
            String line;

            while ((line = br.readLine()) != null) {
                if (line.contains("Member Number: " + selectedMember)) {
                    int startIndex = line.indexOf("Name: ") + "Name: ".length();
                    int endIndex = line.indexOf(",", startIndex);

                    if (startIndex != -1 && endIndex != -1) {
                        gearOfficer = line.substring(startIndex, endIndex).trim();
                    }
                    break;
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
// _______________
Double cost = null;

if (isWeekend(dateOfLoan)) {
    try (BufferedReader br = new BufferedReader(new FileReader("equipment.txt"))) {
        String line;

        while ((line = br.readLine()) != null) {
            if (line.contains("Equipment Number: " + equipmentNumber)) {
                int startIndex = line.indexOf("Hire Cost (Weekend): ") + "Hire Cost (Weekend): ".length();
                int endIndex = line.indexOf(" ", startIndex); // Find the space after the cost or end of line
                if (endIndex == -1) endIndex = line.length(); // Handle end of line

                String costString = line.substring(startIndex, endIndex).trim();
                try {
                    cost = Double.parseDouble(costString); 
                } catch (NumberFormatException e) {
                    System.out.println("Error parsing cost value: " + costString);
                }
                break; 
            }
        }

    } catch (IOException e) {
        System.out.println("Error reading file: " + e.getMessage());
    }

} else {
    try (BufferedReader br = new BufferedReader(new FileReader("equipment.txt"))) {
        String line;

        while ((line = br.readLine()) != null) {
            if (line.contains("Equipment Number: " + equipmentNumber)) {
                int startIndex = line.indexOf("Hire Cost (Week): ") + "Hire Cost (Week): ".length();
                int endIndex = line.indexOf(" ", startIndex);
                if (endIndex == -1) endIndex = line.length();

                String costString = line.substring(startIndex, endIndex).trim();
                try {
                    cost = Double.parseDouble(costString); 
                } catch (NumberFormatException e) {
                    System.out.println("Error parsing cost value: " + costString);
                }
                break; 
            }
        }

    } catch (IOException e) {
        System.out.println("Error reading file: " + e.getMessage());
    }
}


// ___________
if (cost == null) {
    System.out.println("No valid hire cost found for the selected equipment.");
    scanner.close();
    return; 
}

if (gearOfficer != null) {
    Loan loan = new Loan(1, dateOfLoan, dateOfReturn, equipmentNumber, selectedMember, gearOfficer, cost);
    loan.displayLoanDetails();
    loan.saveToFile("loan.txt");
} else {
    System.out.println("Cannot proceed with loan creation without a valid gear officer.");
}
scanner.close();

    }

    public static boolean isWeekend(LocalDate dateOfLoan) {
        DayOfWeek dayOfWeek = dateOfLoan.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }
}
