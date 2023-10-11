package org.example;

import java.util.*;

public class OffsetManager {
    //TODO: account for both uppercase and lowercase?
    //  char for to inline for quick instantiation?
    //
    private char offset;
    private int offsetIdx;
    private Character[] referenceTable;
    private Set<Character> legalCharactersSet;
    private Map<Character,Character> encodeMapper, decodeMapper;

    public char getOffset() {
        return offset;
    }
    public Character[] getReferenceTable() {
        return referenceTable;
    }

    public Map<Character, Character> getEncodeMapper() {
        //TODO: remove this method after testing
        return encodeMapper;
    }

    public Map<Character, Character> getDecodeMapper() {
        return decodeMapper;
    }

    public OffsetManager() {
        referenceTable = new Character[] {
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                '(', ')', '*', '+', ',', '-', '.', '/'
        };
        legalCharactersSet = new HashSet<>(Arrays.asList(referenceTable));
        encodeMapper = new HashMap<>();
        decodeMapper = new HashMap<>();
    }

    public void updateOffset(char newOffset){
        if (!isCharValid(newOffset))
            throw new IllegalArgumentException("Input character array is not valid given the reference table.");
        else{
            this.offset = newOffset;
            this.offsetIdx = findIdxInReferenceTable(newOffset);

            //TODO: move to decode/encode?
            updateMappers();
        }
    }

    private void updateMappers(){
        int referenceTableRange = referenceTable.length;
        for(int i=0;i<referenceTableRange;i++){
            //int newIdx = i-offsetIdx;
            int newIdxEncode = ((i-offsetIdx) % referenceTableRange + referenceTableRange) % referenceTableRange;
            encodeMapper.put(referenceTable[i],referenceTable[newIdxEncode]);

            int newIdxDecode = ((i+offsetIdx) % referenceTableRange);
            decodeMapper.put(referenceTable[i],referenceTable[newIdxDecode]);
        }
    }

    public String encode (String plainText){
//        if(!isStringValid(plainText))
//            throw new IllegalArgumentException("String contains invalid characters.");

        char[] plainTextChar = plainText.toCharArray();
        String encodedString = "";

        //TODO: streams?
        for (int i=0;i<plainText.length();i++){
            if(isCharValid(plainTextChar[i]))
                encodedString += encodeMapper.get(plainTextChar[i]);
            else
                encodedString += plainTextChar[i];
        }

        return encodedString;
    }
    public String decode (String encodedText){
//        if(!isStringValid(encodedText))
//            throw new IllegalArgumentException("String contains invalid characters.");

        char[] encodedTextChar = encodedText.toCharArray();
        String decodedString = "";

        for (int i=0;i<encodedText.length();i++){
            if(isCharValid(encodedTextChar[i]))
                decodedString += decodeMapper.get(encodedTextChar[i]);
            else
                decodedString += encodedTextChar[i];
        }

        return decodedString;
    }

//    private void resetMapper(){
//        //mapper.clear();   //do not need
//        for (int i=0;i<referenceTable.length;i++){
//            //mapper.put(referenceTable[i],i);
//            mapper.put(referenceTable[i],referenceTable[i]);
//        }
//    }

    private boolean isStringValid(String target){
        //return target.chars().allMatch(legalCharactersSet::contains);

        char[] targetToUse = target.toCharArray();
        for(int i=0;i<target.length();i++){
            if (!legalCharactersSet.contains(targetToUse[i])){
                System.out.println("DEBUG: Illegal character "+targetToUse[i]+" at position "+i);
                return false;
            }
        }
        return true;
    }
    private boolean isCharValid(char target){
        // Use a field variable for HashSet because we will check strings not just single char
        return legalCharactersSet.contains(target);
    }

    public int findIdxInReferenceTable(Character character){
        for (int i=0;i<referenceTable.length;i++){
            if (referenceTable[i] == character)
                return i;
        }
        throw new IllegalArgumentException("Input character array is not valid given the reference array.");
    }

}
