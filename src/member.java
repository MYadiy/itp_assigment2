import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class member {

    private String fName, lName, email, address, studentRoll;
    private int memberNo, phoneNo;

    public member (int memberNo, String fName, String lName, String email, String address, String studentRoll, int phoneNo){
        this.memberNo = memberNo;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.address = address;
        this.studentRoll = studentRoll;
        this.phoneNo = phoneNo;
    }

    public int getMemberNo() { return memberNo; }
    public String getFName() { return fName; }
    public String getLName() { return lName; }
    public String getEmail() { return email; }
    public String getAddress() { return address; }
    public String getStudentRoll() { return studentRoll; }
    public int getPhoneNo() { return phoneNo; }

    public void setFName(String fName) {this.fName = fName;}
    public void setFLame(String lName) {this.lName = lName;}
    public void setEmail(String email) {this.email = email;}
    public void setAddress(String address) {this.address = address;}
    public void setStudentRoll(String studentRoll) {this.studentRoll = studentRoll;}
    public void setPhoneNo(int phoneNo) {this.phoneNo = phoneNo;}
    public void setMemberNo(int memberNo) {this.memberNo = memberNo;}; 

    public String toString() {
       return "Member Number: " + memberNo + ", Name: " + fName + " " + lName + ", Email: " + email + ", address: " + address + ", Roll Number:  "
       + studentRoll + " Phone: " + phoneNo;  
    }




    public void saveToFile(String filename) {
        try (FileWriter fileWriter = new FileWriter(filename, true); 
            PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.println(this.toString());
        } catch (IOException e) {
            System.out.println("An error occurred while writing to file: " + e.getMessage());
        }
    }


        public static int countLines(String filename) {
            int lineCount = 0;
            try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
                while (br.readLine() != null) {
                    lineCount++;
                }
            } catch (IOException e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
            return lineCount;
        }

}
