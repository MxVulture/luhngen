package com.mxvulture.luhngen.generators;

import java.util.Set;

/**
 * Created by vmacias on 1/3/17.
 */
public interface CardGenerator {

    public Set<String> createCardNumbers(String bin, String extraCode, int offset, int length, int quantity);

}
