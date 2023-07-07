package testUtils;

import java.util.Random;

public class TestUtils {

    public static String generateRandomEmail() {
        String CHARACTERS = "abcdefghijklmnopqrstuvwxyz1234567890";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        // Generate a random username with 8 characters
        for (int i = 0; i < 8; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }
        sb.append("@dal.ca");

        return sb.toString();
    }

}