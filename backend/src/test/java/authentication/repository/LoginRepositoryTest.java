package authentication.repository;

import authentication.model.Login;
import database.DatabaseConnection;
import member.enums.MemberType;
import member.model.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class LoginRepositoryTest {
    private LoginRepository loginRepository;
    @BeforeEach
    public void setup() throws Exception {
        loginRepository = new LoginRepository();
    }
    @Test

    public void createCredentialTest(){
        Login login = new Login("xyz3@dal.ca","1508");
        Assertions.assertDoesNotThrow(()-> loginRepository.createCredential(login));
    }


}
