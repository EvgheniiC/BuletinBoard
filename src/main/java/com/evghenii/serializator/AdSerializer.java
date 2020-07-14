package com.evghenii.serializator;

import com.evghenii.domain.Ad;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class AdSerializer extends StdSerializer<Ad> {

    public AdSerializer(Class<Ad> t) {
        super(t);
    }

    public AdSerializer() {
        this(null);
    }

    @Override
    public void serialize(Ad ad, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {

        gen.writeStartObject();

        gen.writeObjectField("id", ad.getId());
        gen.writeStringField("text", ad.getText());
        gen.writeStringField("title", ad.getTitle());
        gen.writeObjectField("version", ad.getVersion());
        gen.writeObjectField("price", ad.getPrice());
        gen.writeObjectField("active", ad.isActive());
        gen.writeObjectField("rubric", ad.getRubric().getId());
        gen.writeStringField("date", ad.getDate().toString());
        gen.writeObjectFieldStart("person");
             gen.writeObjectField("id", ad.getPerson().getId());
        gen.writeEndObject();

        gen.writeEndObject();
    }

}
