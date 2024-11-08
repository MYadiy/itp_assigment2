import java.util.Scanner;

public class testEquipment {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        while (true) {
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
            String equipmentActivity = input.nextLine();
            boolean equipmentReturned = false;
            input.nextLine(); 

            System.out.println("\n");

            equipment equipment = new equipment(equipmentName, equipmentDescription, equipmentActivity, equipmentDateOfPurchase, equipmentPurchaseCost, equipmentHireCostWeekend, equipmentHireCostWeek, equipmentReturned);
            equipment.saveToFile("equipment.txt");
        }

        


    }
}
