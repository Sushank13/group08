package Member.ServiceLayer;

import com.dal.cs.backend.authentication.dataLayer.ILoginDataLayer;
import com.dal.cs.backend.authentication.dataLayer.LoginDataLayer;
import com.dal.cs.backend.database.DatabaseConnection;
import com.dal.cs.backend.member.DataLayer.IMemberDataLayer;
import com.dal.cs.backend.member.DataLayer.MemberDataLayer;
import com.dal.cs.backend.member.MemberObject.MemberWithLoginCredential;
import com.dal.cs.backend.member.ServiceLayer.MemberServiceLayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import testUtils.RandomGenerator;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MemberServiceLayerTest {

    private MemberServiceLayer memberServiceLayer;

    private static final Logger logger= LogManager.getLogger(MemberServiceLayerTest.class);

    @BeforeAll
    public void testSetUp() {
        DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
        IMemberDataLayer iMemberDataLayer = MemberDataLayer.getInstance(databaseConnection);
        ILoginDataLayer iLoginDataLayer = LoginDataLayer.getInstance(databaseConnection);
        memberServiceLayer =  new MemberServiceLayer(iMemberDataLayer, iLoginDataLayer);
    }

    @Test
    public void createNewMemberRequestTest() {
        MemberWithLoginCredential newMember = RandomGenerator.generateRandomDalClubMemberWithLoginCredential();
        logger.info("[Test][Member][Service] Created test member with emailId: "+newMember.getEmailId());
        Assertions.assertNotNull(memberServiceLayer.createNewMemberRequest(newMember));
    }
}
