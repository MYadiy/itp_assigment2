public class member {

    private String fName, lName, email, address, studentRoll;
    private int phoneNo;

    public member (String fName, String lName, String email, String address, String studentRoll, int phoneNo){
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.address = address;
        this.studentRoll = studentRoll;
        this.phoneNo = phoneNo;
    }

    public String getFName() { return fName; }
    public String getLName() { return lName; }
    public String getEmail() { return email; }
    public String getAddress() { return address; }
    public String getStudentRoll() { return studentRoll; }
    public int getPhoneNo() { return phoneNo; }

    


}
