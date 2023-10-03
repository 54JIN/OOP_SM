/**
 * TimeSlot enum that represents morning, afternoon, and evening
 * @author Vivek Bhadkamkar (@vab85)
 * @author Sajin Saju (@ss3652)
 */
public enum TimeSlot
{
    /**
     * Time Period of morning with hour, minute, and time of day
     */
    MORNING (10, 30, "am"),
    /**
     * Time period of afternoon with hour, minute, and time of day
     */
    AFTERNOON (2, 0, "pm"),
    /**
     * Time period of evening with hour, minute, and time of day
     */
    EVENING (6, 30, "pm");

    /**
     * Hour property of TimeSlot
     */
    public final int hour;
    /**
     * Minute property of TimeSlot
     */
    public final int minute;
    /**
     * AM or PM of TimeSlot
     */
    public final String AP;

    /**
     * Constructor to set properties of TimeSlot
     * @param hour Hour property associated with TimeSlot
     * @param minute Minute property associated with TimeSlot
     * @param AP AM or PM property associated with TimeSlot
     */
    TimeSlot(final int hour, final int minute, final String AP)
    {
        this.hour = hour;
        this.minute = minute;
        this.AP = AP;
    }

    /**
     * Gets hour property of timeslot
     * @return Hour associated with enum TimeSlot
     */
    public int getHour()
    {
        return this.hour;
    }

    /**
     * Gets minute property of timeslot
     * @return Minute associated with enum TimeSlot
     */
    public int getMinute()
    {
        return this.minute;
    }

    /**
     * Gets the AM or PM value of timeSlot
     * @return AM or PM associated with enum TimeSlot
     */
    public String getAP() {
        return this.AP;
    }

}
