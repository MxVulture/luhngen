package com.mxvulture.luhngen.generators;

import com.mxvulture.luhngen.util.CardVerifier;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by vmacias on 1/3/17.
 */
public class RandomGenerator implements CardGenerator {

    private int seed;

    public RandomGenerator(){
        this(new Random().nextInt());
    }

    public RandomGenerator(int seed){

        this.seed = seed;

    }

    @Override
    public Set<String> createCardNumbers(String bin, String extraCode, int offset, int length, int quantity) {

        Set<String> results = new HashSet<>();

        String prefix = bin+extraCode;

        int missing = (length - prefix.length()) - 1;

        Random masterRandom = new Random(seed);

        Random[] randomGens = new Random[missing];

        for(int i = 0; i<missing; ++i){
            randomGens[i] = new Random(masterRandom.nextInt());
        }

        for(int i = 0; i<quantity; ++i){

            StringBuilder sb = new StringBuilder(prefix);

            for(Random r : randomGens ){
                sb.append(r.nextInt(10));
            }

            if(offset-- > 0){
                --i;
                continue;
            }

            String elPan = sb.toString();

            sb.append(CardVerifier.getCheckDigit(CardVerifier.getLuhnSum(elPan), 10));

            if (!results.add(sb.toString())){
                --i;
            }

        }



        return results;
    }

}
