package com.mxvulture.luhngen.util;

/**
 * Created by vmacias on 1/3/17.
 */
public class CardVerifier {

    public static boolean isValidPAN(String pan){

        int checkDigit = pan.charAt(pan.length()-1) - '0';

        pan = pan.substring(0, pan.length()-1);

        int total = CardVerifier.getLuhnSum(pan);

        return ((checkDigit + total) % 10) == 0;

    }

    public static int getLuhnSum(String partialPAN){

        int total = 0;

        for (int i = partialPAN.length()-1, len = i; i>=0; --i){
            int digit = partialPAN.charAt(i) - '0';

            if ((len-i) % 2 == 0){

                if ((digit *= 2) > 9){
                    digit -= 9;
                }

            }

            total += digit;

        }

        return total;

    }

    public static int getCheckDigit(int presum, int modulo){

        return (modulo - (presum % modulo)) % modulo;

    }



}
