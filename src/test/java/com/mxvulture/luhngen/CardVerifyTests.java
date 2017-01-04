package com.mxvulture.luhngen;

import com.mxvulture.luhngen.generators.CardGenerator;
import com.mxvulture.luhngen.generators.RandomGenerator;
import com.mxvulture.luhngen.util.CardVerifier;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

/**
 * Created by vmacias on 1/3/17.
 */
public class CardVerifyTests {

    @Test
    public void testVerifier(){

        Assert.assertTrue(CardVerifier.isValidPAN("5555555555555557"));

        Assert.assertEquals(CardVerifier.getCheckDigit(CardVerifier.getLuhnSum("555555555555555"), 10), 7);

        Assert.assertEquals(CardVerifier.getCheckDigit(CardVerifier.getLuhnSum("530056910044605"), 10), 3);

    }


    @Test
    public void testRandomGenerator(){

        CardGenerator generator = new RandomGenerator(2000);
        String bin = "564838";
        String extraCode = "00";

        Set<String> cards = generator.createCardNumbers(bin, extraCode, 0, 16, 500);

        Assert.assertEquals(cards.size(), 500);

        for(String card : cards){
            Assert.assertTrue(card.startsWith(bin));
            Assert.assertEquals(card.length(), 16);

            Assert.assertTrue(CardVerifier.isValidPAN(card));
        }

    }

    @Test
    public void testBulkRandomGenerator(){

        CardGenerator generator = new RandomGenerator(2000);
        String bin = "564838";
        String extraCode = "00";

        Set<String> cards = generator.createCardNumbers(bin, extraCode, 0, 16, 50000);

        Assert.assertEquals(cards.size(), 50000);

        for(String card : cards){
            Assert.assertTrue(card.startsWith(bin));
            Assert.assertEquals(card.length(), 16);

            Assert.assertTrue(CardVerifier.isValidPAN(card));
        }

    }

}
