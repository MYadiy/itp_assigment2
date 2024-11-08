import java.util.List;

public class UTBOutdoorInventoryTest {
    private List<Equipment> equipmentList;

    // Method to mark equipment as returned
    public void returnEquipment(String equipmentName) {
        for (EquipmentTest1 equipment : equipmentList) {
            if (equipment.getEquipmentName().equalsIgnoreCase(equipmentName)) {
                if (!equipment.isEquipmentReturned()) {
                    equipment.returnEquipment();
                    System.out.println("Equipment marked as returned: " + equipment.getEquipmentName());
                    equipment.saveToFile("equipment.txt", false);  // Save the update
                } else {
                    System.out.println("Equipment is already marked as returned.");
                }
                return;
            }
        }
        System.out.println("Equipment not found.");
    }
}
