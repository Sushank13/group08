package com.dal.cs.backend.member.DataLayer;

import com.dal.cs.backend.database.DatabaseConnection;
import com.dal.cs.backend.member.MemberObject.Member;

public interface IMemberDataLayer {

    public boolean createNewMember(Member member);

}
