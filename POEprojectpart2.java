import java.util.Scanner;

class Login {

    private String storedUsername;
    private String storedPassword;
    private String storedPhone;

    // Username validation
    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    // Password validation (NO regex)
    public boolean checkPassword(String password) {

        if (password.length() < 8) return false;

        boolean hasUpper = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);

            if (Character.isUpperCase(ch)) {
                hasUpper = true;
            } else if (Character.isDigit(ch)) {
                hasNumber = true;
            } else if (!Character.isLetterOrDigit(ch)) {
                hasSpecial = true;
            }
        }

        return hasUpper && hasNumber && hasSpecial;
    }

    // Cell phone validation (NO regex)
    public boolean checkPhone(String phone) {

        if (!phone.startsWith("+27")) return false;
        if (phone.length() != 12) return false;

        for (int i = 3; i < phone.length(); i++) {
            if (!Character.isDigit(phone.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    // Register method (FIXED: proper flow + stored data protection)
    public void register(Scanner sc) {

        System.out.println("=== REGISTER ===");

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

    // Login method (FIXED: checks registration first)
    public void login(Scanner sc) {

        System.out.println("=== LOGIN ===");

        if (storedUsername == null || storedPassword == null) {
            System.out.println("No user registered. Please register first.");
            return;
        }

        System.out.print("Enter username: ");
        String username = sc.nextLine();

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        if (username.equals(storedUsername) && password.equals(storedPassword)) {
            System.out.println("Login successful.");
        } else {
            System.out.println("Username or password incorrect, please try again.");
        }
    }
}

public class POEprojectpart2 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Login user = new Login();

        int choice = 0;

        while (choice != 3) {

            System.out.println("\n=== MENU ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");

            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                sc.nextLine(); // clear buffer
            } else {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine();
                continue;
            }

            if (choice == 1) {
                user.register(sc);

            } else if (choice == 2) {
                user.login(sc);

            } else if (choice == 3) {
                System.out.println("Goodbye!");

            } else {
                System.out.println("Invalid choice.");
            }
        }

        sc.close();
    }
}
