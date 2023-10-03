/**
 * @author Vivek Bhadkamkar (@vab85)
 * @author Sajin Saju (@ss3652)
 */
public enum Location
{
    /**
     * Location #1: Allison Road Classroom
     */
    ARC103("Allison Road Classroom", "Busch"),
    /**
     * Location #2: Academic Building
     */
    AB2225("Academic Building", "College Avenue"),
    /**
     * Location #3: Beck Hall
     */
    BE_AUD("Beck Hall", "Livingston"),
    /**
     * Location #4: Hill Center
     */
    HLL114 ("Hill Center", "Busch"),
    /**
     * Location #5: Murray Hall
     */
    MU302("Murray Hall", "College Avenue"),
    /**
     * Location #6: Tillett Hall
     */
    TIL232("Tillett Hall", "Livingston");

    /**
     * Name of building
     */
    public final String buildingName;
    /**
     * Name of campus
     */
    public final String campus;

    /**
     * Returns building name
     * @return Building name associated with enum location
     */
    public String getBuildingName()
    {
        return buildingName;
    }

    /**
     * Returns campus name
     * @return Campus name associated with enum location
     */
    public String getCampus()
    {
        return campus;
    }

    /**
     * Constructor to assign properties buildingName and campus to Location
     * @param buildingName Building name property associated with location
     * @param campus Campus property associated with location
     */
    Location(final String buildingName, final String campus)
    {
        this.buildingName = buildingName;
        this.campus = campus;
    }


}
