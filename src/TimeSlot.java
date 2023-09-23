/**
 * @author Vivek Bhadkamkar (@vab85)
 */
public enum TimeSlot
{
    MORNING (10, 30),
    AFTERNOON (2, 0),
    EVENING (6, 30);

    public final int hour;
    public final int minute;

    /**
     *
     * @param hour Hour property associated with TimeSlot
     * @param minute Minute property associated with TimeSlot
     */
    TimeSlot(final int hour, final int minute)
    {
        this.hour = hour;
        this.minute = minute;
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
}
