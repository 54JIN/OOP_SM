/**
 * @author Vivek Bhadkamkar (@vab85)
 * @author Sajin Saju (@ss3652)
 */

import java.util.Scanner;

public enum Location
{
    ARC103("Allison Road Classroom", "Busch"),
    AB2225("Academic Building", "College Avenue"),
    BE_AUD("Beck Hall", "Livingston"),
    HLL114 ("Hill Center", "Busch"),
    MU302("Murray Hall", "College Avenue"),
    TIL232("Tillett Hall", "Livingston");


    
    public final String buildingName;
    public final String campus;

    /**
     * @return Building name associated with enum location
     */
    public String getBuildingName()
    {
        return buildingName;
    }

    /**
     * @return Campus name associated with enum location
     */
    public String getCampus()
    {
        return campus;
    }

    /**
     *
     * @param buildingName Building name property associated with location
     * @param campus Campus property associated with location
     */
    Location(final String buildingName, final String campus)
    {
        this.buildingName = buildingName;
        this.campus = campus;
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a location: \n");
        String locString = sc.nextLine();
        Location location = Location.valueOf(locString);
        String locString2 = sc.nextLine();
        Location l2 = Location.valueOf(locString2);
        System.out.println(location.compareTo(l2));
    }

}
