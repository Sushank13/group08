package member.repository;

import member.enums.MemberType;
import member.model.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class MemberRepositoryTest {
    private MemberRepository memberRepository;

    public MemberRepositoryTest() throws Exception {
        memberRepository = new MemberRepository();
    }
    @Test
    public void createNewMemberTest() {
        Member newMember = new Member("xyz3@dal.ca", "Jinay", "Shah", MemberType.member, "Program 4", 1, "2345678888", LocalDate.parse("2000-08-15"));
        Assertions.assertDoesNotThrow(() -> memberRepository.createNewMember(newMember));
    }
}
