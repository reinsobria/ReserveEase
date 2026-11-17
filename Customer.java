import java.util.Scanner;

public class Customer extends User {
    private String contactNumber;

    public Customer(String fullName, String username, String password, String contactNumber) {
        super(fullName, username, password);
        this.contactNumber = contactNumber;
    }

    public String getContactNumber() { return contactNumber; }

    @Override
    public void displayMenu(Scanner sc, ReservationManager manager) {
        int choice;
        do {
            System.out.println("\n=== CUSTOMER MENU ===");
            System.out.println("1. View available schedules");
            System.out.println("2. Make reservation");
            System.out.println("3. View my reservations");
            System.out.println("4. Update reservation");
            System.out.println("5. Cancel reservation");
            System.out.println("0. Logout");
            choice = Main.readInt(sc, "Choose: ");

            if (choice == 1) {
                manager.displaySchedules(true);
            } else if (choice == 2) {
                makeReservation(sc, manager);
            } else if (choice == 3) {
                viewReservations(manager);
            } else if (choice == 4) {
                updateReservation(sc, manager);
            } else if (choice == 5) {
                cancelReservation(sc, manager);
            }
        } while (choice != 0);
        logout();
    }

    public void makeReservation(Scanner sc, ReservationManager manager) {
        manager.displaySchedules(true);
        int scheduleId = Main.readInt(sc, "Schedule ID to book: ");
        Schedule s = manager.findSchedule(scheduleId);

        if (s == null) {
            System.out.println("Schedule not found.");
        } else if (!manager.checkAvailability(s)) {
            System.out.println("Sorry, that schedule is already taken.");
        } else {
            Reservation r = manager.addReservation(getUsername(), s);
            System.out.println("Reservation submitted! Reference No: " + r.getReservationId()
                    + " | Status: " + r.getStatus());
        }
    }

    public void viewReservations(ReservationManager manager) {
        System.out.println("\n--- My Reservations ---");
        manager.displayReservations(getUsername());
    }

    public void updateReservation(Scanner sc, ReservationManager manager) {
        viewReservations(manager);
        int id = Main.readInt(sc, "Reservation ID to update: ");
        Reservation r = manager.findReservation(id);

        if (r == null || !r.getCustomerUsername().equals(getUsername())) {
            System.out.println("Reservation not found.");
            return;
        }
        if (r.getStatus().equals("CANCELLED") || r.getStatus().equals("REJECTED")) {
            System.out.println("This reservation can no longer be changed.");
            return;
        }

        manager.displaySchedules(true);
        int newId = Main.readInt(sc, "New schedule ID: ");
        Schedule newSchedule = manager.findSchedule(newId);

        if (newSchedule == null || !manager.checkAvailability(newSchedule)) {
            System.out.println("That schedule is not available. Nothing changed.");
        } else {
            r.updateDetails(newSchedule);
            System.out.println("Reservation updated. Status is back to PENDING.");
        }
    }

    public void cancelReservation(Scanner sc, ReservationManager manager) {
        viewReservations(manager);
        int id = Main.readInt(sc, "Reservation ID to cancel: ");
        Reservation r = manager.findReservation(id);

        if (r == null || !r.getCustomerUsername().equals(getUsername())) {
            System.out.println("Reservation not found.");
        } else if (r.getStatus().equals("CANCELLED") || r.getStatus().equals("REJECTED")) {
            System.out.println("Already " + r.getStatus() + ".");
        } else {
            r.cancelReservation();
            System.out.println("Reservation cancelled.");
        }
    }
}
