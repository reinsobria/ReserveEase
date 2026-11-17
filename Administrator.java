import java.util.Scanner;

public class Administrator extends User {
    private String adminLevel;

    public Administrator(String fullName, String username, String password, String adminLevel) {
        super(fullName, username, password);
        this.adminLevel = adminLevel;
    }

    public String getAdminLevel() { return adminLevel; }

    @Override
    public void displayMenu(Scanner sc, ReservationManager manager) {
        int choice;
        do {
            System.out.println("\n=== ADMIN MENU ===");
            System.out.println("1. View all reservations");
            System.out.println("2. Approve reservation");
            System.out.println("3. Reject reservation");
            System.out.println("4. View all schedules");
            System.out.println("5. Add schedule");
            System.out.println("0. Logout");
            choice = Main.readInt(sc, "Choose: ");

            if (choice == 1) {
                viewAllReservations(manager);
            } else if (choice == 2) {
                changeStatus(sc, manager, "APPROVED");
            } else if (choice == 3) {
                changeStatus(sc, manager, "REJECTED");
            } else if (choice == 4) {
                manager.displaySchedules(false);
            } else if (choice == 5) {
                manageSchedules(sc, manager);
            }
        } while (choice != 0);
        logout();
    }

    public void viewAllReservations(ReservationManager manager) {
        System.out.println("\n--- All Reservations ---");
        manager.displayReservations(null);
    }

    public void changeStatus(Scanner sc, ReservationManager manager, String newStatus) {
        viewAllReservations(manager);
        int id = Main.readInt(sc, "Reservation ID: ");
        Reservation r = manager.findReservation(id);

        if (r == null) {
            System.out.println("Reservation not found.");
        } else if (!r.getStatus().equals("PENDING")) {
            System.out.println("Only PENDING reservations can be changed (this one is "
                    + r.getStatus() + ").");
        } else {
            r.changeStatus(newStatus);
            System.out.println("Reservation " + id + " is now " + newStatus + ".");
        }
    }

    public void manageSchedules(Scanner sc, ReservationManager manager) {
        System.out.print("Date (e.g. 2026-12-01): ");
        String date = sc.nextLine().trim();
        System.out.print("Time (e.g. 9:00 AM - 11:00 AM): ");
        String time = sc.nextLine().trim();

        if (date.isEmpty() || time.isEmpty()) {
            System.out.println("Date and time cannot be empty.");
        } else {
            manager.addSchedule(date, time);
            System.out.println("Schedule added.");
        }
    }
}
