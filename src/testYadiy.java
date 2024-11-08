import java.lang.reflect.Member;
import java.util.*;

public class testYadiy {
        public static void main(String[] args) {
            Scanner input = new Scanner(System.in); 


        while (true) {
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
        }

        


    }
}
