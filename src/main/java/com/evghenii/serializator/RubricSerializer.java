package com.evghenii.serializator;

import com.evghenii.domain.Ad;
import com.evghenii.domain.Rubric;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class RubricSerializer extends StdSerializer<Rubric> {

    public RubricSerializer(Class<Rubric> t) {
        super(t);
    }

    public RubricSerializer() {
        this(null);
    }

    @Override
    public void serialize(Rubric rubric, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {

        gen.writeStartObject();

        gen.writeObjectField("id", rubric.getId());
        gen.writeStringField("name", rubric.getName());
        gen.writeObjectField("version", rubric.getVersion());
        gen.writeFieldName("add");

        gen.writeEndObject();
    }
}
