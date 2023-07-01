package authentication.repository;

import authentication.model.Login;
import database.IDatabaseConnection;

import java.sql.Connection;

public class AuthenticationRepository implements ILoginRepository, IDatabaseConnection {
    @Override
    public void createCredential(Login login) {

    }

    @Override
    public Connection getDatabaseConnection() {
        return null;
    }

    @Override
    public void closeDatabaseConnection() {

    }
}
