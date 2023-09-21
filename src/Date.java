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
    public static final int FEBRUARY = 2;


    public Date(int year, int month, int day) //Constructor for testing
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

    } //METHOD NEEDS ERROR HANDLING TO BE IMPLEMENTED FOR BAD DATES. OR WE CAN IMPLEMENT IT IN EVENT CLASS.


    /**
     *
     * @return true or false depending on whether the date is valid or not.
     */
    public boolean isValid()
    {

        boolean isLeapYear = isLeapYear(this.year);
        boolean isRegMonth = isRegMonth(this.month);
        /* Need to check
            1. 31 days
            2. 30 days in February
            3. 29 days in February
            4. 28 days in February
            5. >31 days
        */
        if(this.day > REGMONTHLENGTH) //Check if invalid date at all
        {
            return false;
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
                if(isLeapYear)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
        else
        {
            return true;
        }
        return false;
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
            if(element == this.month)
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
    @Override
    public int compareTo(Date date) {
        return 0;
    }
    public static void main (String[] args)
    {
      Date d = new Date(2000, 2, 28);
      Date d2 = new Date(2001, 2, 28);
      Date d3 = new Date(2016, 2, 28);
      Date d4 = new Date(2016, 5, 31);
      Date d5 = new Date(2016, 4, 31);
      Date d6 = new Date("9/31/2023");



        System.out.println(d.isValid());
        System.out.println(d2.isValid());
        System.out.println(d3.isValid());
        System.out.println(d4.isValid());
        System.out.println(d5.isValid());
        System.out.println(d6.isValid());





    }

}
