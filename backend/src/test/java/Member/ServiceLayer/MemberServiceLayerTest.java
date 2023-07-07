package Member.ServiceLayer;

import com.dal.cs.backend.member.Enum.MemberType;
import com.dal.cs.backend.member.MemberObject.Member;
import com.dal.cs.backend.member.ServiceLayer.MemberServiceLayer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import testUtils.TestUtils;

import java.time.LocalDate;

public class MemberServiceLayerTest {

    private MemberServiceLayer memberServiceLayer;

    public MemberServiceLayerTest() {
        memberServiceLayer =  new MemberServiceLayer();
    }
    @Test
    public void createNewMemberRequestTest() {
        String randomEmail = TestUtils.generateRandomEmail();
        Member newMember = new Member(randomEmail, "Jinay", "Shah", MemberType.member, "Program 4", 1, "2345678888", LocalDate.parse("2000-08-15"));

        Assertions.assertNotNull(memberServiceLayer.createNewMemberRequest(newMember));
    }
}
