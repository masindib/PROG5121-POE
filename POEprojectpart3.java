import java.util.*;

// ================= LOGIN CLASS =================
class Login {

    private String storedUsername;
    private String storedPassword;
    private String storedPhone;

    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    public boolean checkPassword(String password) {

        if (password.length() < 8)
            return false;

        boolean hasUpper = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);

            if (Character.isUpperCase(ch))
                hasUpper = true;
            else if (Character.isDigit(ch))
                hasNumber = true;
            else if (!Character.isLetterOrDigit(ch))
                hasSpecial = true;
        }

        return hasUpper && hasNumber && hasSpecial;
    }

    public boolean checkPhone(String phone) {

        if (!phone.startsWith("+27"))
            return false;

        if (phone.length() != 12)
            return false;

        for (int i = 3; i < phone.length(); i++) {
            if (!Character.isDigit(phone.charAt(i)))
                return false;
        }

        return true;
    }

    public void register(Scanner sc) {

        System.out.println("\n=== REGISTER ===");

        System.out.print("Enter username: ");
        String username = sc.nextLine();

        if (!checkUserName(username)) {
            System.out.println("Username is not correctly formatted.");
            return;
        }

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        if (!checkPassword(password)) {
            System.out.println("Password is not correctly formatted.");
            return;
        }

        System.out.print("Enter cell phone (+27...): ");
        String phone = sc.nextLine();

        if (!checkPhone(phone)) {
            System.out.println("Cell phone number incorrectly formatted.");
            return;
        }

        storedUsername = username;
        storedPassword = password;
        storedPhone = phone;

        System.out.println("Registration successful.");
    }

    public boolean login(Scanner sc) {

        if (storedUsername == null) {
            System.out.println("Please register first.");
            return false;
        }

        System.out.println("\n=== LOGIN ===");

        System.out.print("Enter username: ");
        String username = sc.nextLine();

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        if (username.equals(storedUsername)
                && password.equals(storedPassword)) {

            System.out.println("Login successful.");
            return true;
        }

        System.out.println("Username or password incorrect.");
        return false;
    }
}

// ================= MESSAGE SYSTEM =================
class MessageSystem {

    static String[] recipient = new String[5];
    static String[] message = new String[5];
    static String[] flag = new String[5];
    static String[] messageID = new String[5];
    static String[] messageHash = new String[5];

    static List<String> sentMessages = new ArrayList<>();
    static List<String> storedMessages = new ArrayList<>();
    static List<String> disregardedMessages = new ArrayList<>();

    public static String generateID(int index) {
        return "MSG-" + index;
    }

    public static String generateHash(String msg) {
        int sum = 0;

        for (char c : msg.toCharArray()) {
            sum += c;
        }

        return "HASH-" + (sum % 1000);
    }

    public static void loadData() {

        recipient[0] = "+27834557896";
        message[0] = "Did you get the cake?";
        flag[0] = "Sent";

        recipient[1] = "+27838884567";
        message[1] = "Where are you? You are late! I have asked you to be on time.";
        flag[1] = "Stored";

        recipient[2] = "+27834484567";
        message[2] = "Yohoooo, I am at your gate.";
        flag[2] = "Disregard";

        recipient[3] = "0838884567";
        message[3] = "It is dinner time!";
        flag[3] = "Sent";

        recipient[4] = "+27838884567";
        message[4] = "Ok, I am leaving without you.";
        flag[4] = "Stored";

        for (int i = 0; i < message.length; i++) {

            messageID[i] = generateID(i);
            messageHash[i] = generateHash(message[i]);

            if (flag[i].equals("Sent"))
                sentMessages.add(message[i]);

            else if (flag[i].equals("Stored"))
                storedMessages.add(message[i]);

            else
                disregardedMessages.add(message[i]);
        }
    }

    public static void displayMenu() {

        System.out.println("\n===== QUICKCHAT MENU =====");
        System.out.println("1. Display Recipients");
        System.out.println("2. Display Longest Message");
        System.out.println("3. Search by Message ID");
        System.out.println("4. Search by Recipient");
        System.out.println("5. Delete by Hash");
        System.out.println("6. Display Full Report");
        System.out.println("7. Logout");
    }

    public static void runMenu(int choice, Scanner sc) {

        switch (choice) {

            case 1:
                for (String r : recipient) {
                    System.out.println(r);
                }
                break;

            case 2:
                String longest = "";

                for (String msg : message) {
                    if (msg != null && msg.length() > longest.length()) {
                        longest = msg;
                    }
                }

                System.out.println("Longest Message:");
                System.out.println(longest);
                break;

            case 3:

                System.out.print("Enter Message ID: ");
                String id = sc.nextLine();

                for (int i = 0; i < messageID.length; i++) {
                    if (messageID[i].equals(id)) {
                        System.out.println(message[i]);
                    }
                }
                break;

            case 4:

                System.out.print("Enter Recipient: ");
                String rec = sc.nextLine();

                for (int i = 0; i < recipient.length; i++) {
                    if (recipient[i].equals(rec)) {
                        System.out.println(message[i]);
                    }
                }
                break;

            case 5:

                System.out.print("Enter Hash: ");
                String hash = sc.nextLine();

                for (int i = 0; i < messageHash.length; i++) {

                    if (messageHash[i].equals(hash)) {

                        message[i] = null;
                        System.out.println("Message deleted successfully.");
                    }
                }
                break;

            case 6:

                for (int i = 0; i < message.length; i++) {

                    System.out.println("ID: " + messageID[i]);
                    System.out.println("Recipient: " + recipient[i]);
                    System.out.println("Message: " + message[i]);
                    System.out.println("Flag: " + flag[i]);
                    System.out.println("Hash: " + messageHash[i]);
                    System.out.println("--------------------");
                }
                break;
        }
    }
}

// ================= MAIN CLASS =================
public class POEprojectpart3 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Login user = new Login();

        MessageSystem.loadData();

        int choice = 0;

        while (choice != 3) {

            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");

            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {

                case 1:
                    user.register(sc);
                    break;

                case 2:

                    if (user.login(sc)) {

                        int msgChoice = 0;

                        while (msgChoice != 7) {

                            MessageSystem.displayMenu();

                            System.out.print("Choose option: ");
                            msgChoice = Integer.parseInt(sc.nextLine());

                            if (msgChoice >= 1 && msgChoice <= 6) {
                                MessageSystem.runMenu(msgChoice, sc);
                            }
                        }
                    }
                    break;

                case 3:
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }

        sc.close();
    }
}