import java.util.Scanner;

public class Main {

    // Reads a whole number safely (exception handling)
    public static int readInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ReservationManager manager = new ReservationManager();

        // Starting data: one admin account and some sample schedules
        manager.addUser(new Administrator("System Admin", "admin", "admin123", "Super"));
        manager.addSchedule("2026-12-01", "9:00 AM - 11:00 AM");
        manager.addSchedule("2026-12-01", "1:00 PM - 3:00 PM");
        manager.addSchedule("2026-12-02", "9:00 AM - 11:00 AM");
        manager.addSchedule("2026-12-02", "1:00 PM - 3:00 PM");

        System.out.println("=== Welcome to ReserveEase ===");
        int choice;
        do {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("0. Exit");
            choice = readInt(sc, "Choose: ");

            if (choice == 1) {
                System.out.print("Username: ");
                String username = sc.nextLine().trim();
                System.out.print("Password: ");
                String password = sc.nextLine().trim();

                User user = manager.login(username, password);
                if (user == null) {
                    System.out.println("Incorrect username or password.");
                } else {
                    System.out.println("Welcome, " + user.getFullName() + "!");
                    user.displayMenu(sc, manager); // Customer or Administrator menu
                }
            } else if (choice == 2) {
                System.out.print("Full name: ");
                String name = sc.nextLine().trim();
                System.out.print("Username: ");
                String username = sc.nextLine().trim();
                System.out.print("Password (min 6 characters): ");
                String password = sc.nextLine().trim();
                System.out.print("Contact number: ");
                String contact = sc.nextLine().trim();

                if (name.isEmpty() || username.isEmpty() || contact.isEmpty()) {
                    System.out.println("All fields are required.");
                } else if (password.length() < 6) {
                    System.out.println("Password is too short.");
                } else if (manager.usernameExists(username)) {
                    System.out.println("Username already taken.");
                } else {
                    manager.addUser(new Customer(name, username, password, contact));
                    System.out.println("Account created! You can now log in.");
                }
            }
        } while (choice != 0);

        System.out.println("Thank you for using ReserveEase!");
        sc.close();
    }
}
