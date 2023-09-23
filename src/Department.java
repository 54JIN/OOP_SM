/**
 * @author Vivek Bhadkamkar (vab85)
 */
public enum Department
{
    CS ("Computer Science"),
    EE ("Electrical Engineering"),
    ITI ("Information Technology and Informatics"),
    MATH ("Mathematics"),
    BAIT ("Business Analytics and Information Technology");

    public final String departmentName;
    /**
     *
     * @param departmentName  Department name property associated with Department
     */
    Department(final String departmentName)
    {
        this.departmentName = departmentName;
    }

    /**
     *
     * @return Full Department name associated with Department
     */
    public String getDepartmentName()
    {
        return this.departmentName;
    }
}
