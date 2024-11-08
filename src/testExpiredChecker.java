import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class testExpiredChecker {
    public static void main(String[] args) {
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
                        System.out.println("Loan is overdue. Details: " + line);
                    } 
                } else {
                    System.out.println("Failed to parse return date from line: " + line);
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
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
