package com.dal.cs.backend.member.MemberObject;

import com.dal.cs.backend.member.Enum.MemberType;

import java.time.LocalDate;

public class Member {
    private String emailId;
    private String firstName;
    private String lastName;
    private MemberType memberType;
    private String program;
    private int term;
    private String mobile;
    private LocalDate dob;

    public Member(String emailId, String firstName, String lastName, MemberType memberType, String program, int term, String mobile, LocalDate dob) {
        this.emailId = emailId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.memberType = memberType;
        this.program = program;
        this.term = term;
        this.mobile = mobile;
        this.dob = dob;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public MemberType getMemberType() {
        return memberType;
    }

    public void setMemberType(MemberType memberType) {
        this.memberType = memberType;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
}
