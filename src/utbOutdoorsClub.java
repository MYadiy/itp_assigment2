import java.lang.reflect.Member;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class utbOutdoorsClub {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int memberCount = member.countLines("members.txt");
        System.out.println("Loaded " + memberCount + " members");
        int equipmentCount = member.countLines("equipment.txt");
        System.out.println("Loaded " + equipmentCount + " Equpiments");
        int loanCount = member.countLines("loan.txt");
        System.out.println("Loaded " + loanCount + " Loans");

        System.out.println("\n");

        boolean condition = true;


        do{
            System.out.println("1. Add member \n2. Add equipment \n3. Loan equipment \n4. List loaned equipment \n5. List overdue equipment \n6. Return equipment \n7. Exit \n");
            System.out.print("Choice: ");
            int choice = input.nextInt();
            input.nextLine(); 

            if(0 <= choice && choice < 8){
                if(choice == 1){
                    
                    
                    System.out.println("Adding a new member");
                    System.out.print("Enter first name: ");
                    String fName = input.nextLine();
                    System.out.print("Enter last name: ");
                    String lName = input.nextLine();
                    System.out.print("Enter email: ");
                    String email = input.nextLine();
                    System.out.print("Enter address: ");
                    String address = input.nextLine();
                    System.out.print("Enter student roll number: ");
                    String studentRoll = input.nextLine();
                    System.out.print("Enter phone number: ");
                    int phoneNo = input.nextInt();
                    input.nextLine(); 

                    System.out.println("\n");

                    member member = new member(fName, lName, email, address, studentRoll, phoneNo);
                    member.saveToFile("members.txt");
                }else if(choice == 2){
                    //syazwy here (Add equipment)
                }else if(choice == 3){
                    // Muin here (Loan Equipment)
                }else if(choice == 4){
                    // (list loaned equipment)
                }else if(choice == 5){

                        System.out.println("List of Overdued Equipment: \n");
                        String filename = "loan.txt"; // Name of your text file
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                            try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
                                String line;

                                while ((line = br.readLine()) != null) {
                                    // Parse the return date from each line
                                    LocalDate returnDate = extractReturnDate(line, formatter);
                                    
                                    if (returnDate != null) {
                                        // Get the current date
                                        LocalDate currentDate = LocalDate.now();

                                        // Check if the return date is before the current date (indicating overdue)
                                        if (returnDate.isBefore(currentDate)) {
                                            System.out.println(line);
                                        } 
                                    } else {
                                        System.out.println("Failed to parse return date from line: " + line);
                                    }
                                }

                            } catch (IOException e) {
                                System.out.println("Error reading the file: " + e.getMessage());
                            }
                            System.out.println("\n");
                }else if(choice == 6){
                    // return equipment 
                }else if(choice == 7){
                
                    // exit function 
                    System.out.println("Exiting...");
                    condition = false;
                }
            }else {
                System.out.println("\nPlease pick a number in between 1 - 7!!!\n");
            }


        }while(condition == true);
           

        int memberCount2 = member.countLines("members.txt");
        System.out.println("Loaded " + memberCount2 + " members");
        int equipmentCount2 = member.countLines("equipment.txt");
        System.out.println("Loaded " + equipmentCount2 + " Equpiments");
        int loanCount2 = member.countLines("loan.txt");
        System.out.println("Loaded " + loanCount2 + " Loans");


       



    }

    // Method to extract the ReturnDate from a line
    private static LocalDate extractReturnDate(String line, DateTimeFormatter formatter) {
        try {
            // Find "ReturnDate: " in the line and extract the date
            int startIndex = line.indexOf("ReturnDate: ") + "ReturnDate: ".length();
            int endIndex = line.indexOf(",", startIndex); // Find the end of the date substring
            if (startIndex >= "ReturnDate: ".length() && endIndex > startIndex) {
                String dateStr = line.substring(startIndex, endIndex).trim();
                return LocalDate.parse(dateStr, formatter); // Parse the date
            }
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format in line: " + line);
        }
        return null;
    }

}





