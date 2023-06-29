package Users;

import java.time.LocalDate;

public class User
{
   private String emailId;
   private String firstName;
   private String lastName;
    private UserType userType;
    private String program;
    private String term;
    private String mobile;
    private LocalDate dob;
    private boolean isLoggedIn;

    public String getEmailId() {
        return emailId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public UserType getUserType() {
        return userType;
    }

    public String getProgram() {
        return program;
    }

    public String getTerm() {
        return term;
    }

    public String getMobile() {
        return mobile;
    }

    public LocalDate getDob() {
        return dob;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }
}
