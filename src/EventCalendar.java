/**
 * EventCalendar class that holds an array of Events, can add and remove elements and print out the contents of the array, sorted if desired.
 * @author Vivek Bhadkamkar (@vab85)
 * @author Sajin Saju (@ss3652)
 */
public class EventCalendar {
    /**
     * Array to hold a list of events
     */
    private Event [] events; //the array holding the list of events
    /**
     * Current number of events in the array
     */
    private int numEvents; //current number of events in the array
    /**
     * Constant to indicate if event is not found in an array
     */
    public static final int NOT_FOUND = -1;

    /**
     * Searches for the index of an event in events array
     * @param event Event to be found
     * @return Index of event if found, NOT_FOUND if no event found.
     */
    private int find(Event event) { //search an event in the list
        for(int i = 0; i < events.length; i++){
            if(events[i] != null && events[i].compareTo(event) == 0 && events[i].equals(event) == true ){
                return i;
            }
        }
        return NOT_FOUND;
     }

    /**
     * Grows array by 4 slots
     */
    private void grow() { //increase the capacity of the array by 4
        Event[] temp = new Event[events.length + 4];
        for(int i = 0; i < events.length; i++){
            temp[i] = events[i];
        }

        events = temp;
    }

    /**
     * Shrinks array by 4 slots
     */
    private void shrink() {
        Event[] temp = new Event[events.length - 4];
        
        for(int i = 0; i < temp.length; i++){
            temp[i] = events[i];
        }
        events = temp;
    }

    /**
     * Checks last 4 elements of events[] array to see if it's empty or not
     * @return false if there's an element in the last 4 spaces of the array, true if there are no elements in those 4 spaces
     */
    private boolean last4(){
        for(int i = events.length-4; i < events.length; i++){
            if(events[i] != null){
                return false;
            }
        }
        return true;
    }

    /**
     * Refactors the events array
     * @param j int to help with refactoring
     */
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


    /**
     * Adds events to the events[] array
     * @param event the event to be added
     * @return true if event successfully added, false if not
     */
    public boolean add(Event event) { //return true if an event was added onto the array otherwise return false 
        if(events == null){
            Event[] temp = new Event[4]; 
            temp[0] = event;
            events = temp;
            numEvents = events.length;
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
                numEvents = events.length;
                return true;
            }
            else if(events[i] == null){
                // System.out.println("added on");
                events[i] = event;
                // System.out.println("Finished events length: " + events.length);
                numEvents = events.length;
                return true;
            }
            i++;
        }
        // System.out.println("Finished events length: " + events.length);
        return false;
    }

    /**
     * Removes events from the events[] array
     * @param event the event to be removed
     * @return true if event was removed, false if not
     */
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
                numEvents = events.length;
                return true;
            }
        }
        numEvents = events.length;
        return false;
    }

    /**
     * Checks if events[] contains a given event
     * @param event the event that is checked for existence in the array
     * @return true if event was found in array, false if event is not in array
     */
     public boolean contains (Event event) { //return true if an event was found within the array otherwise return false
         for(int i = 0; i < events.length; i++){
             if(events[i] != null && events[i].compareTo(event) == 0 && events[i].equals(event) == true ){
                 return true;
             }
         }
         return false;
     }

    /**
     * Prints the array of events without any sorting
     */
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

    /**
     * Helper method to sort events by date on the calendar
     * @return temporary array with elements sorted by date on the calendar
     */
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


    /**
     * Helper method to sort events by campus building in alphabetical order
     * @return temporary array with elements sorted by campus building in alphabetical order
     */
     private Event[] sortedByCampusBuilding(){
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
    /**
     * Helper method to sort events by department in alphabetical order
     * @return temporary array with elements sorted by department name in alphabetical order
     */
    private Event[] sortedByDepartment()
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
    /**
     * Prints the array of events sorted in chronological order
     */
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

    /**
     * Prints the array of events sorted by campus building in alphabetical order
     */
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

    /**
     * Prints the array of events sorted by department name in alphabetical order
     */
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

}