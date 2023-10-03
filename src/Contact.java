/**
 * @author Vivek Bhadkamkar (@vab85)
 * @author Sajin Saju (@ss3652)
 */

public class Contact {
    private Department department;
    private String email;

    public Contact(Department department, String email){
        this.department = department;
        // !!! needs error check for email, whether it contains the domain name @rutgers.edu !!!
        this.email = email;
    }

    /**
     *
     * @return Department enum
     */
    public Department getDepartment() {
        return department;
    }

    /**
     *
     * @return Email name
     */
    public String getEmail(){
        return email;
    }

}
