package testUtils;

import com.dal.cs.backend.member.Enum.MemberType;
import com.dal.cs.backend.member.MemberObject.Member;

import java.time.LocalDate;
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
        return LocalDate.of(randomYear,randomMonth, randomDate);
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
        Member  newMember = new Member(email, firstName, lastName, MemberType.member, program, term, mobile, date, password);
        return newMember;
    }
}