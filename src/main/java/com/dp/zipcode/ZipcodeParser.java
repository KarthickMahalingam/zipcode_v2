package com.dp.zipcode;

import com.dp.zipcode.entity.ShipmentZipcode;
import com.dp.zipcode.service.FileParser;
import com.dp.zipcode.service.ZipcodeMerger;
import com.dp.zipcode.service.impl.FileParserImpl;
import com.dp.zipcode.service.impl.ZipcodeMergerImpl;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * The type Zipcode parser.
 */
@Slf4j
public class ZipcodeParser {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        ZipcodeParser zipcodeParser = new ZipcodeParser();
        List<ShipmentZipcode> shipmentZipcodes = zipcodeParser.parseShipmentZipcodeFromFile("zipcode_input.json");
        zipcodeParser.mergeShipmentZipcode(shipmentZipcodes);
    }

    /**
     * Parse shipment zipcode from file list.
     *
     * @param fileName the file name
     * @return the list
     */
    public List<ShipmentZipcode> parseShipmentZipcodeFromFile(String fileName) {
        FileParser fileParser = new FileParserImpl();
        fileParser.initFile(ZipcodeParser.class.getClassLoader().getResourceAsStream(fileName));
        return fileParser.parseZipcodeFromFile();
    }

    /**
     * Merge shipment zipcode.
     *
     * @param shipmentZipcodes the shipment zipcodes
     */
    public void mergeShipmentZipcode(List<ShipmentZipcode> shipmentZipcodes) {
        ZipcodeMerger zipcodeMerger = new ZipcodeMergerImpl();
        shipmentZipcodes.forEach(e-> log.info(e.toString()));
        shipmentZipcodes.forEach(zipcodeMerger::mergeZipCode);
    }


}
