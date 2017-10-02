package com.acton.interview.stringperm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Implements search of all unique permutations of variable length string,
 * where each character in given string can occur multiple times.
 *
 * Implementation is based on "Next Lexicographical Permutation" algorithm.
 * At each step, algorithm is searching for next lexicographically successive permutation.
 *
 * <b>Algorithm steps:</b>
 *
 * 1. Given the character sequence in non-decreasing order, sort it and print it.
 *    The first permutation is the character sequence sorted in non-decreasing order.
 * 2. Find next higher permutation of the char sequence. Do this until next higher permutation
 *    is not possible. If you reach a permutation where all characters are sorted in non-decreasing
 *    order, than that permutation is the last permutation.
 *
 * <b>Steps to find next higher permutation:</b>
 *
 * 1. Use the previously found permutation and seek for the rightmost character in it,
 *    which is lexicographically smaller than its next (right) character. Let us call
 *    this character as 'Pivot A'
 * 2. Find the 'ceiling' of the 'Pivot A' character. 'Ceiling' is the smallest character on right of
 *    'Pivot A' character, which is greater than 'Pivot A' character. Let us call 'ceil' character
 *    as 'Pivot B'
 * 3. Swap the two characters found in above 2 steps.
 * 4. Sort the character sequence (in non-decreasing order) after the original index of 'Pivot A' character
 *
 * Consider string "BANANA". Let previously printed permutation be "AABNNA". The next permutation in sorted
 * order should be "AANABN". This is deducted by following steps:
 *  1. 'Pivot A' character will be 'B' at index 2.
 *  2. The ceiling of character 'B' will be character 'N' at index 3. This is 'Pivot B'
 *  3. After swapping characters at 'Pivot A' and 'Pivot B' we get "AANBNA".
 *  4. The final step is to sort substring after the character at original index of 'Pivot A'.
 *     This is substring "BNA" which when sorted yields "ABN".
 *  5. Finally we get "AANABN"
 *
 * Example: For given string "AABC" program finds following unique permutations:
 *          AABC, AACB, ABAC, ABCA, ACAB, ACBA, BAAC, BACA, BCAA, CAAB, CABA, CBAA
 *
 * @author  Amer Zec
 * Date:    9/30/17
 */
public class UniquePermutation {

    private static final Logger logger = LoggerFactory.getLogger(UniquePermutation.class);

    /**
     * Main method - entry point to program
     * @param args
     */
    public static void main(String[] args) {
        if(args.length > 1) {
            throw new IllegalArgumentException("Please provide only one string as argument for which you want to find unique permutations!");
        }
        String str = args[0];
        if(str.length() == 0) {
            throw new IllegalArgumentException("Please provide string with at least one character!");
        }
        str = str.toUpperCase();//For simplicity make input string uppercase
        List<String> lexicPermutations = getPermutations(str);
        System.out.println("Found permutations:");
        for(String s: lexicPermutations){
            System.out.println(s);
        }
        System.out.println("Total unique permutations: " + lexicPermutations.size());
    }

    /**
     * Prevent constructing
     * objects of this class.
     */
    private UniquePermutation(){
    }

    /**
     * Method with permutation algorithm implementation
     * Algorithm is based on recursive rotations of string characters
     * that generates tree where each leaf is one permutation of the original (tree root)
     * string.
     * @param str string to calculate permutation for
     */
    public static List<String> getPermutations(String str)
    {
        List<String> allPermutations = new ArrayList<>();
        int len = str.length();
        str = sortString(str); //Sort the string in lexicographically non-decreasing order
        logger.debug("Sorted string: ".concat(str));
        boolean isFinished = false; //status flag to know when to stop searching for 'next higher permutation'
        while(!isFinished){
            logger.debug("Adding " + str + " to the list of permutations!");
            allPermutations.add(str); //Add to list of permutations
            int pivotA = findFirstPivot(str);//Find the rightmost character smaller than its next character.
            if(pivotA == -1){
                //If there isn't such character, then all are sorted in decreasing order,
                //This means we just found the last permutation and we are done.
                isFinished = true;
            } else {
                logger.debug("Rightmost character (pivotA) smaller than its next character: " + str.charAt(pivotA));
                // Find the ceil of 'first char' in right of first character.
                int pivotB = findCeilPivot(str, str.charAt(pivotA), pivotA+1, len-1);
                logger.debug("Ceil (pivotB) of " + str.charAt(pivotA) + " found to be: " + str.charAt(pivotB));
                str = swapChars(str, pivotA, pivotB); // Swap characters at pivotA and pivotB
                logger.debug("String after swapping pivotA and pivotB characters: " + str);
                str = sortSubstring(str, pivotA+1, len);
            }
        }
        return allPermutations;
    }

    /**
     * Swaps characters at position i and position j
     * @param str String value in which characters are being swapped
     * @param i Position of first character being swapped
     * @param j Position of second character being swapped
     * @return String where characters in positions i and j are swapped
     */
    private static String swapChars(String str, int i, int j)
    {
        if (i == j)
            return str;

        char temp;
        char[] charArray = str.toCharArray();
        temp = charArray[i] ;
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return String.valueOf(charArray);
    }

    /**
     * Finds 'ceil' character of 'pivotA' in string 'str' starting
     * right from character 'pivotA', starting search at position s (start)
     * and ending at position e (end).
     * Ceil of a character is the smallest character greater than it.
     * @param str
     * @param pivotA
     * @param s
     * @param e
     * @return Index of 'ceil' character of 'pivotA'
     */
    private static int findCeilPivot(String str, char pivotA, int s, int e){
        int ceil_index = s;
        for(int i=s+1;i<=e;i++){
            if(str.charAt(i) > pivotA && str.charAt(i) < str.charAt(ceil_index)){
                ceil_index = i;
            }
        }
        return ceil_index;
    }

    /**
     * Finds the rightmost character in string 'str' lexicographically smaller
     * than its next character.
     * @param str String in which pivot is searched
     * @return Index of the pivot
     */
    private static int findFirstPivot(String str){
        int i;
        for(i=str.length()-2;i>=0; --i){
            if(str.charAt(i) < str.charAt(i+1)){
                break;
            }
        }
        return i;
    }

    /**
     * Sorts string 'toSort' in lexicographically non-decreasing order.
     * @param toSort String being sorted in non-decreasing order
     * @return Sorted string
     */
    private static String sortString(String toSort){
        char[] charArray = toSort.toCharArray();
        Arrays.sort(charArray);
        return String.valueOf(charArray);
    }

    /**
     * Sorts substring of 'toSort' from 'startIndex' to 'endIndex'
     * in lexicographically non-decreasing order.
     * @param toSort String for which substring is being sorted
     * @param startIndex Begining index of substring being sorted
     * @param endIndex Ending index of substring being sorted
     * @return String with substring sorted
     */
    private static String sortSubstring(String toSort, int startIndex, int endIndex) {
        char[] charArray = toSort.toCharArray();
        Arrays.sort(charArray, startIndex, endIndex);
        return String.valueOf(charArray);
    }
}
