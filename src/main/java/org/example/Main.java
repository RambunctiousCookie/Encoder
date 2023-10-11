package org.example;

import java.util.ArrayList;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        OffsetManager offsetManagerOne = new OffsetManager('B',true);
        OffsetManager offsetManagerTwo = new OffsetManager('F',true);
        List<OffsetManager> offsetManagers = new ArrayList<>();
        offsetManagers.add(offsetManagerOne);
        offsetManagers.add(offsetManagerTwo);

        String exampleOne = "HELLO WORLD";
        String exampleTwo = "Hello World";



//        System.out.println(exampleOne);
//        exampleOne = offsetManagerOne.encode(exampleOne);
//        System.out.println(exampleOne);
//        exampleOne = offsetManagerOne.decode(exampleOne);
//        System.out.println(exampleOne+"\n");
//
//
//        System.out.println(exampleTwo);
//        exampleTwo = offsetManagerOne.encode(exampleTwo);
//        System.out.println(exampleTwo);
//        exampleTwo = offsetManagerOne.decode(exampleTwo);
//        System.out.println(exampleTwo);

        for (OffsetManager om : offsetManagers){
            System.out.println(exampleOne);
            exampleOne = om.encode(exampleOne);
            System.out.println(exampleOne);
            exampleOne = om.decode(exampleOne);
            System.out.println(exampleOne+"\n");

            System.out.println(exampleTwo);
            exampleTwo = om.encode(exampleTwo);
            System.out.println(exampleTwo);
            exampleTwo = om.decode(exampleTwo);
            System.out.println(exampleTwo+"\n");
        }


    }
}