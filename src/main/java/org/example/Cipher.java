package org.example;

import java.util.*;

public class Cipher {
    private static final Character[] referenceTable = new Character[] {
            //inline char array for faster instantiation than list, plus it is a size-immutable reference table
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            '(', ')', '*', '+', ',', '-', '.', '/'
    };
    private Set<Character> legalCharactersSet;
    public Cipher() {
        legalCharactersSet = new HashSet<>(Arrays.asList(referenceTable));
    }
    public char getOffset(String target) {
        char offset = target.toCharArray()[0];
        if (!isCharValid(offset))
            throw new IllegalArgumentException("Input character array is not valid given the reference table.");
        else
            return offset;
    }
    public String getString(String target) {
        return target.substring(1);
    }
    public Character[] getReferenceTable() {
        return referenceTable;
    }

//    //Debugging;
//    public Map<Character, Character> getEncodeMapper() {
//
//        return encodeMapper;
//    }
//    public Map<Character, Character> getDecodeMapper() {
//        return decodeMapper;
//    }

//    private void updateMapperOld(){
//        int referenceTableRange = referenceTable.length;
//        for(int i=0;i<referenceTableRange;i++){
//            int newIdxEncode = ((i-offsetIdx) % referenceTableRange + referenceTableRange) % referenceTableRange;
//            encodeMapper.put(referenceTable[i],referenceTable[newIdxEncode]);
//
//            int newIdxDecode = ((i+offsetIdx) % referenceTableRange);
//            decodeMapper.put(referenceTable[i],referenceTable[newIdxDecode]);
//        }
//    }

    //TODO: update mapper modularly?
    //  OOP?
    public String encode (String plainText){
        char[] plainTextChar = getString(plainText).toCharArray();
        int offsetIdx = findIdxInReferenceTable(getOffset(plainText));
        Map<Character,Character> encodeMapper = getMapper(offsetIdx,true);

//        StringBuilder encodedString = new StringBuilder();
//
//        for (int i=0;i<plainTextChar.length;i++){
//            if(isCharValid(plainTextChar[i]))
//                encodedString.append(encodeMapper.get(plainTextChar[i]));
//            else
//                encodedString.append(plainTextChar[i]);
//        }
//        return encodedString.toString();
        return constructProcessedString(plainTextChar,encodeMapper);
    }
    public String decode (String encodedText){
        char[] encodedTextChar = getString(encodedText).toCharArray();
        int offsetIdx = findIdxInReferenceTable(getOffset(encodedText));
        Map<Character,Character> decodeMapper = getMapper(offsetIdx,false);

//        StringBuilder decodedString = new StringBuilder();

//        for (int i=0;i<encodedTextChar.length;i++){
//            if(isCharValid(encodedTextChar[i]))
//                decodedString.append(decodeMapper.get(encodedTextChar[i]));
//            else
//                decodedString.append(encodedTextChar[i]);
//        }
//        return decodedString.toString();
        return constructProcessedString(encodedTextChar,decodeMapper);
    }
    private String constructProcessedString(char[] unprocessedCharArray, Map<Character,Character> mapper){
        StringBuilder decodedString = new StringBuilder();
        for (int i=0;i<unprocessedCharArray.length;i++){
            if(isCharValid(unprocessedCharArray[i]))
                decodedString.append(mapper.get(unprocessedCharArray[i]));
            else
                decodedString.append(unprocessedCharArray[i]);
        }
        return decodedString.toString();

    }
    private Map<Character,Character> getMapper(int offsetIdx, boolean isEncoding){
        Map<Character,Character> mapper = new HashMap<>();
        int referenceTableRange = referenceTable.length;
        for(int i=0; i<referenceTableRange; i++){
            int newIdx;
            if (isEncoding)
                newIdx = ((i-offsetIdx) % referenceTableRange + referenceTableRange) % referenceTableRange;
            else
                newIdx = ((i+offsetIdx) % referenceTableRange);
            mapper.put(referenceTable[i],referenceTable[newIdx]);
        }
        return mapper;
    }
    private int findIdxInReferenceTable(Character character){
        for (int i=0;i<referenceTable.length;i++){
            if (referenceTable[i] == character)
                return i;
        }
        throw new IllegalArgumentException("Input character array is not valid given the reference array.");
    }
    private boolean isCharValid(char target){
        return legalCharactersSet.contains(target);
    }

}
