package Member.DataLayer;

import com.dal.cs.backend.member.MemberObject.Member;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import testUtils.BaseTest;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MemberDataLayerTest extends BaseTest {
    private static final Logger logger = LogManager.getLogger(MemberDataLayerTest.class);

    public MemberDataLayerTest() {
        super();
    }

    @AfterAll
    public void cleanUp(TestInfo testInfo) {
        cleanUpTest();
    }

    @Test
    public void createNewMemberTest() {
        Member randomMember = createMember();
        logger.info("[Test][Member][Service] Created test member with emailId: " + randomMember.getEmailId());
        Assertions.assertTrue(() -> iMemberDataLayer.createNewMember(randomMember));
    }

    @Test
    public void getMemberTest() {
        Member randomMember = createMember();
        logger.info("[Test][Member][Service] Created test member with emailId: " + randomMember.getEmailId());
        iMemberDataLayer.createNewMember(randomMember);
        Member recievedMember = iMemberDataLayer.getMember(randomMember.getEmailId());
        Assertions.assertNotNull(recievedMember, "Member not found");

        //Assert member values
        Assertions.assertEquals(randomMember.getEmailId(), recievedMember.getEmailId());
        Assertions.assertEquals(randomMember.getFirstName(), recievedMember.getFirstName());
        Assertions.assertEquals(randomMember.getLastName(), recievedMember.getLastName());
        Assertions.assertEquals(randomMember.getMemberType(), recievedMember.getMemberType());
        Assertions.assertEquals(randomMember.getProgram(), recievedMember.getProgram());
        Assertions.assertEquals(randomMember.getTerm(), recievedMember.getTerm());
        Assertions.assertEquals(randomMember.getMobile(), recievedMember.getMobile());
        Assertions.assertEquals(randomMember.getDob(), recievedMember.getDob());
    }

    @Test
    public void deleteMemberTest() {
        Member randomMember = createMember();
        logger.info("[Test][Member][Service] Created test member with emailId: " + randomMember.getEmailId());
        iMemberDataLayer.createNewMember(randomMember);
        Assertions.assertTrue(() -> iMemberDataLayer.deleteMember(randomMember.getEmailId()));
    }
}
