/**
 * @author Vivek Bhadkamkar (@vab85)
 * @author Sajin Saju (@ss3652)
 */
public enum TimeSlot
{
    MORNING (10, 30, "am"),
    AFTERNOON (2, 0, "pm"),
    EVENING (6, 30, "pm");

    public final int hour;
    public final int minute;
    public final String AP;

    /**
     *
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
     *
     * @return Hour associated with enum TimeSlot
     */
    public int getHour()
    {
        return this.hour;
    }

    /**
     *
     * @return Minute associated with enum TimeSlot
     */
    public int getMinute()
    {
        return this.minute;
    }

    /**
     *
     * @return AM or PM associated with enum TimeSlot
     */
    public String getAP() {
        return this.AP;
    }

}
