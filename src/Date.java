/**
 * @author Vivek Bhadkamkar (vab85), Sajin Saju (netID)
 */
import java.util.Calendar;
public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    //Define constants to be used for isValid()
    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUARTERCENTENNIAL = 400;
    public static final int[] REGMONTH = {1,3,5,7,8,10,12}; //A month with 31 days is a REGMONTH
    public static final int REGMONTHLENGTH = 31;
    public static final int NUMMONTHSINYEAR = 12;
    public static final int FEBRUARY = 2;
    public static final int CURRYEAR = 2023;



    public Date(int year, int month, int day) //Constructor for testing expected outputs
    {
        this.year = year;
        this.month = month;
        this.day = day;
    }
    public Date(String dateString) //Constructor that parses from date format (Ex. 9/20/2023) to obtain month, day, and year.
    {
        String[] dateTokens = dateString.split("/");
        this.month = Integer.parseInt(dateTokens[0]);
        this.day = Integer.parseInt(dateTokens[1]);
        this.year = Integer.parseInt(dateTokens[2]);
        //System.out.println(this.month + "/" + this.day + "/" + this.year); //Check if it gets the correct values.

    }

  /* Need to check
            1. 31 days
            3. 29+ days in February
            4. 28 days in February
            5. 31+ days
            6. If it's a future date
    */
    /**
     *
     * @return true or false depending on whether the date is valid or not.
     */
    public boolean isValid()
    {
        boolean isLeapYear = isLeapYear(this.year);
        boolean isRegMonth = isRegMonth(this.month);
        Calendar today = Calendar.getInstance();

        if(this.day > REGMONTHLENGTH || this.month > NUMMONTHSINYEAR || this.month <= 0 ||
                this.year < today.get(Calendar.YEAR)) //Check if invalid date at all
        {
            return false;
        }
        else if(this.year == today.get(Calendar.YEAR))
        {
            if(this.month < today.get(Calendar.MONTH))
            {
                return false;
            }
            else if (this.month == today.get(Calendar.MONTH))
            {
                return this.day >= today.get(Calendar.DAY_OF_MONTH);
            }
        }
        else if (this.day == REGMONTHLENGTH) //Check for 31 day months
        {
            return isRegMonth;
        }
        else if(this.month == FEBRUARY) //Check February specifically
        {
            if(this.day > 28)
            {
                return false;
            }
            else if(this.day == 28)
            {
                return isLeapYear;
            }
        }
        else
        {
            return true;
        }
        return true;
    }

    /**
     *
     * @param month Month to be checked if it has 31 days or not
     * @return true if Jan, Mar, May, July, Aug, Oct, or Dec; false otherwise
     */
    private boolean isRegMonth(int month)
    {
        for(int element : REGMONTH)
        {
            if(element == month)
            {
                return true;
            }
        }
        return false;
    }
    /**
     *
     * @param year Enter year to check if this year is a leap year or not.
     * @return true or false depending on whether it's a leap year or not.
     */
    private boolean isLeapYear(int year)
    {
        if(year % QUADRENNIAL == 0)
        {
            if(year % CENTENNIAL == 0)
            {
                return year % QUARTERCENTENNIAL == 0;
            }
            else
            {
                return true;
            }
        }
        else
        {
            return false;
        }
    }
    /**
     * @param date The date to be compared to
     * @return 1 if this.date is later, 0 if they are on the same date, -1 if  this.date is earlier
     */
    @Override
    public int compareTo(Date date) {
        if(this.year > date.year) return 1;
        else if (this.year < date.year) return -1;
        else
        {
            if(this.month > date.month) return 1;
            else if(this.month < date.month) return -1;
            else
            {
                if(this.day > date.day) return 1;
                else if(this.day < date.day) return -1;
                else return 0;


            }
        }



    }
    public static void main (String[] args)
    {
        notCurrDateTest();
        validDateTest();
        invalidDayTest();
        thirtyOneDaysOnInvalidMonth();
        invalidMonthTest();
        falseCalendarInstanceTest();
        trueCalendarInstanceTest();
        leapYearTest();
        notLeapYearTest();
        equalDateTest();
        earlierDateTest();
        laterDateTest();
        laterMonthTest();
    }

    //add javadoc stuff for testcases
    private static void notCurrDateTest()
    {
        Date d = new Date("2/28/2003");
        System.out.println("Expected output: false.");
        System.out.println(d.isValid());
    }
    private static void validDateTest()
    {
        Date d = new Date("10/31/2024");
        System.out.println("Expected output: true.");
        System.out.println(d.isValid());
    }
    private static void invalidDayTest()
    {
            Date d = new Date("10/32/2024");
            System.out.println("Expected output: false.");
            System.out.println(d.isValid());

    }
    private static void thirtyOneDaysOnInvalidMonth()
    {
        Date d = new Date("9/31/2024");
        System.out.println("Expected output: false.");
        System.out.println(d.isValid());
    }
    private static void invalidMonthTest()
    {
        Date d = new Date("13/31/2024");
        System.out.println("Expected output: false.");
        System.out.println(d.isValid());
    }
    private static void falseCalendarInstanceTest()
    {
        Date d = new Date("8/24/2023");
        System.out.println("Expected output: false.");
        System.out.println(d.isValid());
    }
    private static void trueCalendarInstanceTest()
    {
        Date d = new Date("8/26/2023");
        System.out.println("Expected output: true.");
        System.out.println(d.isValid());
    }
    private static void leapYearTest()
    {
        Date d = new Date("2/28/2024");
        System.out.println("Expected output: true.");
        System.out.println(d.isValid());
    }
    private static void notLeapYearTest()
    {
        Date d = new Date("2/28/2025");
        System.out.println("Expected output: false.");
        System.out.println(d.isValid());
    }
    private static void equalDateTest()
    {
        Date d = new Date("2/28/2024");
        Date d2 = new Date("2/28/2024");
        System.out.println("Expected output: 0.");
        System.out.println(d.compareTo(d2));
    }
    private static void earlierDateTest()
    {
        Date d = new Date("2/27/2024");
        Date d2 = new Date("2/28/2024");
        System.out.println("Expected output: -1.");
        System.out.println(d.compareTo(d2));
    }
    private static void laterDateTest()
    {
        Date d = new Date("2/28/2024");
        Date d2 = new Date("2/27/2024");
        System.out.println("Expected output: 1.");
        System.out.println(d.compareTo(d2));
    }
    private static void laterMonthTest()
    {
        Date d = new Date("2/28/2024");
        Date d2 = new Date("3/1/2024");
        System.out.println("Expected output: -1.");
        System.out.println(d.compareTo(d2));
    }


}
