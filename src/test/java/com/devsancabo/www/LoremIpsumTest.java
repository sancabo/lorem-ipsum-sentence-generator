package com.devsancabo.www;

import org.junit.Test;

public class LoremIpsumTest {

    @Test
    public void test() throws IllegalStateException {
        for(int i = 0; i<10000; ++i) {
            String randomSentence = LoremIpsum.getRandomSentence(50);
            System.out.println(randomSentence);
            if(randomSentence.length() > 50*10) throw new IllegalStateException("Maximum character amount is 500");
        }
    }
}
