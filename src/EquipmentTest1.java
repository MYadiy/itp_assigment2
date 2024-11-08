public class EquipmentTest1 {
    private String equipmentName, equipmentDescription, equipmentActivity, equipmentDateOfPurchase;
    private int equipmentNumber, equipmentPurchaseCost, equipmentHireCostWeekend, equipmentHireCostWeek;
    private boolean equipmentReturned;

    // constructor
    public EquipmentTest1(String equipmentName, String equipmentDescription, String equipmentActivity,
                     String equipmentDateOfPurchase, int equipmentNumber, int equipmentPurchaseCost,
                     int equipmentHireCostWeekend, int equipmentHireCostWeek, boolean equipmentReturned) {
        this.equipmentName = equipmentName;
        this.equipmentNumber = equipmentNumber;
        this.equipmentDescription = equipmentDescription;
        this.equipmentActivity = equipmentActivity;
        this.equipmentDateOfPurchase = equipmentDateOfPurchase;
        this.equipmentPurchaseCost = equipmentPurchaseCost;
        this.equipmentHireCostWeekend = equipmentHireCostWeekend;
        this.equipmentHireCostWeek = equipmentHireCostWeek;
        this.equipmentReturned = equipmentReturned;
    }

    // existing getters and setters

    // Method to mark equipment as returned
    public void returnEquipment() {
        this.equipmentReturned = true;
        System.out.println("Equipment " + equipmentName + " has been marked as returned.");
    }

    // Updated display method to show equipment details
    @Override
    public String toString() {
        return "Equipment Number: " + equipmentNumber + ", Name: " + equipmentName +
                ", Description: " + equipmentDescription + ", Activity: " + equipmentActivity +
                ", Date of Purchase: " + equipmentDateOfPurchase + ", Purchase Cost: " +
                equipmentPurchaseCost + ", Hire Cost (Weekend): " + equipmentHireCostWeekend +
                ", Hire Cost (Week): " + equipmentHireCostWeek + ", Returned: " + equipmentReturned;
    }

    // Method to save current equipment state back to the file
    public void saveToFile(String filename) {
        // This could be used for appending or updating the file with current equipment data
        try (FileWriter fileWriter = new FileWriter(filename, true); 
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.println(this.toString());
        } catch (IOException e) {
            System.out.println("An error occurred while writing to file: " + e.getMessage());
        }
    }

    // Other methods remain unchanged
}
