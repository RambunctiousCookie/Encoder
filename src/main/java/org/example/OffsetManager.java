package org.example;

import java.util.*;

public class OffsetManager {
    private char offset;
    private boolean convertCase;
    private final Character[] referenceTable;
    private Set<Character> legalCharactersSet;
    private Map<Character,Character> encodeMapper, decodeMapper;

    public OffsetManager() {
        convertCase = true;
        //inline char array for faster instantiation than list, plus it is a size-immutable reference table
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
    public OffsetManager(char offset, boolean convertCase) {
        this.offset = offset;
        this.convertCase = convertCase;
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
    public char getOffset() {
        return offset;
    }
    public void setOffset(char offset){
        if (!isCharValid(offset))
            throw new IllegalArgumentException("Input character array is not valid given the reference table.");
        else{
            this.offset = offset;
            updateMappers();
        }
    }
    public Character[] getReferenceTable() {
        return referenceTable;
    }
    public boolean isConvertCase() {
        return convertCase;
    }
    public void setConvertCase(boolean convertCase) {
        this.convertCase = convertCase;
    }

    public String encode (String plainText){
        if(convertCase)
            plainText = plainText.toUpperCase();

        char[] plainTextChar = plainText.toCharArray();
        StringBuilder encodedString = new StringBuilder();

        for (int i=0;i<plainText.length();i++){
            if(isCharValid(plainTextChar[i]))
                encodedString.append(encodeMapper.get(plainTextChar[i]));
            else
                encodedString.append(plainTextChar[i]);
        }
        return encodedString.toString();
    }
    public String decode (String encodedText){
        if(convertCase)
            encodedText = encodedText.toUpperCase();

        char[] encodedTextChar = encodedText.toCharArray();
        StringBuilder decodedString = new StringBuilder();

        for (int i=0;i<encodedText.length();i++){
            if(isCharValid(encodedTextChar[i]))
                decodedString.append(decodeMapper.get(encodedTextChar[i]));
            else
                decodedString.append(encodedTextChar[i]);
        }
        return decodedString.toString();
    }

    private void updateMappers(){
        int offsetIdx = findIdxInReferenceTable(offset);
        int referenceTableRange = referenceTable.length;
        for(int i=0;i<referenceTableRange;i++){
            int newIdxEncode = ((i-offsetIdx) % referenceTableRange + referenceTableRange) % referenceTableRange;
            encodeMapper.put(referenceTable[i],referenceTable[newIdxEncode]);

            int newIdxDecode = ((i+offsetIdx) % referenceTableRange);
            decodeMapper.put(referenceTable[i],referenceTable[newIdxDecode]);
        }
    }
    private int findIdxInReferenceTable(Character character){
        for (int i=0;i<referenceTable.length;i++){
            if (referenceTable[i] == character)
                return i;
        }
        throw new IllegalArgumentException("Input character array is not valid given the reference array.");
    }
    private boolean isCharValid(char target){
        // Use a field variable for HashSet because of assumption
        //  Assume we might need to check strings
        //  Maybe not just single char in future implementations
        return legalCharactersSet.contains(target);
    }
    private boolean isStringValid(String target){
        char[] targetToUse = target.toCharArray();
        for(int i=0;i<target.length();i++){
            if (!legalCharactersSet.contains(targetToUse[i])){
                System.out.println("DEBUG: Illegal character "+targetToUse[i]+" at position "+i);
                return false;
            }
        }
        return true;
    }
}
