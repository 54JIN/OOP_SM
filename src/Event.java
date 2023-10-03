/**
 * @author Vivek Bhadkamkar (@vab85)
 * @author Sajin Saju (@ss3652)
 */

public class Event implements Comparable<Event> {
    private Date date; //the event date
    private TimeSlot startTime; //the starting time
    private Location location;
    private Contact contact; //include the department name and email
    private int duration; //in minutes

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

    public Location getLocation(){
        return this.location;
    }
    public Contact getContact() {return this.contact;}


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

    public String toString(){
        // !!! The time has to be accounted for to be AM or PM for start and end time !!!
        return("[Event Date: " + date.toString() + "] [Start: " + startTime.hour + ":" + (startTime.minute < 10? ("0" + startTime.minute) : startTime.minute) + startTime.AP + "] [End: " + endTime() + "] @" + location + " (" + location.buildingName + ", " + location.campus + ") [Contact: " + contact.getDepartment().departmentName + ", " + contact.getEmail() + "]");
    }

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

    public int compareTimeSlot(TimeSlot startTime, TimeSlot otherE){
        boolean currAM = startTime.AP.equals("am");
            boolean otAM = otherE.AP.equals("am");
            if(currAM){
                if(currAM != otAM){
                    return -1;
                }
            }
            if(currAM == otAM){
                int currH = startTime.hour;
                int otH = otherE.hour;
                if(currH < otH){
                    return -1;
                }
                else if(currH == otH){
                    int currM = startTime.minute;
                    int otM = otherE.minute;
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

    /*
        return -1 if earlier
        return 0 if same
        return 1 if later
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

    public int checkE(Date dateE, TimeSlot timeSlotE){
        if(date.compareTo(dateE) == -1){
            return -1;
        }
        else if(date.compareTo(dateE) == 0){
            return compareTimeSlot(startTime, timeSlotE);
        }
        return 1;
    }

    /*
        return true if same
        return false if not
    */
    public boolean equals(Event otherE){
        if(location.getBuildingName().equals(otherE.location.getBuildingName()) && location.getCampus().equals(otherE.location.getCampus())){
            if(compareTimeSlot(startTime, otherE) == 0){
                return true;
            }
        }
        return false;
    }

    public boolean equalE(TimeSlot timeSlotE, Location otherE){
        if(location.getBuildingName().equals(otherE.getBuildingName()) && location.getCampus().equals(otherE.getCampus())){
            if(compareTimeSlot(startTime, timeSlotE) == 0){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args)
    {
        Date tempD = new Date("11/29/2023");
        TimeSlot tempT = TimeSlot.MORNING;
        Location tempL = Location.HLL114;
        Contact tempC = new Contact(Department.BAIT, "bait@rutgers.edu");
        int duration = 65;
        Event temp = new Event(tempD,tempT,tempL,tempC, duration);

        Date tempD2 = new Date("11/29/2023");
        TimeSlot tempT2 = TimeSlot.MORNING;
        Location tempL2 = Location.AB2225;
        Contact tempC2 = new Contact(Department.BAIT, "bait@rutgers.edu");
        int duration2 = 65;
        Event temp2 = new Event(tempD2,tempT2,tempL2,tempC2, duration2);

        System.out.println(temp.toString());
        System.out.println(temp.compareTo(temp2));
        System.out.println(temp.equals(temp2));
    }
}
