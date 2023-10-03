import java.util.Calendar;
/**
 * Creates Date with year, month, and day and provides method isValid() to check if the date is valid
 * @author Vivek Bhadkamkar (@vab85)
 * @author Sajin Saju (@ss3652)
 */
public class Date implements Comparable<Date> {
    /**
     * Year
     */
    private int year;
    /**
     * Month
     */
    private int month;
    /**
     * Day
     */
    private int day;

    //Define constants to be used for isValid()
    /**
     * QUADRENNIAL assignment for isLeapYear
     */
    public static final int QUADRENNIAL = 4;
    /**
     * CENTENNIAL assignment for isLeapYear
     */
    public static final int CENTENNIAL = 100;
    /**
     * QUARTERCENTENNIAL assignment for isLeapYear
     */
    public static final int QUARTERCENTENNIAL = 400;
    /**
     * Define months with 31 days as a REGMONTH
     */
    public static final int[] REGMONTH = {1,3,5,7,8,10,12}; //A month with 31 days is a REGMONTH
    /**
     * Define the length of a REGMONTH as 31 (days)
     */
    public static final int REGMONTHLENGTH = 31;
    /**
     * Define the number of months in a year
     */
    public static final int NUMMONTHSINYEAR = 12;
    /**
     * Define integer value for February
     */
    public static final int FEBRUARY = 2;
    /**
     * Create calendar object to check the current date
     */
    private Calendar today = Calendar.getInstance();

    /**
     * Constructor that creates a date object from year, month, and day
     * @param month Month in Year
     * @param day Day of the month
     * @param year Year
     */
    public Date(int month, int day, int year) //Constructor for testing expected outputs
    {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    /**
     * Constructor that creates a date object from a string
     * @param dateString String for parsing a string input of Date.
     */
    public Date(String dateString) //Constructor that parses from date format (Ex. 9/20/2023) to obtain month, day, and year.
    {
        String[] dateTokens = dateString.split("/");
        this.month = Integer.parseInt(dateTokens[0]);
        this.day = Integer.parseInt(dateTokens[1]);
        this.year = Integer.parseInt(dateTokens[2]);
        //System.out.println(this.month + "/" + this.day + "/" + this.year); //Check if it gets the correct values.

    }

    /**
     * Checks the month 6 months ahead of the current month
     * @param month The current month represented as number from 1-12
     * @return a value indicating whether the month is 6 months ahead or not.
     */
    private int sixMonths(int month){
        return (month+6)%12;
    }
    /**
     * Checks if a given date is valid according to specifications of being in the future, but not more than 6 months in the future.
     * @return true or false depending on whether the date is valid or not.
     */
    public boolean isValid()
    {
        try{
            boolean isLeapYear = isLeapYear(this.year);
            boolean isRegMonth = isRegMonth(this.month);
            if(this.day > REGMONTHLENGTH || this.month > NUMMONTHSINYEAR || this.month <= 0) //Check if invalid date at all
            {throw new NullPointerException(toString() + ": Invalid calendar date!");}
            else if(this.month == FEBRUARY) //Check February specifically
            {
                if(isLeapYear && this.day == 29){return isLeapYear;}
                else if(!isLeapYear && this.day == 29){return false;}
                else if(this.year < today.get(Calendar.YEAR) || (this.year == today.get(Calendar.YEAR) && this.month < ((today.get(Calendar.MONTH) + 1) + 1)) || (this.year == today.get(Calendar.YEAR) && this.month == (today.get(Calendar.MONTH) + 1) && this.day < today.get(Calendar.DAY_OF_MONTH))){
                    throw new NullPointerException(toString() + ": Event date must be a future date!");}
                else if( (this.year == today.get(Calendar.YEAR) && ( (((today.get(Calendar.MONTH) + 1)+6)/12) == 0) && this.month == (today.get(Calendar.MONTH) + 1)+6 && this.day > today.get(Calendar.DAY_OF_MONTH)) || 
                            (this.year == today.get(Calendar.YEAR) && ( (((today.get(Calendar.MONTH) + 1)+6)/12) == 0) && this.month > (today.get(Calendar.MONTH) + 1)+6) ||
                            (this.year > today.get(Calendar.YEAR) && (((today.get(Calendar.MONTH) + 1)+6)/12 >= 1)  && this.month == sixMonths((today.get(Calendar.MONTH) + 1)) && this.day > today.get(Calendar.DAY_OF_MONTH) ) ||
                            (this.year > today.get(Calendar.YEAR) && (((today.get(Calendar.MONTH) + 1)+6)/12 >= 1) ) && this.month > sixMonths((today.get(Calendar.MONTH) + 1)) ){
                                throw new NullPointerException(toString() + ": Event date must be within 6 months!");}
                else if(this.day > 28)
                {return false;}
                else if(this.day == 28)
                {return isLeapYear;}
                else if (this.day < 28)
                {return true;}
            }
            else if (this.day == REGMONTHLENGTH) //Check for 31 day months
            {
                if(isRegMonth == false)
                {throw new NullPointerException(toString() + ": Invalid calendar date!");}
                else
                {return true;}
            }
            else if( this.year < today.get(Calendar.YEAR) || (this.year == today.get(Calendar.YEAR) && this.month < (today.get(Calendar.MONTH) + 1)) || (this.year == today.get(Calendar.YEAR) && this.month == (today.get(Calendar.MONTH) + 1) && this.day < today.get(Calendar.DAY_OF_MONTH))){
                throw new NullPointerException(toString() + ": Event date must be a future date!");}
            else if( (this.year == today.get(Calendar.YEAR) && ( (((today.get(Calendar.MONTH) + 1)+6)/12) == 0) && this.month == (today.get(Calendar.MONTH) + 1)+6 && this.day > today.get(Calendar.DAY_OF_MONTH)) || 
                        (this.year == today.get(Calendar.YEAR) && ( (((today.get(Calendar.MONTH) + 1)+6)/12) == 0) && this.month > (today.get(Calendar.MONTH) + 1)+6) ||
                        (this.year > today.get(Calendar.YEAR) && (((today.get(Calendar.MONTH) + 1)+6)/12 >= 1)  && this.month == sixMonths((today.get(Calendar.MONTH) + 1)) && this.day > today.get(Calendar.DAY_OF_MONTH) ) ||
                        (this.year > today.get(Calendar.YEAR) && (((today.get(Calendar.MONTH) + 1)+6)/12 >= 1) ) && this.month > sixMonths((today.get(Calendar.MONTH) + 1)) ){
                            throw new NullPointerException(toString() + ": Event date must be within 6 months!");}
            else
            {return true;}
            return false;
        }
        catch (NullPointerException e) {throw e;}
    }

    /**
     * Checks if a month has 31 days or not
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
     * Checks if a year is a leap year or not
     * @param year Enter year to check if this year is a leap year or not.
     * @return true or false depending on whether it's a leap year or not.
     */
    private boolean isLeapYear(int year)
    {
        if(year % QUADRENNIAL == 0)
        {
            if(year % CENTENNIAL == 0)
            {
                if(year % QUARTERCENTENNIAL == 0)
                {
                    return true;
                }
                else
                {
                    return false;
                }
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
     * Compares two dates to see which one happens earlier and which one happens later
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
    /**
     * Formats date values into a string
     * @return String form of the date.
     */
    @Override
    public String toString(){return (month + "/" + day + "/" + year);}

    /**
     * Testbed main for Date
     * @param args arguments from console
     */
    public static void main (String[] args)
    {

        notCurrDateTest();
        validDateTest();
        invalidDayTest();
        thirtyOneDaysOnInvalidMonth();
        invalidMonthTest();
        invalidMonthTest2();
        leapYearTest();
        notLeapYearTest();
        /*equalDateTest();
        earlierDateTest();
        laterDateTest();
        laterMonthTest();*/
    }

    /**
     * Tests an invalid date
     */
    private static void notCurrDateTest()
    {
        boolean passed=false;
                try {
                    Date d = new Date("10/1/2023");
                    System.out.println("Expected Output: should print an exception.");
                    d.isValid();
                }
                catch(NullPointerException e){
                    System.out.println(e.getMessage());
                    passed=true;
                }
                if(passed)
                {
                    System.out.println("Test case passed.");
                }
                else {
                    System.out.println("Test case failed");
                }
    }

    /**
     * Tests a valid date
     */
    private static void validDateTest()
    {
        Date d = new Date("10/31/2023");
        System.out.println("Expected output: true.");
        System.out.println(d.isValid());
    }
    private static void invalidDayTest()
    {
        boolean passed=false;
        try {
            Date d = new Date("10/32/2023");
            System.out.println("Expected Output: 10/32/2023: Invalid calendar date!");
            d.isValid();
        }
        catch(NullPointerException e){
            System.out.println(e.getMessage());
            passed=true;
        }
        if(passed)
        {
            System.out.println("Test case passed.");
        }
        else
        {
            System.out.println("Test case failed");
        }
    }

    /**
     * Tests an invalid 31 days on a month with only 30 days
     */
    private static void thirtyOneDaysOnInvalidMonth()
    {
        boolean passed=false;
        try {
            Date d = new Date("11/31/2023");
            System.out.println(d);
            System.out.println("Expected Output: 11/31/2023: Invalid calendar date!");
            d.isValid();
        }
        catch(NullPointerException e)
        {
            System.out.println(e.getMessage());
            passed=true;
        }
        if(passed)
        {
            System.out.println("Test case passed.");
        }
        else
        {
            System.out.println("Test case failed");
        }
    }

    /**
     * Tests an invalid month number > 12
     */
    private static void invalidMonthTest()
    {
        boolean passed=false;
        try {
            Date d = new Date("13/31/2023");
            System.out.println("Expected Output: 13/31/2023: Invalid calendar date!");
            d.isValid();
        }
        catch(NullPointerException e){
            System.out.println(e.getMessage());
            passed=true;
        }
        if(passed)
        {
            System.out.println("Test case passed.");
        }
        else
        {
            System.out.println("Test case failed");
        }
    }

    /**
     * Tests an invalid month number < 1
     */
    private static void invalidMonthTest2()
    {
        boolean passed=false;
        try {
            Date d = new Date("0/31/2023");
            System.out.println("Expected Output: 0/31/2023: Invalid calendar date!");
            d.isValid();
        }
        catch(NullPointerException e){
            System.out.println(e.getMessage());
            passed=true;
        }
        if(passed)
        {
            System.out.println("Test case passed.");
        }
        else
        {
            System.out.println("Test case failed");
        }
    }

    /**
     * Test a valid leap year date
     */
    private static void leapYearTest()
    {
        Date d = new Date("2/28/2024");
        System.out.println("Expected output: true.");
        System.out.println(d.isValid());

    }
    /**
     * Tests an invalid leap year date
     */
    private static void notLeapYearTest()
    {
        boolean passed=false;
        try {
            Date d = new Date("2/28/2023");
            System.out.println("Expected Output: 2/28/2023: Event date must be a future date!");
            d.isValid();
        }
        catch(NullPointerException e){
            System.out.println(e.getMessage());
            passed=true;
        }
        if(passed)
        {
            System.out.println("Test case passed.");
        }
        else
        {
            System.out.println("Test case failed");
        }
    }
}
