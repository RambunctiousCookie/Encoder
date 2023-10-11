package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        Cipher cipher = new Cipher();
//        List<Cipher> ciphers = new ArrayList<>();
//        ciphers.add(cipher);
//        //ciphers.add(cipherTwo);

        String example = "BHELLO WORLD";
        example = cipher.encode(example);
        System.out.println(example);
        example = "B"+example;
        example = cipher.decode(example);
        System.out.println(example);


//        //basic OOP polymorphism
//        for (Cipher om : ciphers){
//            System.out.println(example);
//            example = om.encode(example);
//            System.out.println(example);
//            example = om.decode(example);
//            System.out.println(example+"\n");
//        }

        ////For debugging
//        cipherOne.getEncodeMapper().entrySet().stream().sorted(Comparator.comparingInt(entry -> cipherOne.findIdxInReferenceTable(entry.getKey()))).forEach(System.out::println);
//        System.out.println();
//        cipherOne.getDecodeMapper().entrySet().stream().sorted(Comparator.comparingInt(entry -> cipherOne.findIdxInReferenceTable(entry.getKey()))).forEach(System.out::println);
    }
}