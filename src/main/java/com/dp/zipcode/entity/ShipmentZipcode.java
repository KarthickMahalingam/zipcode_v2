package com.dp.zipcode.entity;

import com.dp.zipcode.config.ZipcodeDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * The type Shipment zipcode.
 */
@Data
@AllArgsConstructor
@JsonDeserialize(using = ZipcodeDeserializer.class)
public class ShipmentZipcode {
    private List<Zipcode> zipcode;
}
