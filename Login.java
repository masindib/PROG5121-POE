import java.util.Scanner;

public class Login {

    private String username;
    private String password;
    private String cellPhone;
    private String firstName;
    private String lastName;

    // Username validation
    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    // Password validation
    public boolean checkPasswordComplexity(String password) {
        boolean hasUpper = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;

        if (password.length() >= 8) {
            for (char c : password.toCharArray()) {
                if (Character.isUpperCase(c)) hasUpper = true;
                if (Character.isDigit(c)) hasNumber = true;
                if (!Character.isLetterOrDigit(c)) hasSpecial = true;
            }
        }

        return hasUpper && hasNumber && hasSpecial;
    }

    // Cell phone validation
    public boolean checkCellPhoneNumber(String number) {
        return number.startsWith("+") && number.length() >= 10 && number.length() <= 15;
    }

    // Register user
    public String registerUser(String username, String password, String number, String firstName, String lastName) {

        if (!checkUserName(username)) {
            return "Username is not correctly formatted.";
        }

        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted.";
        }

        if (!checkCellPhoneNumber(number)) {
            return "Cell number is incorrectly formatted.";
        }

        this.username = username;
        this.password = password;
        this.cellPhone = number;
        this.firstName = firstName;
        this.lastName = lastName;

        return "User successfully registered.";
    }

    // Login check
    public boolean loginUser(String username, String password) {
        return this.username != null &&
               this.username.equals(username.trim()) &&
               this.password.equals(password.trim());
    }

    // Main method
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        Login obj = new Login();

        System.out.println("=== REGISTER ===");

        System.out.print("Enter first name: ");
        String firstName = input.nextLine();

        System.out.print("Enter last name: ");
        String lastName = input.nextLine();

        System.out.print("Enter username: ");
        String username = input.nextLine();

        System.out.print("Enter password: ");
        String password = input.nextLine();

        System.out.print("Enter cell number: ");
        String cell = input.nextLine();

        String registerMessage = obj.registerUser(username, password, cell, firstName, lastName);
        System.out.println(registerMessage);

        // Proceed to login only if registration is successful
        if (registerMessage.equals("User successfully registered.")) {

            System.out.println("\n=== LOGIN ===");

            System.out.print("Enter username: ");
            String loginUser = input.nextLine();

            System.out.print("Enter password: ");
            String loginPass = input.nextLine();

            boolean loginStatus = obj.loginUser(loginUser, loginPass);

            if (loginStatus) {
                System.out.println("Login successful");
                System.out.println("Welcome " + obj.firstName + ", " + obj.lastName + " it is great to see you again.");
            } else {
                System.out.println("Login failed");
                System.out.println("Username or password incorrect, please try again.");
            }
        }

        input.close();
    }
}