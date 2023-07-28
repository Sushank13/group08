package testUtils;

import com.dal.cs.backend.Club.ClassObject.Category;
import com.dal.cs.backend.Club.ClassObject.Club;
import com.dal.cs.backend.Club.ClassObject.ClubUpdateRequest;
import com.dal.cs.backend.Club.Enum.RequestStatus;
import com.dal.cs.backend.Club.Enum.RequestType;
import com.dal.cs.backend.Event.EventObject.Event;
import com.dal.cs.backend.member.Enum.MemberType;
import com.dal.cs.backend.member.MemberObject.Member;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;

public class RandomGenerator {

    private static String CHARACTERS = "abcdefghijklmnopqrstuvwxyz";
    private static String DIGITS = "1234567890";

    private static StringBuilder generateRandomStringFromCharacters(int length, String characterSet) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characterSet.length());
            char randomChar = characterSet.charAt(randomIndex);
            sb.append(randomChar);
        }
        return sb;
    }

    public static String generateRandomString(int length) {
        String randomCharacters = CHARACTERS + DIGITS;
        return generateRandomStringFromCharacters(length, randomCharacters).toString();
    }

    public static Integer generateRandomInteger(int upperBound) {
        Random random = new Random();
        return random.nextInt(1, upperBound);
    }

    public static Integer generateRandomInteger(int lowerBound, int upperBound) {
        Random random = new Random();
        return random.nextInt(lowerBound, upperBound);
    }

    public static String generateRandomPhoneNumber() {
        return generateRandomStringFromCharacters(10, DIGITS).toString();
    }

    public static String generateRandomEmail() {
        // Generate a random username with 8 characters
        return generateRandomString(8) + "@dal.ca";
    }

    public static String generateRandomProgram(int upperBound) {
        // Generate a random username with 8 characters
        return "Program" + generateRandomInteger(upperBound);
    }

    public static LocalDate generateRandomDate() {
        int randomYear = generateRandomInteger(LocalDate.now().getYear());
        int randomMonth = generateRandomInteger(LocalDate.now().getMonthValue());
        int randomDate = generateRandomInteger(LocalDate.now().getDayOfMonth());
        return LocalDate.of(randomYear, randomMonth, randomDate);
    }

    public static LocalTime generateRandomTime() {
        int randomHour = generateRandomInteger(LocalTime.now().getHour());
        int randomMinute = generateRandomInteger(LocalTime.now().getMinute());
        return LocalTime.of(randomHour, randomMinute);
    }

    public static String generateRandomClubID() {
        return "CLB_" + generateRandomInteger(1000, 10000);
    }

    public static String generateRandomCategoryID() {
        return "CAT_" + generateRandomInteger(1000, 10000);
    }

    public static Category generateRandomCategory() {
        String categoryID = RandomGenerator.generateRandomCategoryID();
        String categoryName = RandomGenerator.generateRandomString(10);
        return new Category(categoryID, categoryName);
    }

    public static String generateRandomEventID() {
        return "EVNT_" + generateRandomInteger(1000, 10000);
    }

    private static String generateRandomFacebookLink() {
        return "https://www.facebook.com/" + generateRandomString(10);
    }

    private static String generateRandomInstagramLink() {
        return "https://www.instagram.com/" + generateRandomString(10);
    }


    public static String generateRandomRequestID() {
        return "REQ_" + generateRandomInteger(1000, 10000);
    }

    public static Member generateRandomDalClubMember() {
        String email = RandomGenerator.generateRandomEmail();
        String firstName = generateRandomString(10);
        String lastName = generateRandomString(10);
        String program = generateRandomProgram(8);
        int term = generateRandomInteger(8);
        String mobile = generateRandomPhoneNumber();
        LocalDate date = generateRandomDate();
        String password = generateRandomString(10);
        Member newMember = new Member(email, firstName, lastName, MemberType.member, program, term, mobile, date, password);
        return newMember;
    }

    public static Member generateRandomAdminMember() {
        Member admin = generateRandomDalClubMember();
        admin.setMemberType(MemberType.admin);
        return admin;
    }

    public static Member generateRandomPresidentMember() {
        Member president = generateRandomDalClubMember();
        president.setMemberType(MemberType.president);
        return president;
    }

    public static Club generateRandomClub(String presidentEmailID, Category category) {
        String clubID = generateRandomClubID();
        String clubName = generateRandomString(10);
        String description = generateRandomString(100);
        String facebookLink = generateRandomFacebookLink();
        String instagramLink = generateRandomInstagramLink();
        String location = generateRandomString(100);
        String meetingTime = generateRandomString(10);
        String rules = generateRandomString(100);
        return new Club(clubID, clubName, description, presidentEmailID, facebookLink, instagramLink, category.getCategoryID(), location, meetingTime, null, rules, category.getCategoryName());
    }

    public static ClubUpdateRequest generateRandomNewClubRequest(Club clubDetails) {
        String requestID = generateRandomRequestID();
        return new ClubUpdateRequest(requestID,
                clubDetails.getClubID(),
                clubDetails.getPresidentEmailID(),
                clubDetails.getCategoryID(),
                clubDetails.getCategoryName(),
                clubDetails.getClubName(),
                clubDetails.getDescription(),
                clubDetails.getFacebookLink(),
                clubDetails.getInstagramLink(),
                clubDetails.getLocation(),
                clubDetails.getMeetingTime(),
                clubDetails.getClubImage(),
                clubDetails.getRules(),
                RequestType.NEW_REQUEST,
                RequestStatus.PENDING);
    }

    public static Event generateRandomEvent(String organiserEmailID, String clubID, String eventID) {
        String eventName = generateRandomString(10);
        String description = generateRandomString(100);
        String venue = generateRandomString(100);
        LocalDate startDate = generateRandomDate();
        LocalDate endDate = generateRandomDate();
        LocalTime startTime = generateRandomTime();
        LocalTime endTime = generateRandomTime();
        String eventTopic = generateRandomString(20);
        return new Event(eventID, clubID, organiserEmailID, eventName, description, venue, null, startDate.toString(), endDate.toString(), startTime.toString(), endTime.toString(), eventTopic);
    }
}