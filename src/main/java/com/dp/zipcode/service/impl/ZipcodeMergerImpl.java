package com.dp.zipcode.service.impl;

import com.dp.zipcode.entity.ShipmentZipcode;
import com.dp.zipcode.entity.Zipcode;
import com.dp.zipcode.service.ZipcodeMerger;
import com.dp.zipcode.util.ZipcodeComparator;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * The type Zipcode merger.
 */
@Slf4j
public class ZipcodeMergerImpl implements ZipcodeMerger {
    private List<Zipcode> sortZipcodeByStartRange(List<Zipcode> zipcodes) {
        zipcodes.sort(new ZipcodeComparator());
        return zipcodes;
    }

    private List<Zipcode> parseAndMergeZipCode(List<Zipcode> zipcodes) {
        Zipcode mergedZipcode = null;
        List<Zipcode> zipcodeList = new LinkedList<>();
        for (Zipcode zipcode : sortZipcodeByStartRange(zipcodes)) {
            if (Objects.isNull(mergedZipcode)) {
                mergedZipcode = zipcode;
            } else {
                if (mergedZipcode.getEndRange() >= zipcode.getStartRange()) {
                    mergedZipcode.setEndRange(Math.max(mergedZipcode.getEndRange(), zipcode.getEndRange()));
                } else {
                    zipcodeList.add(mergedZipcode);
                    mergedZipcode = zipcode;
                }
            }
        }
        zipcodeList.add(mergedZipcode);
        return zipcodeList;
    }

    public void mergeZipCode(ShipmentZipcode zipcodeData) {
        List<Zipcode> mergedZipcode = parseAndMergeZipCode(zipcodeData.getZipcode());
        log.info("Merged zipcode for: {}", zipcodeData.toString());
        for (Zipcode zipcode : mergedZipcode) {
            log.info("[{}, {}] ",zipcode.getStartRange(), zipcode.getEndRange());
        }
    }
}
