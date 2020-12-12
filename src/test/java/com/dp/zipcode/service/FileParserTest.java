package com.dp.zipcode.service;

import com.dp.zipcode.entity.ShipmentZipcode;
import com.dp.zipcode.entity.Zipcode;
import com.dp.zipcode.service.impl.FileParserImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.io.InputStream;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The type File parser test.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(FileParser.class)
class FileParserTest {

    /**
     * The File parser.
     */
    FileParser fileParser;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        fileParser = new FileParserImpl();
    }

    /**
     * Init file.
     */
    @Test
    void initFile() {
        InputStream inputStreamExpected = getClass().getClassLoader().getResourceAsStream("zipcode_input_test4.json");
        fileParser.initFile(inputStreamExpected);
        InputStream inputStreamActual = Whitebox.getInternalState(fileParser, "inputFile");
        assertEquals(inputStreamActual, inputStreamExpected);
    }

    /**
     * Parse zipcode from file.
     */
    @Test
    void parseZipcodeFromFile() {
        fileParser.initFile(getClass().getClassLoader().getResourceAsStream("zipcode_input_test4.json"));
        List<Zipcode> zipcodes = new LinkedList<>();
        zipcodes.add(new Zipcode(94530, 94535));
        zipcodes.add(new Zipcode(94534, 94535));
        assertEquals(fileParser.parseZipcodeFromFile(), Collections.singletonList(new ShipmentZipcode(zipcodes)));
    }
}