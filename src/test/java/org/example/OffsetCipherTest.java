package org.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OffsetCipherTest {

    private static String validKeyboardChars;
    private static OffsetCipher offsetCipher;
    private static String testString;
    private static String hwString;
    private static int testRounds;

    @BeforeAll
    public static void setup() {
        validKeyboardChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()-_=+[]{};:'\",.<>?/\\|";
        offsetCipher = new OffsetCipher();
        hwString = "HELLO WORLD";

        testRounds = 99;   //TODO: You can set the number of rounds/loops for the robustness test here.
    }

//    @BeforeEach
//    public void setupString() {
//        testString = generateRandomString(15,65);
//    }

    @Test
    public void testEncodeDecodeWithGivenLetterB() {
        testString = "B" + hwString;
        String encodedString = offsetCipher.encode(testString);
        assertEquals("BGDKKN VNQKC", encodedString);
        String decodedString = offsetCipher.decode(encodedString);
        assertEquals("BHELLO WORLD", decodedString);
    }

    @Test
    public void testEncodeDecodeWithGivenLetterF() {
        testString = "F" + hwString;
        String encodedString = offsetCipher.encode(testString);
        assertEquals("FC/GGJ RJMG.", encodedString);
        String decodedString = offsetCipher.decode(encodedString);
        assertEquals("FHELLO WORLD", decodedString);
    }

    @Test
    public void testEncodeDecodeRobustness() {

        for (int i = 0; i < testRounds; i++) {
            testString = generateRandomString(15, 65);
            String encodedString = offsetCipher.encode(testString);
            String decodedString = offsetCipher.decode(encodedString);
            assertEquals(testString, decodedString);
        }
    }

    private String generateRandomString(int minLength, int maxLength) {
        if (minLength > maxLength || minLength < 0) {
            throw new IllegalArgumentException("Invalid input length range");
        }

        Random random = new Random();
        int stringLength = minLength + random.nextInt(maxLength - minLength + 1);
        StringBuilder randomString = new StringBuilder();
        randomString.append(offsetCipher.getReferenceTable()[random.nextInt(offsetCipher.getReferenceTable().length)]);

        int validCharsLength = validKeyboardChars.length();

        for (int i = 1; i < stringLength; i++) {
            int randomIndex = random.nextInt(validCharsLength);
            randomString.append(validKeyboardChars.charAt(randomIndex));
        }

        return randomString.toString();
    }
}
