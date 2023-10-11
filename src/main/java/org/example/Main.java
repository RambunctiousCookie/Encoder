package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        Cipher cipherOne = new Cipher('B',true);
        Cipher cipherTwo = new Cipher('F',true);
        List<Cipher> ciphers = new ArrayList<>();
        ciphers.add(cipherOne);
        ciphers.add(cipherTwo);

        String example = "HELLO WORLD";

        //basic OOP polymorphism
        for (Cipher om : ciphers){
            System.out.println(example);
            example = om.encode(example);
            System.out.println(example);
            example = om.decode(example);
            System.out.println(example+"\n");
        }

        ////For debugging
//        cipherOne.getEncodeMapper().entrySet().stream().sorted(Comparator.comparingInt(entry -> cipherOne.findIdxInReferenceTable(entry.getKey()))).forEach(System.out::println);
//        System.out.println();
//        cipherOne.getDecodeMapper().entrySet().stream().sorted(Comparator.comparingInt(entry -> cipherOne.findIdxInReferenceTable(entry.getKey()))).forEach(System.out::println);
    }
}