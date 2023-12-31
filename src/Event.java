public class Event implements Comparable<Event> {
    private Date date; //the event date
    private TimeSlot startTime; //the starting time
    private Location location;
    private Contact contact; //include the department name and email
    private int duration; //in minutes

    @Override
    public int compareTo(Event o) {
        return 0;
    }
}
