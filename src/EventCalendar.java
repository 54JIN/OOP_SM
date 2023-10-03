/**
 * @author Vivek Bhadkamkar (@vab85)
 * @author Sajin Saju (@ss3652)
 */


public class EventCalendar {

    private Event [] events; //the array holding the list of events
    private int numEvents; //current number of events in the array
    public static final int NOT_FOUND = -1;

    private int find(Event event) { //search an event in the list
        for(int i = 0; i < events.length; i++){
            if(events[i] != null && events[i].compareTo(event) == 0 && events[i].equals(event) == true ){
                return i;
            }
        }
        return NOT_FOUND;
     }

    private void grow() { //increase the capacity of the array by 4
        Event[] temp = new Event[events.length + 4];
        // System.out.println("Grwoing: ");

        for(int i = 0; i < events.length; i++){
            temp[i] = events[i];
        }
        // int i = 0;
        // while (i < events.length){
        //     temp[i] = events[i];
        //     System.out.println(i + ": " + events.toString());
        //     i++;
        // }
        // while(i < temp.length){
        //     System.out.println(temp[i]);
        //     i++;
        // }
        events = temp;
    }

    private void shrink() {
        Event[] temp = new Event[events.length - 4];
        
        for(int i = 0; i < temp.length; i++){
            temp[i] = events[i];
        }
        events = temp;
    }

    private boolean last4(){
        for(int i = events.length-4; i < events.length; i++){
            if(events[i] != null){
                return false;
            }
        }
        return true;
    }

    private void refactor(int j){
        Event[] temp = new Event[events.length];
        int eventsI = 0;
        for(int i = 0; i < events.length; i++){
            if(i == j){
                eventsI++;
                if(eventsI < events.length){
                    temp[i] = events[eventsI];
                }
                else{
                    temp[i] = null;
                }
            }
            else{
                if(eventsI < events.length){
                    temp[i] = events[eventsI];
                }
                else{
                    temp[i] = null;
                }
            }
            eventsI++;
        }
        events = temp;
    }



    public boolean add(Event event) { //return true if an event was added onto the array otherwise return false 
        if(events == null){
            Event[] temp = new Event[4]; 
            temp[0] = event;
            events = temp;
            return true;
        }
        if(contains(event)){
            return false;
        }
        int i = 0;
        // System.out.println("Starting events length: " + events.length);
        while(i < events.length){
            if(i == events.length-1 && events[i] != null){
                // System.out.println("made it grow");
                grow();
                events[i+1] = event;
                // System.out.println("Finished events length: " + events.length);
                return true;
            }
            else if(events[i] == null){
                // System.out.println("added on");
                events[i] = event;
                // System.out.println("Finished events length: " + events.length);
                return true;
            }
            i++;
        }
        // System.out.println("Finished events length: " + events.length);
        return false;
    }

    public boolean remove (Event event) { //return true if an event was removed from the array otherwise return false
        if(events == null){
            return false;
        }
        for(int i = 0; i < events.length; i++){
            if(events[i] != null && events[i].compareTo(event) == 0 && events[i].equals(event)){
                refactor(i);
                if(last4()){
                    shrink();
                }
                return true;
            }
        }
        return false;
    }

     public boolean contains (Event event) { //return true if an event was found within the array otherwise return false
         for(int i = 0; i < events.length; i++){
             if(events[i] != null && events[i].compareTo(event) == 0 && events[i].equals(event) == true ){
                 return true;
             }
         }
         return false;
     }

    public void print() { //print the array as is
        if(events == null){
            System.out.println("Event calendar is empty!");    
        }
        else{
            System.out.println("* Event calendar *");
            // System.out.println(events.length);
            for(int i = 0; i < events.length; i++) {
                if(events[i] != null){
                    System.out.println(events[i].toString());
                }
            }
            System.out.println("* end of event calendar *");
        }
    }
    public Event[] sortedByDateTime(){
        Event[] temp = new Event[events.length];
        for(int i = 0; i < events.length; i++){
            if(events[i] != null){
                temp[i] = events[i];
            }
        }
        for(int i = 0; i < temp.length; i++){
            for(int j = i+1; j < temp.length; j++){
                Event tempE = null;
                if(temp[i] != null && temp[j] != null && temp[i].compareTo(temp[j]) == 1){
                    tempE = temp[i];
                    temp[i] = temp[j];
                    temp[j] = tempE;
                }
            }
        }
        return temp;
    }



     public Event[] sortedByCampusBuilding(){
         Event[] temp = new Event[events.length];
         for(int i = 0; i < events.length; i++){
             if(events[i] != null){
                 temp[i] = events[i];
             }
         }
         for (int i = 0; i < temp.length; i++) {
             for (int j = i + 1; j < temp.length; j++) {
                 Event tempE = null;
                 if (temp[i] != null && temp[j] != null && temp[i].getLocation().compareTo(temp[j].getLocation()) > 0)
                 {
                     tempE = temp[i];
                     temp[i] = temp[j];
                     temp[j] = tempE;
                 }
             }
         }
         return temp;
     }

    public void printByDate()
     { //ordered by date and timeslot
        if(events == null)
        {
            System.out.println("Event calendar is empty!");    
        }
        else{
            System.out.println("* Event calendar by event date and start time *");
            // System.out.println(events.length);
            Event[] temp = sortedByDateTime();
            for(int i = 0; i < temp.length; i++) {
                if(temp[i] != null){
                    System.out.println(temp[i].toString());
                }
            }
            System.out.println("* end of event calendar *");
        }
    }

    public void printByCampus() { //ordered by campus and building/room
        if(events == null){
            System.out.println("Event calendar is empty!");
         }
         else{
             System.out.println("* Event calendar by campus and building *");
             // System.out.println(events.length);
             Event[] temp = sortedByCampusBuilding();
             for(int i = 0; i < temp.length; i++) {
                 if(temp[i] != null){
                     System.out.println(temp[i].toString());
                 }
             }
             System.out.println("* end of event calendar *");
         }
    }

    public void printByDepartment()
     {
         if(events == null)
         {
             System.out.println("Event calendar is empty!");
         }
         else{
             System.out.println("* Event calendar by department *");
             // System.out.println(events.length);
             Event[] temp = sortedByDepartment();
             for(int i = 0; i < temp.length; i++) {
                 if(temp[i] != null){
                     System.out.println(temp[i].toString());
                 }
             }
             System.out.println("* end of event calendar *");
         }
     }

    public Event[] sortedByDepartment()
    {
        Event[] temp = new Event[events.length];
        for(int i = 0; i < events.length; i++){
            if(events[i] != null){
                temp[i] = events[i];
            }
        }
        for (int i = 0; i < temp.length; i++) {
            for (int j = i + 1; j < temp.length; j++) {
                Event tempE = null;
                if (temp[i] != null && temp[j] != null && temp[i].getContact().getDepartment().compareTo(temp[j].getContact().getDepartment()) > 0)
                {
                    tempE = temp[i];
                    temp[i] = temp[j];
                    temp[j] = tempE;
                }
            }
        }


        return temp;
    }

    // !!! temp method - NEEDS TO BE DELETED !!!
    public static Event createEvent(String date, String timeslot, String location, String contact, int duration){
        Date tempD = new Date(date);

        TimeSlot tempT = null;
        if(timeslot.equals("M")){
            tempT = TimeSlot.MORNING;
        }
        else if(timeslot.equals("A")){
            tempT = TimeSlot.AFTERNOON;
        }
        else if(timeslot.equals("E")){
            tempT = TimeSlot.EVENING;
        }
        
        Location tempL = null;
        if(location.equals("AB2225")){
            tempL = Location.AB2225;
        }
        else if(location.equals("ARC103")){
            tempL = Location.ARC103;
        }
        else if(location.equals("BE_AUD")){
            tempL = Location.BE_AUD;
        }
        else if(location.equals("HLL114")){
            tempL = Location.HLL114;
        }
        else if(location.equals("MU302")){
            tempL = Location.MU302;
        }
        else if(location.equals("TIL232")){
            tempL = Location.TIL232;
        }

        Contact tempC = null;
        if(contact.equals("BAIT")){
            tempC = new Contact(Department.BAIT, "bait@rutgers.edu");
        }
        else if(contact.equals("CS")){
            tempC = new Contact(Department.CS, "cs@rutgers.edu");
        }
        else if(contact.equals("EE")){
            tempC = new Contact(Department.EE, "ee@rutgers.edu");
        }
        else if(contact.equals("ITI")){
            tempC = new Contact(Department.ITI, "iti@rutgers.edu");
        }
        else if(contact.equals("MATH")){
            tempC = new Contact(Department.MATH, "math@rutgers.edu");
        }

        Event tempE = new Event(tempD,tempT,tempL,tempC, duration);
        return tempE;
    }
    public static void main(String[] args)
    {
        Date tempD = new Date("11/29/2023");
        TimeSlot tempT = TimeSlot.MORNING;
        Location tempL = Location.HLL114;
        Contact tempC = new Contact(Department.BAIT, "bait@rutgers.edu");
        int duration = 65;
        Event tempE = new Event(tempD,tempT,tempL,tempC, duration);

        Date tempD1 = new Date("10/09/2023");
        TimeSlot tempT1 = TimeSlot.AFTERNOON;
        Location tempL1 = Location.ARC103;
        Contact tempC1 = new Contact(Department.CS, "cs@rutgers.edu");
        int duration1 = 85;
        Event tempE1 = new Event(tempD1,tempT1,tempL1,tempC1, duration1);

        Event tempE2 = createEvent("10/07/2023", "M", "HLL114", "MATH", 65);
        Event tempE3 = createEvent("12/23/2023", "A", "MU302", "ITI", 65);
        Event tempE4 = createEvent("11/09/2023", "E", "HLL114", "BAIT", 65);

        EventCalendar tempEC = new EventCalendar();

        System.out.println(tempEC.add(tempE) + "\n");
        System.out.println(tempEC.add(tempE1) + "\n");
        System.out.println(tempEC.add(tempE2) + "\n");
        System.out.println(tempEC.add(tempE3) + "\n");
        System.out.println(tempEC.add(tempE4) + "\n");

        //tempEC.print();

        //tempEC.remove(tempE2);

        //tempEC.print();

        tempEC.printByCampus();

        tempEC.printByDepartment();

        tempEC.printByDate();

    }
}