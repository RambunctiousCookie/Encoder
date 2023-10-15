package org.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

    @BeforeEach
    public void setupString() {
        offsetCipher = new OffsetCipher();
        //Reset the reference table every time a test is run

        ////Could be cheaper to just reset the table but more dangerous if there are more tests/variables are added down the road
//        offsetCipher.setReferenceTable(new Character[] {
//                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
//                'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
//                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
//                '(', ')', '*', '+', ',', '-', '.', '/'
//        });
    }

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

    @Test
    public void testEncodeDecodeModularity() {
        for (int i = 0; i < testRounds; i++) {
            offsetCipher.setReferenceTable(generateRandomCharArray(validKeyboardChars));

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

    private Character[] generateRandomCharArray(String validKeyboardChars) {
        Random random = new Random();
        int length = random.nextInt(44) + 1;    //random number between 0 (inclusive) and 44 (exclusive)
        List<Character> charList = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            char randomChar;
            do {
                randomChar = validKeyboardChars.charAt(random.nextInt(validKeyboardChars.length()));
            } while (charList.contains(randomChar));

            charList.add(randomChar);
        }

        Collections.shuffle(charList);
        return charList.toArray(new Character[0]);
    }
}
