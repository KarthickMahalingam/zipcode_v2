package com.dp.zipcode.config;

import com.dp.zipcode.entity.ShipmentZipcode;
import com.dp.zipcode.entity.Zipcode;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * The type Zipcode deserializer.
 */
@Slf4j
public class ZipcodeDeserializer extends StdDeserializer<ShipmentZipcode> {
    /**
     * Instantiates a new Zipcode deserializer.
     */
    public ZipcodeDeserializer() {
        this(null);
    }

    /**
     * Instantiates a new Zipcode deserializer.
     *
     * @param vc the vc
     */
    public ZipcodeDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public ShipmentZipcode deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);
        List<Zipcode> zipcode = new LinkedList<>();
        jsonNode.forEach(e -> {
            if (e.size() < 2) {
                log.warn("Error parsing json: Zipcode range should have 2 values: {}", e.toString());
                throw new IllegalArgumentException("Error parsing json: Zipcode range should have 2 values: " + e.toString());
            }
            zipcode.add(new Zipcode(e.get(0).asInt(), e.get(1).asInt()));
        });
        return new ShipmentZipcode(zipcode);
    }
}
