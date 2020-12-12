package com.dp.zipcode.service.impl;

import com.dp.zipcode.entity.ShipmentZipcode;
import com.dp.zipcode.service.FileParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * The type File parser.
 */
@Slf4j
public class FileParserImpl implements FileParser {
    private InputStream inputFile;

    public void initFile(InputStream inputFile) {
        this.inputFile = inputFile;
    }

    public void initFileByPath(String fileName) {
        this.inputFile = this.getClass().getClassLoader().getResourceAsStream(fileName);
    }

    public List<ShipmentZipcode> parseZipcodeFromFile() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Optional<List<ShipmentZipcode>> zipcodes = Optional.of(Collections.emptyList());
        try {
            zipcodes = Optional.of(objectMapper.readValue(inputFile, new TypeReference<List<ShipmentZipcode>>() {
            }));
        } catch (JsonMappingException jsonMappingException) {
            log.error("Error parsing the shipment zicodes:",  jsonMappingException);
            throw new IllegalArgumentException("Error parsing the shipment zipcodes");
        } catch (IOException ioException) {
            log.error("Error parsing the shipment zicodes:",  ioException);
            ioException.printStackTrace();
        }
        return zipcodes.orElseThrow(NullPointerException::new);
    }
}
