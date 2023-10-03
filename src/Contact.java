/**
 * Contact class that holds Department enum and email associated with that department
 * @author Vivek Bhadkamkar (@vab85)
 * @author Sajin Saju (@ss3652)
 */

public class Contact {
    /**
     * Department name enum
     */
    private Department department;
    /**
     * String representing email address ending with @rutgers.edu
     */
    private String email;

    /**
     * Constructor creating a contact with Department and email
     * @param department department enum
     * @param email email with @rutgers.edu
     */
    public Contact(Department department, String email){
        this.department = department;
        // !!! needs error check for email, whether it contains the domain name @rutgers.edu !!!
        this.email = email;
    }

    /**
     * Gets department enum
     * @return Department enum
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * Gets email address
     * @return Email name
     */
    public String getEmail(){
        return email;
    }

}
