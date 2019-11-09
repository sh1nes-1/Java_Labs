package lab2.service.catalog;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import lab2.model.Catalog;
import lab2.model.SmartPhone;

import java.io.IOException;
import java.util.Map;

public class CatalogSerializer extends StdSerializer<Catalog> {
    protected CatalogSerializer() {
        super(Catalog.class);
    }


    @Override
    public void serialize(Catalog catalog, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();

        jsonGenerator.writeFieldName("SmartPhones");
        jsonGenerator.writeStartArray();

        if (jsonGenerator instanceof ToXmlGenerator) {
            jsonGenerator.writeStartObject();
        }

        for (Map.Entry<SmartPhone, Integer> e : catalog.getGoods().entrySet()) {
            if (jsonGenerator instanceof ToXmlGenerator) {
                jsonGenerator.writeFieldName("item");
            }

            jsonGenerator.writeStartObject();

            jsonGenerator.writeObjectField("smartPhone", e.getKey());
            jsonGenerator.writeObjectField("amount", e.getValue());

            jsonGenerator.writeEndObject();
        }

        if (jsonGenerator instanceof ToXmlGenerator) {
            jsonGenerator.writeEndObject();
        }

        jsonGenerator.writeEndArray();

        jsonGenerator.writeEndObject();
    }
}
