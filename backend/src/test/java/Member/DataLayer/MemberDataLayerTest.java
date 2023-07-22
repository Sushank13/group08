package Member.DataLayer;

import com.dal.cs.backend.database.DatabaseConnection;
import com.dal.cs.backend.database.IDatabaseConnection;
import com.dal.cs.backend.member.DataLayer.IMemberDataLayer;
import com.dal.cs.backend.member.DataLayer.MemberDataLayer;
import com.dal.cs.backend.member.Enum.MemberType;
import com.dal.cs.backend.member.MemberObject.Member;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import testUtils.RandomGenerator;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MemberDataLayerTest {
    private static final Logger logger = LogManager.getLogger(MemberDataLayerTest.class);
    private IMemberDataLayer memberDataLayer;

    private Member randomMember;

    @BeforeAll
    public void beforeALl() {
        IDatabaseConnection databaseConnection = DatabaseConnection.getInstance();
        memberDataLayer = MemberDataLayer.getInstance(databaseConnection);
    }
    @BeforeEach
    public void beforeEach() {
        randomMember = RandomGenerator.generateRandomDalClubMember();
    }

    @AfterEach
    public void cleanUp(TestInfo testInfo) {
        if (testInfo.getTags().contains("SkipCleanUp"))
            return;
        memberDataLayer.deleteMember(randomMember.getEmailId());
    }

    @Test
    public void createNewMemberTest() {
        logger.info("[Test][Member][Service] Created test member with emailId: " + randomMember.getEmailId());
        Assertions.assertTrue(() -> memberDataLayer.createNewMember(randomMember));
    }

    @Test
    public void getMemberTest() {
        logger.info("[Test][Member][Service] Created test member with emailId: " + randomMember.getEmailId());
        memberDataLayer.createNewMember(randomMember);
        Member recievedMember = memberDataLayer.getMember(randomMember.getEmailId());
        Assertions.assertNotNull(recievedMember, "Member not found");

        //Assert member values
        /*Assertions.assertEquals(randomMember.getEmailId(), recievedMember.getEmailId());
        Assertions.assertEquals(randomMember.getFirstName(), recievedMember.getFirstName());
        Assertions.assertEquals(randomMember.getLastName(), recievedMember.getLastName());
        Assertions.assertEquals(randomMember.getMemberType(), recievedMember.getMemberType());
        Assertions.assertEquals(randomMember.getProgram(), recievedMember.getProgram());
        Assertions.assertEquals(randomMember.getTerm(), recievedMember.getTerm());
        Assertions.assertEquals(randomMember.getMobile(), recievedMember.getMobile());
        Assertions.assertEquals(randomMember.getDob(), recievedMember.getDob());*/
    }

    @Test
    @Tag("SkipCleanUp")
    public void deleteMemberTest() {
        logger.info("[Test][Member][Service] Created test member with emailId: " + randomMember.getEmailId());
        memberDataLayer.createNewMember(randomMember);
        Assertions.assertTrue(() -> memberDataLayer.deleteMember(randomMember.getEmailId()));
    }
}
