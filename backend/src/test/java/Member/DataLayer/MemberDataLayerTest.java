package Member.DataLayer;

import com.dal.cs.backend.database.DatabaseConnection;
import com.dal.cs.backend.database.IDatabaseConnection;
import com.dal.cs.backend.member.DataLayer.IMemberDataLayer;
import com.dal.cs.backend.member.DataLayer.MemberDataLayer;
import com.dal.cs.backend.member.MemberObject.Member;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import testUtils.RandomGenerator;


public class MemberDataLayerTest {
    private static final Logger logger = LogManager.getLogger(MemberDataLayerTest.class);
    private IMemberDataLayer memberDataLayer;

    @BeforeEach
    public void beforeTestRun() {
        IDatabaseConnection databaseConnection = DatabaseConnection.getInstance();
        memberDataLayer = MemberDataLayer.getInstance(databaseConnection);
    }

    @Test
    public void createNewMemberTest() {
        Member newMember = RandomGenerator.generateRandomDalClubMember();
        logger.info("[Test][Member][Service] Created test member with emailId: " + newMember.getEmailId());
        Assertions.assertTrue(() -> memberDataLayer.createNewMember(newMember));
    }

    @Test
    public void getMemberTest() {
        Member newMember = RandomGenerator.generateRandomDalClubMember();
        logger.info("[Test][Member][Service] Created test member with emailId: " + newMember.getEmailId());
        memberDataLayer.createNewMember(newMember);
        Member recievedMember = memberDataLayer.getMember(newMember.getEmailId());
        Assertions.assertNotNull(recievedMember);

        Assertions.assertEquals(newMember.getEmailId(), recievedMember.getEmailId());
        Assertions.assertEquals(newMember.getFirstName(), recievedMember.getFirstName());
        Assertions.assertEquals(newMember.getLastName(), recievedMember.getLastName());
        Assertions.assertEquals(newMember.getMemberType(), recievedMember.getMemberType());
        Assertions.assertEquals(newMember.getProgram(), recievedMember.getProgram());
        Assertions.assertEquals(newMember.getTerm(), recievedMember.getTerm());
        Assertions.assertEquals(newMember.getMobile(), recievedMember.getMobile());
        Assertions.assertEquals(newMember.getDob(), recievedMember.getDob());
    }
}
