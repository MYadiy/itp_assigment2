import java.lang.reflect.Member;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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

                    System.out.println("List of Equipment that has not been returned yet:");
                
                    // Read the equipment file and display unreturned equipment
                    String filename = "equipment.txt";
                    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
                        String line;
                
                        // Iterate through each line of the equipment file
                        while ((line = br.readLine()) != null) {
                            if (line.contains("Returned: false")) {
                                // Display equipment details that are not marked as returned
                                System.out.println(line);
                            }
                        }
                    } catch (IOException e) {
                        System.out.println("Error reading the equipment file: " + e.getMessage());
                    }
                
                    // Now prompt the user for the equipment number to return
                    System.out.print("Enter the equipment number to return: ");
                    int equipmentNumber = input.nextInt();
                    input.nextLine();  // Consume the newline character left by nextInt()
                
                    // Call the method to mark the equipment as returned
                    returnEquipment(equipmentNumber);

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
 //Bar's code
    private static void returnEquipment(int equipmentNumber) {
        String filename = "equipment.txt";
        List<String> updatedEquipmentList = new ArrayList<>();
        boolean equipmentFound = false;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
               
                if (line.trim().isEmpty()) {
                    continue;
                }
        
             

        
                
                String[] data = line.split(",\\s*");  // Split by comma and any number of spaces
                
                
                System.out.println(Arrays.toString(data));  // For debugging purposes
                
               
                if (data.length == 9) {
                    try {
                        
                        int currentEquipmentNumber = Integer.parseInt(data[0].split(": ")[1].trim());
                        String equipmentName = data[1].split(": ")[1].trim();
                        String equipmentDescription = data[2].split(": ")[1].trim();
                        String equipmentActivity = data[3].split(": ")[1].trim();
                        String equipmentDateOfPurchase = data[4].split(": ")[1].trim();
                        int equipmentPurchaseCost = Integer.parseInt(data[5].split(": ")[1].trim());
                        int equipmentHireCostWeekend = Integer.parseInt(data[6].split(": ")[1].trim());
                        int equipmentHireCostWeek = Integer.parseInt(data[7].split(": ")[1].trim());
                        String returnedStatus = data[8].split(": ")[1].trim();
        
                        
                        if (currentEquipmentNumber == equipmentNumber) {
                            data[8] = "Returned: true";  // Mark as returned
                            equipmentFound = true;
                            System.out.println("Equipment " + equipmentName + " has been marked as returned.");
                        }
        
                        
                        updatedEquipmentList.add(String.join(", ", data));
                    } catch (Exception e) {
                        System.out.println("Error processing data for line: " + line + " - " + e.getMessage());
                    }
                } else {
                    System.out.println("Warning: Skipping improperly formatted line: " + line);
                    updatedEquipmentList.add(line);  
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error processing equipment file: " + e.getMessage());
        }
        
        
        if (equipmentFound) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
                for (String equipmentData : updatedEquipmentList) {
                    writer.println(equipmentData);
                }
                System.out.println("Equipment list updated in " + filename);
            } catch (IOException e) {
                System.out.println("Error updating equipment file: " + e.getMessage());
            }
        } else {
            System.out.println("Equipment number " + equipmentNumber + " not found.");
        }
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





