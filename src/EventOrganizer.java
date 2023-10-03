/**
 * @author Vivek Bhadkamkar (@vab85)
 * @author Sajin Saju (@ss3652)
 */

import java.util.Scanner;

public class EventOrganizer
{
    public static Event createEvent(String date, String timeslot, String location, String contact, String email, int duration){
        try{
            Date tempD = new Date(date);

            // System.out.println("Date: " + tempD);

            TimeSlot tempT = null;
            if(timeslot.equals("morning")){
                tempT = TimeSlot.MORNING;
            }
            else if(timeslot.equals("afternoon")){
                tempT = TimeSlot.AFTERNOON;
            }
            else if(timeslot.equals("evening")){
                tempT = TimeSlot.EVENING;
            }
            else{
                throw new NullPointerException("Invalid time slot!");
            }

            // System.out.println("TimeSlot: " + tempT);

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
            else{
                throw new NullPointerException("Invalid location!");
            }

            // System.out.println("Location: " + tempL);

            if(!email.contains("@rutgers.edu")){
                throw new NullPointerException("Invalid contact information!");
            }

            Contact tempC = null;
            if(contact.equals("BAIT")){
                tempC = new Contact(Department.BAIT, email);
            }
            else if(contact.equals("CS")){
                tempC = new Contact(Department.CS, email);
            }
            else if(contact.equals("EE")){
                tempC = new Contact(Department.EE, email);
            }
            else if(contact.equals("ITI")){
                tempC = new Contact(Department.ITI, email);
            }
            else if(contact.equals("MATH")){
                tempC = new Contact(Department.MATH, email);
            }
            else{
                throw new NullPointerException("Invalid contact information!");
            }
            
            // System.out.println("Department: " + tempC.getDepartment() + ", Email: " + tempC.getEmail());
            // System.out.println("Duration: " + duration);

            Event tempE = new Event(tempD,tempT,tempL,tempC,duration);

            // System.out.println(tempE);

            return tempE;
        }
        catch (NullPointerException e) {
            throw e;
        }
    }

    public static void run() {
        /* 
            A 10/21/2023 afternoon hll114 cs cs@rutgers.edu 60
        */
        System.out.println("Event Organizer running...");
        Scanner scan = new Scanner(System.in);
        EventCalendar activeCalendar = new EventCalendar();
        while(true){
            String textCommand = scan.nextLine();
            if(textCommand.equals("Q")) {
                System.out.println("Event Organizer terminated.");
                break;
            }
            String[] commands = textCommand.split("\\s+");
            if(commands.length > 0){
                try{
                    if(commands[0].equals("P")){
                        activeCalendar.print();
                    }
                    else if(commands[0].equals("PE")){
                        activeCalendar.printByDate();
                    }
                    else if(commands[0].equals("PC")){
                        activeCalendar.printByCampus();
                    }
                    else if(commands[0].equals("PD")){
                        activeCalendar.printByDepartment();
                    }
                    // for(int i = 0; i < commands.length; i++){
                    //     System.out.println(commands[i]);
                    // }
                    else if(commands[0].equals("A") && commands.length == 7){
                        Event newEvent = createEvent(commands[1], commands[2].toLowerCase(), commands[3].toUpperCase(), commands[4].toUpperCase(), commands[5], Integer.parseInt(commands[6]));
                        // System.out.println(newEvent);
                        boolean result = activeCalendar.add(newEvent);
                        // System.out.println(activeCalendar.add(newEvent));
                        
                        // activeCalendar.print();
                        System.out.println(result? "Event added to the calendar." : "The event is already on the calendar.");
                    }
                    else if(commands[0].equals("R") && commands.length == 4){
                        Date tempD = new Date(commands[1]);
                        // boolean exist = activeCalendar.eventExist(commands[1], commands[2].toLowerCase(), commands[3].toUpperCase());
                        if(!tempD.isValid()){
                            System.out.println(commands[1] + ": Invalid calendar date!");
                        }
                        // else if (!exist){
                        //     System.out.println(commands[1] + ": Invalid calendar date!");
                        // }
                        else{
                            Event newEvent = createEvent(commands[1], commands[2].toLowerCase(), commands[3].toUpperCase(), "CS", "cs@rutgers.edu", 60);
                            boolean result = activeCalendar.remove(newEvent);
                            System.out.println(result? "Event has been removed from the calendar!" : "Cannot remove; event is not in the calendar!");
                        }
                    }
                    else{
                        System.out.println(textCommand + " is an invalid command!");
                    }
                }
                catch (NullPointerException e){
                    System.out.println(e.getMessage());
                }
            }
            // Implement ` ` is an invalid command!
        }
    }
}
