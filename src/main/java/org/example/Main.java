package org.example;

import java.util.Comparator;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        OffsetManager offsetManager = new OffsetManager();
        //System.out.println(offsetManager.getReferenceTable().length);
        //offsetManager.getMapper().entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(System.out::println);

        offsetManager.updateOffset('B');

//        offsetManager.getEncodeMapper().entrySet().stream().sorted(Comparator.comparingInt(entry -> offsetManager.findIdxInReferenceTable(entry.getKey()))).forEach(System.out::println);
//
//        System.out.println();
//
//        offsetManager.getDecodeMapper().entrySet().stream().sorted(Comparator.comparingInt(entry -> offsetManager.findIdxInReferenceTable(entry.getKey()))).forEach(System.out::println);
        //offsetManager.getMapper().entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(System.out::println);

        String example = offsetManager.encode("HELLO WORLD");
        System.out.println(example);
        String decodedExample = offsetManager.decode(example);
        System.out.println(decodedExample);

    }
}