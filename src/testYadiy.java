

public class testYadiy {
        public static void main(String[] args) {
        member member = new member("John", "Doe", "john.doe@example.com", "123 Main St", "2023001", 123456789);
        member.saveToFile("members.txt");
    }
}
