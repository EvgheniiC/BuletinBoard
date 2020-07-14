package com.evghenii.deserializer;

import com.evghenii.domain.Ad;
import com.evghenii.domain.Person;
import com.evghenii.domain.Rubric;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

public class AdDeserializer extends StdDeserializer<Ad> {
    public AdDeserializer(Class<?> vc) {
        super(vc);
    }

    public AdDeserializer() {
        this(null);
    }

    @Override
    public Ad deserialize(JsonParser p, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {

        Ad ad = new Ad();

        ObjectCodec codec = p.getCodec();

        JsonNode node = codec.readTree(p);

        int id = node.get("id").asInt();
        int version = node.get("version").asInt();
        int rubricId = node.get("rubric").asInt();
        int personId = node.get("person").asInt();
        boolean active = node.get("active").asBoolean();
        String text = node.get("text").asText();
        String title = node.get("title").asText();
        String dat = node.get("date").asText();
        LocalDate date = LocalDate.parse(dat);
        BigDecimal price = new BigDecimal(node.get("price").asDouble());

        Rubric rubric = new Rubric(rubricId);

        Person person = new Person(personId);

        ad.setRubric(rubric);
        ad.setPerson(person);

        ad.setActive(active);
        ad.setDate(date);
        ad.setText(text);
        ad.setTitle(title);
        ad.setVersion(version);
        ad.setPrice(price);
        ad.setId(id);

        return ad;
    }


}
