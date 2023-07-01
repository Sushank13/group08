package authentication.model;

public class Login {

    private String emailID;
    private String password;

    public Login(String emailID, String password) {
        this.emailID = emailID;
        this.password = password;
    }

    public String getemailID() {
        return emailID;
    }

    public void setemailID(String emailID) {
        this.emailID = emailID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
