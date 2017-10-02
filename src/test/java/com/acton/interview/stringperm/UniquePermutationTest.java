package com.acton.interview.stringperm;

import org.junit.Test;

import java.util.List;
import static org.junit.Assert.assertTrue;

/**
 * Tests for UniquePermutation.
 * @author Amer Zec
 * Date: 10/1/2017
 */
public class UniquePermutationTest {

    @Test
    public void testSingleCharPermutations() {
        String testString = "A";
        List<String> permutations = UniquePermutation.getPermutations(testString);
        assertTrue("String " + testString + " has only one permutation", permutations.size() == 1);
    }

    @Test
    public void testTwoIdenticalCharactersPermutations() {
        String testString = "AA";
        List<String> permutations = UniquePermutation.getPermutations(testString);
        assertTrue("String " + testString + " has only one permutation", permutations.size() == 1);
    }

    @Test
    public void testTwoDistinctCharactersPermutations() {
        String testString = "AB";
        List<String> permutations = UniquePermutation.getPermutations(testString);
        assertTrue("String " + testString + " has two permutations", permutations.size() == 2);
    }

    @Test
    public void testThreeDistinctCharactersPermutations() {
        String testString = "ABC";
        List<String> permutations = UniquePermutation.getPermutations(testString);
        assertTrue("String " + testString + " has " + testString.length() + "! permutations - note ! means factorial.", permutations.size() == factorial(testString.length()));
    }

    @Test
    public void testRepeatingCharactersPermutations() {
        String testString = "AABC";
        List<String> permutations = UniquePermutation.getPermutations(testString);

        int expectedPermutations = factorial(testString.length()) / //Number of regular permutations, divided by
                                        factorial(2); //Number of occurrences of letter A
        assertTrue("String " + testString + " has (4!)/(2!) permutations.", permutations.size() == expectedPermutations);
    }

    @Test
    public void testRepeatingCharactersPermutations2() {
        String testString = "AABBC";
        List<String> permutations = UniquePermutation.getPermutations(testString);

        int expectedPermutations = factorial(testString.length()) / //Number of regular permutations, divided by
                (factorial(2) * factorial(2)); //( factorial(A occurrences) * factorial(B occurrences)
        assertTrue("String " + testString + " has (4!)/(2! * 2!) permutations.", permutations.size() == expectedPermutations);
    }

    /**
     * Quick method to find factorial of n
     * @param n Number for which factorial is being calculated
     * @return n!
     */
    private static int factorial(int n){
        if (n == 0)
            return 1;
        else
            return(n * factorial(n-1));
    }
}
