package testUtils;

import com.dal.cs.backend.Club.ClassObject.Category;
import com.dal.cs.backend.Club.ClassObject.Club;
import com.dal.cs.backend.Club.DataLayer.ClubDataLayer;
import com.dal.cs.backend.Club.DataLayer.IClubDataLayer;
import com.dal.cs.backend.Club.DataLayer.IClubSecondDataLayer;
import com.dal.cs.backend.Event.DataLayer.EventDataLayer;
import com.dal.cs.backend.Event.DataLayer.IEventDataLayer;
import com.dal.cs.backend.database.DatabaseConnection;
import com.dal.cs.backend.database.IDatabaseConnection;
import com.dal.cs.backend.member.DataLayer.IMemberDataLayer;
import com.dal.cs.backend.member.DataLayer.MemberDataLayer;
import com.dal.cs.backend.member.Enum.MemberType;
import com.dal.cs.backend.member.MemberObject.Member;

import java.sql.SQLException;
import java.util.Random;
import java.util.Stack;

public class BaseTest {
    protected IMemberDataLayer iMemberDataLayer;
    protected IClubDataLayer iClubDataLayer;
    protected IClubSecondDataLayer iClubSecondDataLayer;
    protected IEventDataLayer iEventDataLayer;
    private Stack<CleanUpRow> cleanUpStack;

    public BaseTest() {
        IDatabaseConnection iDatabaseConnection = DatabaseConnection.getInstance();
        iMemberDataLayer = MemberDataLayer.getInstance(iDatabaseConnection);
        iClubDataLayer = ClubDataLayer.getInstance(iDatabaseConnection);
        iClubSecondDataLayer = ClubDataLayer.getInstance(iDatabaseConnection);
        iEventDataLayer = EventDataLayer.getInstance(iDatabaseConnection);
        cleanUpStack = new Stack<>();
    }

    public void addToStack(Class className, String uniqueID) {
        cleanUpStack.push(new CleanUpRow(className, uniqueID));
    }

    public void popCleanUpStack() {
        cleanUpStack.pop();
    }

    public Member createMember(boolean createInDatabase, MemberType memberType) {
        Member member;
        switch (memberType) {
            case admin -> member = RandomGenerator.generateRandomAdminMember();
            case president -> member = RandomGenerator.generateRandomPresidentMember();
            default -> member = RandomGenerator.generateRandomDalClubMember();
        }

        if (createInDatabase) {
            iMemberDataLayer.createNewMember(member);
            addToStack(Member.class, member.getEmailId());
        }
        return member;
    }

    public Category createCategory(boolean createInDatabase) {
        Category category = RandomGenerator.generateRandomCategory();
        if (createInDatabase) {
            try {
                iClubDataLayer.createClubCategory(category);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            addToStack(Category.class, category.getCategoryID());
        }
        return category;
    }

    public Club createClub(boolean createInDatabase, String presidentEmailID, Category category) {
        Club club = RandomGenerator.generateRandomClub(presidentEmailID, category);
        if (createInDatabase) {
            try {
                iClubDataLayer.createClub(club);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            addToStack(Club.class, club.getClubID());
        }
        return club;
    }

    public void cleanUpTest() {
        while (!cleanUpStack.empty()) {
            deleteRow(cleanUpStack.pop());
        }
    }

    private void deleteRow(CleanUpRow cleanUpRow) {
        Class className = cleanUpRow.getClassName();
        String uniqueID = cleanUpRow.getUniqueID();
        if (className.equals(Member.class)) {
            iMemberDataLayer.deleteMember(uniqueID);
        } else if (className.equals(Category.class)) {
            try {
                iClubDataLayer.deleteClubCategory(uniqueID);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else if (className.equals(Club.class)) {
            try {
                iClubDataLayer.deleteClub(uniqueID);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
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