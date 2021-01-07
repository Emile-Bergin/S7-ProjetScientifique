package com.Connector;

import com.Objects.Fire;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;

class FireSerializer extends JsonSerializer<List<Fire>> {


    @Override
    public void serialize(List<Fire> value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeStartArray();
        for (Fire f : value) {
            jgen.writeStartObject();
            jgen.writeEndObject();
        }
        jgen.writeEndArray();
    }

}
