package authentication.repository;

import authentication.model.Login;

public interface ILoginRepository {
    public void createCredential(Login login);
}
