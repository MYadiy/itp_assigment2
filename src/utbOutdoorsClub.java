import java.lang.reflect.Member;
import java.util.*;
import java.time.DayOfWeek;
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
                    input.nextLine(); 
                    System.out.print("Enter the activity: ");
                    String equipmentActivity = input.nextLine();
                    boolean equipmentReturned = false;                    

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
            
                    boolean equipmentFound = false;
                    Activity selectedActivity = null;

                    while (!equipmentFound) {
                        System.out.println("Available activities:");
                        Activity.displayAllActivities(); 

                        System.out.print("Enter activity number: ");
                        int activityNumber = input.nextInt();
                        input.nextLine();  

                        selectedActivity = Activity.values()[activityNumber - 1];

                        // Display the equipment based on the selected activity
                        System.out.println("Equipment no, Equipment name, Description, Cost weekend, Cost per week");

                        try (BufferedReader br = new BufferedReader(new FileReader("equipment.txt"))) {
                            String line;
                            equipmentFound = false; 
                            
                            while ((line = br.readLine()) != null) {
                                if (line.contains("Activity: " + selectedActivity.getDisplayName())) {

                                    String[] parts = line.split(", ");
                                    String equipmentNumber = parts[0].split(": ")[1];
                                    String name = parts[1].split(": ")[1];
                                    String description = parts[2].split(": ")[1];
                                    String hireCostWeekend = parts[6].split(": ")[1];
                                    String hireCostWeek = parts[7].split(": ")[1];

                                    System.out.println(equipmentNumber + ", " + name + ", " + description + ", " + hireCostWeekend + ", " + hireCostWeek);
                                    equipmentFound = true;
                                }
                            }

                            if (!equipmentFound) {
                                System.out.println("No equipment found!");
                            }       
                        } catch (IOException e) {
                            System.out.println("Error reading file: " + e.getMessage());
                        }

                    }
             
                    System.out.print("Enter equipment number: ");
                    int equipmentNumber = input.nextInt();
            
                    // Display members for gear officer selection
                    System.out.println("Person who is hiring:");
                    try (BufferedReader br = new BufferedReader(new FileReader("members.txt"))) {
                        String line;
                        while ((line = br.readLine()) != null) {
                            if (line.contains("Member")) {
            
                                String[] parts = line.split(", ");      
                                String memberNumber = parts[0].split(": ")[1]; 
                                String name = parts[1].split(": ")[1]; 
                    
                                System.out.println(memberNumber + ". " + name);
                            }
                        }
            
                    } catch (IOException e) {
                        System.out.println("Error reading file: " + e.getMessage());
                    }
            
                    int selectedMember = input.nextInt();
                    String gearOfficer = null;
                    int cost = 0;

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
            
                    if (isWeekend(dateOfLoan)) {
                        try (BufferedReader br = new BufferedReader(new FileReader("equipment.txt"))) {
                            String line;
            
                            while ((line = br.readLine()) != null) {
                                if (line.contains("Equipment Number: " + equipmentNumber)) {
                                    int startIndex = line.indexOf("Hire Cost (Weekend): ") + "Hire Cost (Weekend): ".length();
                                    int endIndex = line.indexOf(",", startIndex);
                                    if (endIndex == -1) endIndex = line.length();
            
                                    String costString = line.substring(startIndex, endIndex).trim();
                                    cost = Integer.parseInt(costString);
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
                                    int endIndex = line.indexOf(",", startIndex);
                                    if (endIndex == -1) endIndex = line.length();
            
                                    String costString = line.substring(startIndex, endIndex).trim();
                                    cost = Integer.parseInt(costString);
                                    break;
                                }
                            }
            
                        } catch (IOException e) {
                            System.out.println("Error reading file: " + e.getMessage());
                        }
                    }


          
                    Loan loan = new Loan(dateOfLoan, dateOfReturn, equipmentNumber, selectedMember, gearOfficer, cost);
                    // loan.displayLoanDetails();
                    loan.saveToFile("loan.txt");
                    System.out.println("The cost of hiring the equipment is $" + cost);
                }else if(choice == 4){
                    String equipmentNoReturn = "equipment.txt"; // File name

                    listNotReturnedEquipment(equipmentNoReturn);
                }else if(choice == 5){

                        System.out.println("List of Overdued Equipment: \n");
                        String filename = "loan.txt";
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                            try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
                                String line;

                                while ((line = br.readLine()) != null) {
                                    LocalDate returnDate = extractReturnDate(line, formatter);
                                    
                                    if (returnDate != null) {
                                        // get current date
                                        LocalDate currentDate = LocalDate.now();

                                        // check if overdue
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
                
                    String filename = "equipment.txt";
                    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
                        String line;
                
                        while ((line = br.readLine()) != null) {
                            if (line.contains("Returned: false")) {
                                System.out.println(line);
                            }
                        }
                    } catch (IOException e) {
                        System.out.println("Error reading the equipment file: " + e.getMessage());
                    }

                    System.out.print("Enter the equipment number to return: ");
                    int equipmentNumber = input.nextInt();
                    input.nextLine();

                    returnEquipment(equipmentNumber);

                }else if(choice == 7){
                
                    // exit 
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
                
                
                System.out.println(Arrays.toString(data));  // debugging
                
               
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
                            data[8] = "Returned: true";
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


    // Choice 4 function
    public static void listNotReturnedEquipment(String equipmentNoReturn) {
        try (BufferedReader br = new BufferedReader(new FileReader(equipmentNoReturn))) {
            String line;
            
            System.out.println("\nEquipment not returned:");
            while ((line = br.readLine()) != null) {
                if (isNotReturned(line)) {
                    System.out.println(line);
                }
            }
            System.out.println("\n");

        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }

    // Method to check if equipment is not returned
    private static boolean isNotReturned(String line) {
        // Check if "Returned: false" is present in the line
        return line.contains("Returned: false");
    }

 public static boolean isWeekend(LocalDate dateOfLoan) {
        DayOfWeek dayOfWeek = dateOfLoan.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }
}





