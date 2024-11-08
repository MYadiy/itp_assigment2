import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class testExpiredChecker {
    public static void main(String[] args) {
       
            String filename = "loan.txt";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
            try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
                String dateStr = br.readLine();
                
                // Parse the date from the file
                LocalDate expiryDate = LocalDate.parse(dateStr, formatter);
                
                // Get the current date
                LocalDate currentDate = LocalDate.now();
    
                // Compare dates
                if (expiryDate.isBefore(currentDate)) {
                    System.out.println("The date " + expiryDate + " has expired.");
                } else {
                    System.out.println("The date " + expiryDate + " has not expired.");
                }
    
            } catch (IOException e) {
                System.out.println("Error reading the file: " + e.getMessage());
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format in file. Expected yyyy-MM-dd");
            }
        
    }
}
