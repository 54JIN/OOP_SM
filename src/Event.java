/**
 * Event method, contains Date, Timeslot, Location, Contact, and duration of the event.
 * @author Vivek Bhadkamkar (@vab85)
 * @author Sajin Saju (@ss3652)
 */

public class Event implements Comparable<Event> {
    /**
     * Event date
     */
    private Date date; //the event date
    /**
     * Starting time
     */
    private TimeSlot startTime; //the starting time
    /**
     * Location
     */
    private Location location;
    /**
     * Department name and email
     */
    private Contact contact; //include the department name and email
    /**
     * Duration of event in minutes
     */
    private int duration; //in minutes

    /**
     * Constructor for Event
     * @param date Event date, cannot be more than 6 months in future
     * @param startTime Starting time of event, morning, evening, or afternoon
     * @param location Location of event at a place in Rutgers
     * @param contact Email address and department associated with event
     * @param duration How long the event will last, between 30 and 120 mins
     */
    public Event(Date date, TimeSlot startTime, Location location, Contact contact, int duration){
        try{
            if(date.isValid()){
                this.date = date;
            }
            else{
                // System.out.println("Date not valid");
                throw new NullPointerException(date.toString() + ": Invalid calendar date!");
            }
            this.startTime = startTime;
            this.location = location;
            this.contact = contact;
            // !!! Needs error check to see if the minimum is at least 30 minutes and at most 120 minutes. !!!
            if(duration > 29 && duration < 121){
                this.duration = duration;
            }
            else{
                throw new NullPointerException("Event duration must be at least 30 minutes and at most 120 minutes");
            }
        }
        catch (NullPointerException e) {
            throw e;
        }
    }

    /**
     * Gets location and returns it
     * @return Location of event
     */
    public Location getLocation(){
        return this.location;
    }

    /**
     * Gets contact and returns it
     * @return Event's contact
     */
    public Contact getContact() {return this.contact;}

    /**
     * Obtains end time of event using duration
     * @return Time which the event ends
     */
    public String endTime(){
        int hour = (duration/60) + ( ( duration%60 + startTime.minute )/60 );

        int minutes = (duration%60 + startTime.minute)%60;
        // !!! Needs to be reformated to be capable of calculating for minutes to be added onto for hour !!!
        if(startTime.AP == "am"){
            return ((startTime.hour + hour)+":"+(minutes < 10? ("0" + minutes) : minutes) + (duration > 89? "pm": "am" ));
        }
        else{
            return ((startTime.hour + hour)+":"+(minutes < 10? ("0" + minutes) : minutes) + startTime.AP );
        }
    }

    /**
     * ToString override to print out the event's details in a formatted output
     * @return String form of the event
     */
    @Override
    public String toString(){
        // !!! The time has to be accounted for to be AM or PM for start and end time !!!
        return("[Event Date: " + date.toString() + "] [Start: " + startTime.hour + ":" + (startTime.minute < 10? ("0" + startTime.minute) : startTime.minute) + startTime.AP + "] [End: " + endTime() + "] @" + location + " (" + location.buildingName + ", " + location.campus + ") [Contact: " + contact.getDepartment().departmentName + ", " + contact.getEmail() + "]");
    }

    /**
     * Compares two timeSlots to aid the compareTo override
     * @param startTime start time of event
     * @param otherE Another event to be compared to
     * @return -1 if earlier, 0 if same time, 1 if later
     */
    public int compareTimeSlot(TimeSlot startTime, Event otherE){
        boolean currAM = startTime.AP.equals("am");
            boolean otAM = otherE.startTime.AP.equals("am");
            if(currAM){
                if(currAM != otAM){
                    return -1;
                }
            }
            if(currAM == otAM){
                int currH = startTime.hour;
                int otH = otherE.startTime.hour;
                if(currH < otH){
                    return -1;
                }
                else if(currH == otH){
                    int currM = startTime.minute;
                    int otM = otherE.startTime.minute;
                    if(currM < otM){
                        return -1;
                    }
                    else if(currM == otM){
                        return 0;
                    }
                }
            }
            return 1;
    }
    /**
     * Overrides compareTo and tells if an Event is earlier or later than another.
     * @param otherE the event to be compared.
     * @return  -1 if earlier, 0 if same time, 1 if later
     */
    @Override
    public int compareTo(Event otherE) {
        //comparing the date, the compared to date is later return -1
        if(date.compareTo(otherE.date) == -1){
            return -1;
        }
        else if(date.compareTo(otherE.date) == 0){
            return compareTimeSlot(startTime, otherE);
        }
        return 1;
    }
    /**
     * Checks if an event is the same as another event.
     * @param otherE the event to be compared
     * @return true if same, false if not.
     */
    public boolean equals(Event otherE){
        if(location.getBuildingName().equals(otherE.location.getBuildingName()) && location.getCampus().equals(otherE.location.getCampus())){
            if(compareTimeSlot(startTime, otherE) == 0){
                return true;
            }
        }
        return false;
    }

    /**
     * Test bed main for Event
     * @param args
     */
   public static void main(String[] args)
    {
        differentLocations();
        differentDates();
        differentTimeSlots();
        differentDateAndTimeSlot();
        differentTimeSlotsAndLocation();
        differentDateAndLocation();
        everythingSame();
    }

    /**
     * Tests if two events with different locations are equal
     */
    private static void differentLocations()
    {
        Date d = new Date("11/21/2023");
        Date d2 = new Date("11/21/2023");
        Event e = new Event(d, TimeSlot.MORNING, Location.TIL232, new Contact(Department.MATH, "math@rutgers.edu"), 120);
        Event e2 = new Event(d, TimeSlot.MORNING, Location.AB2225, new Contact(Department.MATH, "math@rutgers.edu"), 120);
        System.out.println("Expected output: false");
        System.out.println(e.equals(e2));
    }
    /**
     * Tests if two events with different dates are equal
     */
    private static void differentDates()
    {
        Date d = new Date("11/21/2023");
        Date d2 = new Date("11/22/2023");
        Event e = new Event(d, TimeSlot.MORNING, Location.TIL232, new Contact(Department.MATH, "math@rutgers.edu"), 120);
        Event e2 = new Event(d, TimeSlot.MORNING, Location.TIL232, new Contact(Department.MATH, "math@rutgers.edu"), 120);
        System.out.println("Expected output: false");
        System.out.println(e.equals(e2));
    }
    /**
     * Tests if two events with different timeslots are equal
     */
    private static void differentTimeSlots()
    {
        Date d = new Date("11/21/2023");
        Date d2 = new Date("11/21/2023");
        Event e = new Event(d, TimeSlot.MORNING, Location.TIL232, new Contact(Department.MATH, "math@rutgers.edu"), 120);
        Event e2 = new Event(d, TimeSlot.AFTERNOON, Location.TIL232, new Contact(Department.MATH, "math@rutgers.edu"), 120);
        System.out.println("Expected output: false");
        System.out.println(e.equals(e2));
    }
    /**
     * Tests if two events with different timeslots and locations are equal
     */
    private static void differentTimeSlotsAndLocation()
    {
        Date d = new Date("11/21/2023");
        Date d2 = new Date("11/21/2023");
        Event e = new Event(d, TimeSlot.MORNING, Location.TIL232, new Contact(Department.MATH, "math@rutgers.edu"), 120);
        Event e2 = new Event(d, TimeSlot.AFTERNOON, Location.HLL114, new Contact(Department.MATH, "math@rutgers.edu"), 120);
        System.out.println("Expected output: false");
        System.out.println(e.equals(e2));
    }
    /**
     * Tests if two events with different locations and dates are equal
     */
    private static void differentDateAndLocation()
    {
        Date d = new Date("11/21/2023");
        Date d2 = new Date("11/22/2023");
        Event e = new Event(d, TimeSlot.MORNING, Location.TIL232, new Contact(Department.MATH, "math@rutgers.edu"), 120);
        Event e2 = new Event(d, TimeSlot.MORNING, Location.HLL114, new Contact(Department.MATH, "math@rutgers.edu"), 120);
        System.out.println("Expected output: false");
        System.out.println(e.equals(e2));
    }
    /**
     * Tests if two events with different dates and timeslots are equal
     */
    private static void differentDateAndTimeSlot()
    {
        Date d = new Date("11/21/2023");
        Date d2 = new Date("11/22/2023");
        Event e = new Event(d, TimeSlot.MORNING, Location.TIL232, new Contact(Department.MATH, "math@rutgers.edu"), 120);
        Event e2 = new Event(d, TimeSlot.AFTERNOON, Location.TIL232, new Contact(Department.MATH, "math@rutgers.edu"), 120);
        System.out.println("Expected output: false");
        System.out.println(e.equals(e2));
    }
    /**
     * Tests if two identical events are equal
     */
    private static void everythingSame()
    {
        Date d = new Date("11/21/2023");
        Date d2 = new Date("11/21/2023");
        Event e = new Event(d, TimeSlot.MORNING, Location.TIL232, new Contact(Department.MATH, "math@rutgers.edu"), 120);
        Event e2 = new Event(d, TimeSlot.MORNING, Location.TIL232, new Contact(Department.MATH, "math@rutgers.edu"), 120);
        System.out.println("Expected output: true");
        System.out.println(e.equals(e2));
    }
}
