import java.util.Scanner;

// Abstract parent class of Customer and Administrator
public abstract class User {
    private String fullName;
    private String username;
    private String password;

    public User(String fullName, String username, String password) {
        this.fullName = fullName;
        this.username = username;
        this.password = password;
    }

    public String getFullName() { return fullName; }
    public String getUsername() { return username; }

    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public void logout() {
        System.out.println("Logged out. Goodbye, " + fullName + "!");
    }

    // Each subclass shows its own menu (polymorphism)
    public abstract void displayMenu(Scanner sc, ReservationManager manager);
}
