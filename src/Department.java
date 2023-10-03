/**
 * @author Vivek Bhadkamkar (@vab85)
 * @author Sajin Saju (@ss3652)
 */
public enum Department
{
    BAIT ("Business Analytics and Information Technology"),
    CS ("Computer Science"),
    EE ("Electrical Engineering"),
    ITI ("Information Technology and Informatics"),
    MATH ("Mathematics");


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

    public static void main (String[] args)
    {
        Department d = Department.valueOf("CS");
        Department d2 = Department.valueOf("BAIT");
        System.out.println(d.compareTo(d2));

    }
}
