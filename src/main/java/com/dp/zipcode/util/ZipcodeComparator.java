package com.dp.zipcode.util;

import com.dp.zipcode.entity.Zipcode;

import java.util.Comparator;

/**
 * The type Zipcode comparator.
 */
public class ZipcodeComparator implements Comparator<Zipcode> {
    @Override
    public int compare(Zipcode zipcode1, Zipcode zipcode2) {
        return Integer.compare(zipcode1.getStartRange(), zipcode2.getStartRange());
    }
}
