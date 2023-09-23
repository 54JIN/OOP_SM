/**
 * @author Vivek Bhadkamkar (vab85)
 */

import java.util.Scanner;

public enum Location
{
    HLL114 ("Hill Center", "Busch"),
    ARC103("Allison Road Classroom", "Busch"),
    BE_AUD("Beck Hall", "Livingston"),
    TIL232("Tillett Hall", "Livingston"),
    AB2225("Academic Building", "College Ave"),
    MU302("Murray Hall", "College Ave");
    
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
        switch(location)
        {
            case HLL114:
                    {
                        System.out.println(location.getBuildingName());
                        break;
                    }
            case ARC103:
                    {
                        System.out.println(location.getBuildingName());
                        break;
                    }
            case BE_AUD:
                    {
                        System.out.println(location.getBuildingName());
                        break;
                    }
            case TIL232:
                {
                    System.out.println(location.getBuildingName());
                    break;
                }
            case AB2225:
                {
                    System.out.println(location.getBuildingName());
                    break;
                }
            case MU302:
                {
                    System.out.println(location.getBuildingName());
                    break;
                }


        }
    }

}
