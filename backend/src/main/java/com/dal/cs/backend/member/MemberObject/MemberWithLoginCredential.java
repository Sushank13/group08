package com.dal.cs.backend.member.MemberObject;

import com.dal.cs.backend.member.Controller.MemberController;
import com.dal.cs.backend.member.Enum.MemberType;

import java.time.LocalDate;

public class MemberWithLoginCredential extends Member {
    private String password;

    public MemberWithLoginCredential(String emailId, String firstName, String lastName, MemberType memberType, String program, int term, String mobile, LocalDate dob, String password) {
        super(emailId, firstName, lastName, memberType, program, term, mobile, dob);
        this.password = password;
    }

    public MemberWithLoginCredential(Member member, String password) {
        super(member.getEmailId(), member.getFirstName(), member.getLastName(), member.getMemberType(), member.getProgram(), member.getTerm(), member.getMobile(), member.getDob());
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Member getMember() {
        return this;
    }
}
