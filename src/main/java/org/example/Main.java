package org.example;
public class Main {
    public static void main(String[] args) {
        DxcCipher dxcCipher = new DxcCipher();

        String example = "HELLO WORLD";
        example = "B"+example;
        System.out.println(example);
        example = dxcCipher.encode(example);
        System.out.println(example);

        example = dxcCipher.decode(example);
        System.out.println(example);

        example = "F"+example.substring(1);
        example = dxcCipher.encode(example);
        System.out.println(example);

        example = example;
        example = dxcCipher.decode(example);
        System.out.println(example);
    }
}