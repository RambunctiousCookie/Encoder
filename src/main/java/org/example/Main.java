package org.example;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        OffsetManager offsetManager = new OffsetManager();
        //System.out.println(offsetManager.getReferenceTable().length);
        //offsetManager.getMapper().entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(System.out::println);

        offsetManager.setOffset('B');

//        offsetManager.getEncodeMapper().entrySet().stream().sorted(Comparator.comparingInt(entry -> offsetManager.findIdxInReferenceTable(entry.getKey()))).forEach(System.out::println);
//
//        System.out.println();
//
//        offsetManager.getDecodeMapper().entrySet().stream().sorted(Comparator.comparingInt(entry -> offsetManager.findIdxInReferenceTable(entry.getKey()))).forEach(System.out::println);
        //offsetManager.getMapper().entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(System.out::println);


        String exampleOne = "HELLO WORLD";
        System.out.println(exampleOne);
        exampleOne = offsetManager.encode("HELLO WORLD");
        System.out.println(exampleOne);
        exampleOne = offsetManager.decode(exampleOne);
        System.out.println(exampleOne+"\n");

        String exampleTwo = "Hello World";
        System.out.println(exampleTwo);
        exampleTwo = offsetManager.encode("HELLO WORLD");
        System.out.println(exampleTwo);
        exampleTwo = offsetManager.decode(exampleTwo);
        System.out.println(exampleTwo);



    }
}