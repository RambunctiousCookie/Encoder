package org.example;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        DxcCipher dxcCipher = new DxcCipher();

        String example = "HELLO WORLD";
        System.out.println(example);

        example = "B"+example;
        example = dxcCipher.encode(example);
        System.out.println(example);

        example = "B"+example;
        example = dxcCipher.decode(example);
        System.out.println(example);

        example = "F"+example;
        example = dxcCipher.encode(example);
        System.out.println(example);

        example = "F"+example;
        example = dxcCipher.decode(example);
        System.out.println(example);
    }
}