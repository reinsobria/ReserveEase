public class Schedule {
    private int scheduleId;
    private String date;
    private String time;
    private boolean availabilityStatus = true; // true = still open

    public Schedule(int scheduleId, String date, String time) {
        this.scheduleId = scheduleId;
        this.date = date;
        this.time = time;
    }

    public int getScheduleId() { return scheduleId; }
    public String getDate() { return date; }
    public String getTime() { return time; }

    public boolean checkAvailability() { return availabilityStatus; }
    public void updateAvailability(boolean status) { availabilityStatus = status; }

    @Override
    public String toString() {
        return scheduleId + " | " + date + " | " + time + " | "
                + (availabilityStatus ? "AVAILABLE" : "TAKEN");
    }
}
