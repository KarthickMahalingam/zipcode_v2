package com.dp.zipcode;

import com.dp.zipcode.service.FileParser;
import com.dp.zipcode.service.impl.FileParserImpl;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;


/**
 * The type Zipcode parser test.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(ZipcodeParser.class)
@PowerMockIgnore("javax.management.*")
public class ZipcodeParserTest {

    @Rule
    ExpectedException expectedException = ExpectedException.none();
    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
    }

    /**
     * Expect exception when zipcode with incorrect length.
     */
    @Test
    public void ExpectExceptionWhenZipcodeWithIncorrectLength() {
        FileParser fileParser = new FileParserImpl();
        fileParser.initFile(getClass().getClassLoader().getResourceAsStream("zipcode_input_test2.json"));
        expectedException.expect(IllegalArgumentException.class);
        fileParser.parseZipcodeFromFile();
    }
}