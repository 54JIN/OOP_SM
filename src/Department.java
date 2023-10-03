/**
 * Department enum that represents 1 of 5 departments, organized in alphabetical order
 * @author Vivek Bhadkamkar (@vab85)
 * @author Sajin Saju (@ss3652)
 */
public enum Department
{
    /**
     * Department location #1: Business Analytics and Information Technology
     */
    BAIT ("Business Analytics and Information Technology"),
    /**
     * Department location #2: Computer Science
     */
    CS ("Computer Science"),
    /**
     * Department name #3: Electrical Engineering
     */
    EE ("Electrical Engineering"),
    /**
     * Department name #4: Information Technology and Informatics
     */
    ITI ("Information Technology and Informatics"),
    /**
     * Department name #5: Mathematics
     */
    MATH ("Mathematics");

    /**
     * String property associated with Department name
     */
    public final String departmentName;
    /**
     * Constructor to set property of departnmentName
     * @param departmentName  Department name property associated with Department
     */
    Department(final String departmentName)
    {
        this.departmentName = departmentName;
    }

    /**
     * Gets department name
     * @return Full Department name associated with Department
     */
    public String getDepartmentName()
    {
        return this.departmentName;
    }

}
