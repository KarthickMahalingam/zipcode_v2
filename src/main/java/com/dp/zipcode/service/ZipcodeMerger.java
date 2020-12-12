package com.dp.zipcode.service;

import com.dp.zipcode.entity.ShipmentZipcode;

/**
 * The interface Zipcode merger.
 */
public interface ZipcodeMerger {
    /**
     * Merge zip code.
     *
     * @param zipcodeData the zipcode data
     */
    void mergeZipCode(ShipmentZipcode zipcodeData);
}
