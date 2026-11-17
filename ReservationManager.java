import java.util.ArrayList;

// Keeps all users, schedules and reservations in ArrayLists
public class ReservationManager {
    private ArrayList<User> userList = new ArrayList<>();
    private ArrayList<Schedule> scheduleList = new ArrayList<>();
    private ArrayList<Reservation> reservationList = new ArrayList<>();
    private int nextScheduleId = 1;
    private int nextReservationId = 1;

    // ---------- Users ----------
    public boolean usernameExists(String username) {
        for (User u : userList) {
            if (u.getUsername().equalsIgnoreCase(username)) return true;
        }
        return false;
    }

    public void addUser(User user) {
        userList.add(user);
    }

    // Returns the matching user, or null if the login is wrong
    public User login(String username, String password) {
        for (User u : userList) {
            if (u.login(username, password)) return u;
        }
        return null;
    }

    // ---------- Schedules ----------
    public void addSchedule(String date, String time) {
        scheduleList.add(new Schedule(nextScheduleId++, date, time));
    }

    public Schedule findSchedule(int id) {
        for (Schedule s : scheduleList) {
            if (s.getScheduleId() == id) return s;
        }
        return null;
    }

    public boolean checkAvailability(Schedule s) {
        return s.checkAvailability();
    }

    public void displaySchedules(boolean onlyAvailable) {
        System.out.println("ID | Date | Time | Status");
        boolean any = false;
        for (Schedule s : scheduleList) {
            if (!onlyAvailable || s.checkAvailability()) {
                System.out.println(s);
                any = true;
            }
        }
        if (!any) System.out.println("(none)");
    }

    // ---------- Reservations ----------
    public Reservation addReservation(String customerUsername, Schedule s) {
        Reservation r = new Reservation(nextReservationId++, customerUsername, s);
        reservationList.add(r);
        return r;
    }

    public Reservation findReservation(int id) {
        for (Reservation r : reservationList) {
            if (r.getReservationId() == id) return r;
        }
        return null;
    }

    // Pass a username to show one customer's records, or null to show everything
    public void displayReservations(String customerUsername) {
        boolean any = false;
        for (Reservation r : reservationList) {
            if (customerUsername == null || r.getCustomerUsername().equals(customerUsername)) {
                System.out.println(r);
                any = true;
            }
        }
        if (!any) System.out.println("(no records)");
    }
}
