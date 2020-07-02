package com.evghenii.serializator;

import com.evghenii.domain.SuitableAd;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class SuitableAdSerializer extends StdSerializer<SuitableAd> {


    protected SuitableAdSerializer(Class<SuitableAd> t) {
        super(t);
    }

    protected SuitableAdSerializer () {
        this(null);
    };

    @Override
    public void serialize(SuitableAd ad, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {

        gen.writeStartObject();

        gen.writeObjectField("id",ad.getId());
        gen.writeStringField("title", ad.getTitle());
        gen.writeObjectField("version", ad.getVersion());

        gen.writeObjectFieldStart("person");
        gen.writeObjectField("name", ad.getPerson().getName());
        gen.writeEndObject();

        gen.writeObjectFieldStart("rubric");
        gen.writeObjectField("name", ad.getRubric().getName());
        gen.writeEndObject();

        gen.writeEndObject();
    }
}
