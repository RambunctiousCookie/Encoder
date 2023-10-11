package org.example;
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