package authentication.dataLayer;

import Member.DataLayer.MemberDataLayerTest;
import com.dal.cs.backend.authentication.dataLayer.ILoginDataLayer;
import com.dal.cs.backend.authentication.dataLayer.LoginDataLayer;
import com.dal.cs.backend.database.DatabaseConnection;
import com.dal.cs.backend.member.DataLayer.IMemberDataLayer;
import com.dal.cs.backend.member.DataLayer.MemberDataLayer;
import com.dal.cs.backend.member.MemberObject.Member;
import com.dal.cs.backend.member.MemberObject.MemberWithLoginCredential;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import testUtils.RandomGenerator;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LoginDataLayerTest {
    private ILoginDataLayer iLoginDataLayer;
    private IMemberDataLayer iMemberDataLayer;

    private static final Logger logger= LogManager.getLogger(LoginDataLayerTest.class);

    @BeforeAll
    public void testSetup() {
        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
        iMemberDataLayer = MemberDataLayer.getInstance(databaseConnection);
        iLoginDataLayer = LoginDataLayer.getInstance(databaseConnection);
    }

    @Test
    public void setPasswordTest() {
        MemberWithLoginCredential member = RandomGenerator.generateRandomDalClubMemberWithLoginCredential();
        logger.info("[Test][Login][Data] Created test member with login using emailId: "+member.getEmailId());
        iMemberDataLayer.createNewMember(member.getMember());

        Assertions.assertTrue(() -> iLoginDataLayer.createLoginCredential(member.getEmailId(), member.getPassword()));
    }
}
