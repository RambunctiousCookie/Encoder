package org.example;
public class Main {
    public static void main(String[] args) {
        OffsetCipher offsetCipher = new OffsetCipher();

        String example = "HELLO WORLD";
        example = "B"+example;
        System.out.println(example);
        example = offsetCipher.encode(example);
        System.out.println(example);

        example = offsetCipher.decode(example);
        System.out.println(example);

        example = "F"+example.substring(1);
        example = offsetCipher.encode(example);
        System.out.println(example);

        example = example;
        example = offsetCipher.decode(example);
        System.out.println(example);
    }
}