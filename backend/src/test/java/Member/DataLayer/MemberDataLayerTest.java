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
}
