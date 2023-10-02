/**
 * @author Vivek Bhadkamkar (@vab85)
 * @author Sajin Saju (@ss3652)
 */

public class Contact {
    private Department department;
    private String email;

    public Contact(Department department, String email){
        this.department = department;
        // !!! needs error check for email, wether it contains the domain name @rutgers.edu !!!
        this.email = email;
    }

    public Department getDepartment() {
        return department;
    }

    public String getEmail(){
        return email;
    }

    public boolean isValid()
    {
        return true;
    }
}
