public class Reservation {
    private int reservationId;
    private String customerUsername;
    private Schedule schedule;
    private String status; // PENDING, APPROVED, REJECTED, CANCELLED

    public Reservation(int reservationId, String customerUsername, Schedule schedule) {
        this.reservationId = reservationId;
        this.customerUsername = customerUsername;
        this.schedule = schedule;
        this.status = "PENDING";
        schedule.updateAvailability(false); // slot is now taken
    }

    public int getReservationId() { return reservationId; }
    public String getCustomerUsername() { return customerUsername; }
    public Schedule getSchedule() { return schedule; }
    public String getStatus() { return status; }

    // Move to another schedule; the old slot becomes free again
    public void updateDetails(Schedule newSchedule) {
        schedule.updateAvailability(true);
        schedule = newSchedule;
        schedule.updateAvailability(false);
        status = "PENDING";
    }

    // Rejecting or cancelling frees the slot
    public void changeStatus(String newStatus) {
        status = newStatus;
        if (newStatus.equals("REJECTED") || newStatus.equals("CANCELLED")) {
            schedule.updateAvailability(true);
        }
    }

    public void cancelReservation() {
        changeStatus("CANCELLED");
    }

    @Override
    public String toString() {
        return "RES-" + reservationId + " | " + customerUsername + " | "
                + schedule.getDate() + " | " + schedule.getTime() + " | " + status;
    }
}
