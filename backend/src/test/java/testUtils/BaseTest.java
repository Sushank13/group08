package testUtils;

import com.dal.cs.backend.Club.DataLayer.ClubDataLayer;
import com.dal.cs.backend.Club.DataLayer.IClubDataLayer;
import com.dal.cs.backend.Club.DataLayer.IClubSecondDataLayer;
import com.dal.cs.backend.Event.DataLayer.EventDataLayer;
import com.dal.cs.backend.Event.DataLayer.IEventDataLayer;

import com.dal.cs.backend.database.DatabaseConnection;
import com.dal.cs.backend.database.IDatabaseConnection;
import com.dal.cs.backend.member.DataLayer.IMemberDataLayer;
import com.dal.cs.backend.member.DataLayer.MemberDataLayer;
import com.dal.cs.backend.member.MemberObject.Member;

import java.util.Stack;
public class BaseTest {
    protected IMemberDataLayer iMemberDataLayer;
    private IClubDataLayer iClubDataLayer;
    private IClubSecondDataLayer iClubSecondDataLayer;
    private IEventDataLayer iEventDataLayer;
    private Stack<CleanUpRow> cleanUpStack;
    public BaseTest() {
        IDatabaseConnection iDatabaseConnection = DatabaseConnection.getInstance();
        iMemberDataLayer = MemberDataLayer.getInstance(iDatabaseConnection);
        iClubDataLayer = ClubDataLayer.getInstance(iDatabaseConnection);
        iClubSecondDataLayer = ClubDataLayer.getInstance(iDatabaseConnection);
        iEventDataLayer = EventDataLayer.getInstance(iDatabaseConnection);
        cleanUpStack = new Stack<>();
    }

    private void addToStack(Class className, String uniqueID) {
        cleanUpStack.push(new CleanUpRow(className, uniqueID));
    }
    public Member createMember() {
        Member member = RandomGenerator.generateRandomDalClubMember();
        addToStack(Member.class, member.getEmailId());
        return member;
    }

    public void cleanUpTest() {
        while(!cleanUpStack.empty()) {
            deleteRow(cleanUpStack.pop());
        }
    }

    private void deleteRow(CleanUpRow cleanUpRow) {
        if (cleanUpRow.getClassName().equals(Member.class)) {
            iMemberDataLayer.deleteMember(cleanUpRow.getUniqueID());
        }
    }
}

class CleanUpRow {
    private Class className;

    private String uniqueID;

    public CleanUpRow(Class className, String uniqueID) {
        this.className = className;
        this.uniqueID = uniqueID;
    }

    public Class getClassName() {
        return className;
    }

    public void setClassName(Class className) {
        this.className = className;
    }

    public String getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(String uniqueID) {
        this.uniqueID = uniqueID;
    }
}