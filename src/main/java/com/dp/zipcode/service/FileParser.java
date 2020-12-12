package com.dp.zipcode.service;

import com.dp.zipcode.entity.ShipmentZipcode;

import java.io.InputStream;
import java.util.List;

/**
 * The interface File parser.
 */
public interface FileParser {
    /**
     * Init file.
     *
     * @param inputFile the input file
     */
    void initFile(InputStream inputFile);

    /**
     * Init file by path.
     *
     * @param fileName the file name
     */
    void initFileByPath(String fileName);

    /**
     * Parse zipcode from file list.
     *
     * @return the list
     */
    List<ShipmentZipcode> parseZipcodeFromFile();
}
