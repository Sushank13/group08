package authentication.service;

import authentication.model.Login;
import authentication.repository.LoginRepository;

import java.sql.SQLException;

public class AuthenticationService {
    private LoginRepository loginRepository;
    public AuthenticationService(LoginRepository loginRepository){

        this.loginRepository = loginRepository;
    }

    public void createCredential(Login login) throws SQLException {
        loginRepository.createCredential(login);
    }
}
