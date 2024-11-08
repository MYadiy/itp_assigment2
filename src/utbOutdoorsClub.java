import java.lang.reflect.Member;
import java.util.*;

public class utbOutdoorsClub {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int memberCount = member.countLines("members.txt");
        System.out.println("Loaded " + memberCount + " members");

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
                    // list overdue equipment
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
           

        System.out.println("Loaded " + memberCount + " members");
       



    }
}
