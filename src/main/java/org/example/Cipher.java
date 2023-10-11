package org.example;

public interface Cipher {
    //strategy pattern/OOP
    String encode(String plainText);
    String decode(String encodedText);
}
