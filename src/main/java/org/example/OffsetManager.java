package org.example;

import java.util.HashMap;
import java.util.Map;

public class OffsetManager {
    //TODO: account for both uppercase and lowercase?
    //  char for to inline for quick instantiation?
    //
    private char offset;
    private int offsetIdx;
    private Character[] referenceTable;
    private Map<Character,Character> mapper;
    public char getOffset() {
        return offset;
    }
    public Character[] getReferenceTable() {
        return referenceTable;
    }

    public Map<Character, Character> getMapper() {
        //TODO: remove this method
        return mapper;
    }

    public OffsetManager() {
        referenceTable = new Character[] {
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                '(', ')', '*', '+', ',', '-', '.', '/'
        };
        this.mapper = new HashMap<>();
    }

    public void updateOffset(char newOffset){
        if (!offsetValidator(newOffset))
            throw new IllegalArgumentException("Input character array is not valid given the reference table.");
        else{
            this.offset = newOffset;
            this.offsetIdx = findIdxInReferenceTable(newOffset);

            //TODO: move to decode/encode?
            updateMapper();
        }
    }

    private void updateMapper(){
        int referenceTableRange = referenceTable.length;
        for(int i=0;i<referenceTableRange;i++){
            //int newIdx = i-offsetIdx;
            int newIdx = ((i-offsetIdx) % referenceTableRange + referenceTableRange) % referenceTableRange;
            mapper.put(referenceTable[i],referenceTable[newIdx]);
        }
    }

    public String encode (String plainText){
        

        //TODO:
        for (int i=0;i<plainText.length();i++){

        }


        for(int i=0;i<referenceTable.length;i++){
            //int newIdx = i-offsetIdx;
            int newIdx = ((i-offsetIdx) % referenceTable.length + referenceTable.length) % referenceTable.length;
            mapper.put(referenceTable[i],referenceTable[newIdx]);
        }

        return "";
    }
    public String decode (String encodedText){
        //TODO:
        return "";
    }

//    private void resetMapper(){
//        //mapper.clear();   //do not need
//        for (int i=0;i<referenceTable.length;i++){
//            //mapper.put(referenceTable[i],i);
//            mapper.put(referenceTable[i],referenceTable[i]);
//        }
//    }

    private boolean offsetValidator(char target){
        for (Character elem : referenceTable){
            if (elem == target)
                return true;
        }
        return false;
    }

    public int findIdxInReferenceTable(Character character){
        for (int i=0;i<referenceTable.length;i++){
            if (referenceTable[i] == character)
                return i;
        }
        throw new IllegalArgumentException("Input character array is not valid given the reference array.");
    }

}
