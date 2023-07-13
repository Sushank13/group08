package Member.DataLayer;

import com.dal.cs.backend.member.DataLayer.MemberDataLayer;
import com.dal.cs.backend.member.Enum.MemberType;
import com.dal.cs.backend.member.MemberObject.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import testUtils.TestUtils;

import java.time.LocalDate;

public class MemberDataLayerTest {
    private MemberDataLayer memberDataLayer;
    @BeforeEach
    public void beforeTestRun() {
        memberDataLayer = new MemberDataLayer() ;
    }

    @Test
    public void createNewMemberTest() {
        String randomEmail = TestUtils.generateRandomEmail();

        Member newMember = new Member(randomEmail, "Jinay", "Shah", MemberType.member, "Program 4", 1, "2345678888", LocalDate.parse("2000-08-15"));
        Assertions.assertTrue(() -> memberDataLayer.createNewMember(newMember));
    }
}
