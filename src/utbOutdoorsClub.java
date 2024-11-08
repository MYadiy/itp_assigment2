import java.lang.reflect.Member;
import java.util.*;
import java.time.DayOfWeek;
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
                    
                    int memberNo = member.countLines("members.txt") + 1;
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

                    member member = new member(memberNo, fName, lName, email, address, studentRoll, phoneNo);
                    member.saveToFile("members.txt");

                }else if(choice == 2){

                    int equipmentNumber = equipment.countLines("equipment.txt") + 1;
                    
                    System.out.println("Adding new equipment");
                    System.out.print("Enter name of equipment: ");
                    String equipmentName = input.nextLine();
                    System.out.print("Enter description of the equipment: ");
                    String equipmentDescription = input.nextLine();
                    System.out.print("Enter date of purchase: ");
                    String equipmentDateOfPurchase = input.nextLine();
                    System.out.print("Enter purchase cost: ");
                    int equipmentPurchaseCost = input.nextInt();
                    System.out.print("Enter the cost per weekend: ");
                    int equipmentHireCostWeekend = input.nextInt();
                    System.out.print("Enter the cost per week: ");
                    int equipmentHireCostWeek = input.nextInt();
                    System.out.print("Enter the activity: ");
                    String equipmentActivity = input.next();
                    boolean equipmentReturned = false;
                    
                    input.nextLine(); 

                    System.out.println("\n");
                    
                    equipment equipment = new equipment(equipmentName, equipmentDescription, equipmentActivity, equipmentDateOfPurchase, equipmentNumber, equipmentPurchaseCost, equipmentHireCostWeekend, equipmentHireCostWeek, equipmentReturned);
                    equipment.saveToFile("equipment.txt");
                    
                }else if(choice == 3){
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                    System.out.print("Enter loan date (DD/MM/YYYY): ");
                    String loanDate = input.nextLine();
                    LocalDate dateOfLoan = LocalDate.parse(loanDate, dtf);
            
                    System.out.print("Enter expected return date (DD/MM/YYYY): ");
                    String returnDate = input.nextLine();
                    LocalDate dateOfReturn = LocalDate.parse(returnDate, dtf);
            
                    System.out.println("Available activities:");
                    Activity.displayAllActivities();
            
                    System.out.print("Enter activity number: ");
                    int activityNumber = input.nextInt();
                    input.nextLine();
            
                    Activity selectedActivity;
                    try {
                        selectedActivity = Activity.values()[activityNumber - 1];
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Invalid activity number selected.");
                        input.close();
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
                    int equipmentNumber = input.nextInt();
            
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


                    System.out.print("Enter member no: ");
                    int selectedMember = input.nextInt();
            
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
            
            if (gearOfficer != null) {
                Loan loan = new Loan(1, dateOfLoan, dateOfReturn, equipmentNumber, selectedMember, gearOfficer, cost);
                loan.displayLoanDetails();
                loan.saveToFile("loan.txt");
            } else {
                System.out.println("Cannot proceed with loan creation without a valid gear officer.");
            }
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
 public static boolean isWeekend(LocalDate dateOfLoan) {
        DayOfWeek dayOfWeek = dateOfLoan.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }
}





