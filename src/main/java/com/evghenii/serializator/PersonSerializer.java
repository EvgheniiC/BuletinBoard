package com.evghenii.serializator;

import com.evghenii.domain.Email;
import com.evghenii.domain.Person;
import com.evghenii.domain.Phone;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class PersonSerializer extends StdSerializer<Person> {

    public PersonSerializer(Class<Person> t) {
        super(t);
    }

    protected PersonSerializer() {
        this(null);
    }

    @Override
    public void serialize(Person person, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {

        gen.writeStartObject();

        gen.writeObjectField("id", person.getId());
        gen.writeStringField("name", person.getName());
        gen.writeStringField("password", person.getPassword());
        gen.writeObjectField("version", person.getVersion());
        gen.writeObjectFieldStart("address");
            gen.writeObjectField("id", person.getAddress().getId());
        gen.writeEndObject();

        gen.writeFieldName("phone");
        gen.writeStartArray();
        for (Phone phone : person.getPhones()) {
            gen.writeNumber(phone.getId());
        }
        gen.writeEndArray();

        gen.writeFieldName("add");

        gen.writeFieldName("email");
        gen.writeStartArray();
        for (Email email : person.getEmails()) {
            gen.writeString(email.getEmail());
        }
        gen.writeEndArray();

        gen.writeEndObject();
    }
}
