package authentication.dataLayer;

import com.dal.cs.backend.authentication.dataLayer.ILoginDataLayer;
import com.dal.cs.backend.authentication.dataLayer.LoginDataLayer;
import com.dal.cs.backend.database.DatabaseConnection;
import com.dal.cs.backend.database.IDatabaseConnection;
import com.dal.cs.backend.member.DataLayer.IMemberDataLayer;
import com.dal.cs.backend.member.DataLayer.MemberDataLayer;
import com.dal.cs.backend.member.MemberObject.MemberWithLoginCredential;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import testUtils.RandomGenerator;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LoginDataLayerTest {
    private static final Logger logger = LogManager.getLogger(LoginDataLayerTest.class);
    private ILoginDataLayer loginDataLayer;
    private IMemberDataLayer memberDataLayer;

    private MemberWithLoginCredential randomMemberWithLoginCredential;

    @BeforeAll
    public void beforeALl() {
        IDatabaseConnection databaseConnection = DatabaseConnection.getInstance();
        memberDataLayer = MemberDataLayer.getInstance(databaseConnection);
        loginDataLayer = LoginDataLayer.getInstance(databaseConnection);
    }
    @BeforeEach
    public void beforeEach() {
        randomMemberWithLoginCredential = RandomGenerator.generateRandomDalClubMemberWithLoginCredential();
    }

    @AfterEach
    public void afterEach() {
        memberDataLayer.deleteMember(randomMemberWithLoginCredential.getEmailId());
    }

    @Test
    public void setPasswordTest() {
        logger.info("[Test][Login][Data] Created test member with login using emailId: " + randomMemberWithLoginCredential.getEmailId());
        memberDataLayer.createNewMember(randomMemberWithLoginCredential.getMember());

//        Assertions.assertTrue(() -> loginDataLayer.createLoginCredential(randomMemberWithLoginCredential.getEmailId(), randomMemberWithLoginCredential.getPassword()));
    }
}
