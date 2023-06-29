package Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceLayer
{
    @Autowired
    private IAuthenticationDataLayer iAuthenticationDataLayer;
    boolean isAuthenticated(String email,String password)
    {

        String passWord=iAuthenticationDataLayer.getCredentials(email);
        if(passWord.equals(password))
        {
            return true;
        }
        return false;
    }
}
