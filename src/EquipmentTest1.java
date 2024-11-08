import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class EquipmentTest1 {
    private String equipmentName, equipmentDescription, equipmentActivity, equipmentDateOfPurchase;
    private int equipmentPurchaseCost, equipmentHireCostWeekend, equipmentHireCostWeek;
    private boolean equipmentReturned;

    // Constructor
    public EquipmentTest1(String equipmentName, String equipmentDescription, String equipmentActivity,
                     String equipmentDateOfPurchase, int equipmentPurchaseCost,
                     int equipmentHireCostWeekend, int equipmentHireCostWeek, boolean equipmentReturned) {
        this.equipmentName = equipmentName;
        this.equipmentDescription = equipmentDescription;
        this.equipmentActivity = equipmentActivity;
        this.equipmentDateOfPurchase = equipmentDateOfPurchase;
        this.equipmentPurchaseCost = equipmentPurchaseCost;
        this.equipmentHireCostWeekend = equipmentHireCostWeekend;
        this.equipmentHireCostWeek = equipmentHireCostWeek;
        this.equipmentReturned = equipmentReturned;
    }

    // Getters and Setters omitted for brevity

    // Mark equipment as returned
    public void returnEquipment() {
        this.equipmentReturned = true;
    }

    // Display equipment details
    @Override
    public String toString() {
        return "Name: " + equipmentName + " | Description: " + equipmentDescription + " | Activity: " + equipmentActivity + 
               " | Date of Purchase: " + equipmentDateOfPurchase + " | Purchase Cost: " + equipmentPurchaseCost + 
               " | Hire Cost (Weekend): " + equipmentHireCostWeekend + " | Hire Cost (Week): " + equipmentHireCostWeek + 
               " | Returned: " + equipmentReturned;
    }

    // Save equipment data to file (used for updating the equipment status)
    public void saveToFile(String filename, boolean append) {
        try (FileWriter fileWriter = new FileWriter(filename, append);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.println(this.toString());
        } catch (IOException e) {
            System.out.println("An error occurred while writing to file: " + e.getMessage());
        }
    }
}
