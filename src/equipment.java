import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class equipment {
    private String equipmentName, equipmentDescription, equipmentActivity, equipmentDateOfPurchase;
    private int equipmentNumber, equipmentPurchaseCost, equipmentHireCostWeekend, equipmentHireCostWeek;
    private boolean equipmentReturned;

    // constructor
    
    public equipment(String equipmentName, String equipmentDescription, String equipmentActivity,
    String equipmentDateOfPurchase, int equipmentNumber, int equipmentPurchaseCost,
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

    // getters

    public String getEquipmentName() {
        return equipmentName;
    }

    public String getEquipmentDescription() {
        return equipmentDescription;
    }

    public String getEquipmentActivity() {
        return equipmentActivity;
    }

    public String getEquipmentDateOfPurchase() {
        return equipmentDateOfPurchase;
    }

    public int getEquipmentNumber() {
        return equipmentNumber;
    }

    public int getEquipmentPurchaseCost() {
        return equipmentPurchaseCost;
    }

    public int getEquipmentHireCostWeekend() {
        return equipmentHireCostWeekend;
    }

    public int getEquipmentHireCostWeek() {
        return equipmentHireCostWeek;
    }

    public boolean isEquipmentReturned() {
        return equipmentReturned;
    }


    // setters

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public void setEquipmentDescription(String equipmentDescription) {
        this.equipmentDescription = equipmentDescription;
    }

    public void setEquipmentActivity(String equipmentActivity) {
        this.equipmentActivity = equipmentActivity;
    }

    public void setEquipmentDateOfPurchase(String equipmentDateOfPurchase) {
        this.equipmentDateOfPurchase = equipmentDateOfPurchase;
    }

    public void setEquipmentNumber(int equipmentNumber) {
        this.equipmentNumber = equipmentNumber;
    }

    public void setEquipmentPurchaseCost(int equipmentPurchaseCost) {
        this.equipmentPurchaseCost = equipmentPurchaseCost;
    }

    public void setEquipmentHireCostWeekend(int equipmentHireCostWeekend) {
        this.equipmentHireCostWeekend = equipmentHireCostWeekend;
    }

    public void setEquipmentHireCostWeek(int equipmentHireCostWeek) {
        this.equipmentHireCostWeek = equipmentHireCostWeek;
    }

    public void setEquipmentReturned(boolean equipmentReturned) {
        this.equipmentReturned = equipmentReturned;
    }

    // display equipment details

    public String toString() {
        return "Name: " + equipmentName + ", Description: " + equipmentDescription + ", Activity: " + equipmentActivity + ", Date of Purchase: " + equipmentDateOfPurchase + ", Purchase Cost: " + equipmentPurchaseCost + ", Hire Cost (Weekend): " + equipmentHireCostWeekend + ", Hire Cost (Week): " + equipmentHireCostWeek + ", Returned: " + equipmentReturned;
    }

    public void saveToFile(String filename) {
        try (FileWriter fileWriter = new FileWriter(filename, true); 
            PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.println(this.toString());
        } catch (IOException e) {
            System.out.println("An error occurred while writing to file: " + e.getMessage());
        }
    }
}
