package Email.ServiceLayer;

import com.dal.cs.backend.Email.ClassObject.Email;
import com.dal.cs.backend.Email.ObjectBuilder.EmailBuilder;
import com.dal.cs.backend.Email.ServiceLayer.EmailServiceLayer;
import com.dal.cs.backend.Email.ServiceLayer.IEmailServiceLayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EmailServiceLayerTest
{
    private IEmailServiceLayer iEmailServiceLayer;
    @BeforeEach
    public void beforeTestRun()
    {
        iEmailServiceLayer=new EmailServiceLayer();
    }
    @Test
    public void sendEmailTest()
    {
        Email email=new EmailBuilder().setEmailBody("Test Body")
                .setEmailRecipient("sjinay.two3@gmail.com")
                .setEmailSubject("Test Subject").buildEmail();
        iEmailServiceLayer.sendEmail(email);
    }
}
