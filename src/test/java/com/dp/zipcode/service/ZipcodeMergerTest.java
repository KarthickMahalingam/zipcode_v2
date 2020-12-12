package com.dp.zipcode.service;

import com.dp.zipcode.entity.ShipmentZipcode;
import com.dp.zipcode.entity.Zipcode;
import com.dp.zipcode.service.impl.ZipcodeMergerImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The type Zipcode merger test.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(ZipcodeMerger.class)
class ZipcodeMergerTest {
    /**
     * The Zipcode merger.
     */
    ZipcodeMerger zipcodeMerger;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        zipcodeMerger = new ZipcodeMergerImpl();
    }

    /**
     * Merge zip code.
     *
     * @throws Exception the exception
     */
    @Test
    void mergeZipCode() throws Exception {
        List<Zipcode> zipcodesInput = new LinkedList<>();
        zipcodesInput.add(new Zipcode(94530, 94535));
        zipcodesInput.add(new Zipcode(94534, 94535));
        List<Zipcode> zipcodesExpected = new LinkedList<>();
        zipcodesExpected.add(new Zipcode(94530, 94535));
        zipcodeMerger.mergeZipCode(new ShipmentZipcode(zipcodesInput));
        List<Zipcode> mergedZipcode = Whitebox.invokeMethod(zipcodeMerger, "parseAndMergeZipCode", zipcodesInput);
        assertEquals(mergedZipcode, zipcodesExpected);
    }
}