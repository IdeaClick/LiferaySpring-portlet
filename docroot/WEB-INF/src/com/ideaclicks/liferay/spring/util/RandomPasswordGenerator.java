package com.ideaclicks.liferay.spring.util;


import java.util.Random;

public class RandomPasswordGenerator {
    private static final String ALPHA_CAPS  = GlobalConstants.ALPHA_CAPS;
    private static final String ALPHA   = GlobalConstants.ALPHA;
    private static final String NUM     = GlobalConstants.NUM;
    private static final String SPL_CHARS   = GlobalConstants.SPL_CHARS;
    private static int noOfCAPSAlpha = GlobalConstants.NOOFCAPSALPHA;
    private static int noOfDigits = GlobalConstants.NOOFDIGITS;
    private static int noOfSplChars = GlobalConstants.NOOFSPLCHARS;
    private static int minLen = GlobalConstants.MINLEN;
    private static int maxLen = GlobalConstants.MAXLEN;
    public static char[] generatePswd() {
        if(minLen > maxLen)
            throw new IllegalArgumentException("Min. Length > Max. Length!");
        if( (noOfCAPSAlpha + noOfDigits + noOfSplChars) > minLen )
            throw new IllegalArgumentException
            ("Min. Length should be atleast sum of (CAPS, DIGITS, SPL CHARS) Length!");
        Random rnd = new Random();
        int len = rnd.nextInt(maxLen - minLen + 1) + minLen;
        char[] pswd = new char[len];
        int index = 0;
        for (int i = 0; i < noOfCAPSAlpha; i++) {
            index = getNextIndex(rnd, len, pswd);
            pswd[index] = ALPHA_CAPS.charAt(rnd.nextInt(ALPHA_CAPS.length()));
        }
        for (int i = 0; i < noOfDigits; i++) {
            index = getNextIndex(rnd, len, pswd);
            pswd[index] = NUM.charAt(rnd.nextInt(NUM.length()));
        }
        for (int i = 0; i < noOfSplChars; i++) {
            index = getNextIndex(rnd, len, pswd);
            pswd[index] = SPL_CHARS.charAt(rnd.nextInt(SPL_CHARS.length()));
        }
        for(int i = 0; i < len; i++) {
            if(pswd[i] == 0) {
                pswd[i] = ALPHA.charAt(rnd.nextInt(ALPHA.length()));
            }
        }
        return pswd;
    }
     
    private static int getNextIndex(Random rnd, int len, char[] pswd) {
        int index = rnd.nextInt(len);
        while(pswd[index = rnd.nextInt(len)] != 0);
        return index;
    }
}