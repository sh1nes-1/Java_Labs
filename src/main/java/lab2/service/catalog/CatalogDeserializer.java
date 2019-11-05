package lab2.service.catalog;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import lab2.model.Catalog;
import lab2.model.SmartPhone;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CatalogDeserializer extends StdDeserializer<Catalog> {
    protected CatalogDeserializer() {
        super(Catalog.class);
    }

    @Override
    public Catalog deserialize(JsonParser jp, DeserializationContext context) throws IOException {

        Catalog result = new Catalog();

        /* DEBUG
        JsonToken jit = jp.currentToken();
        List<String> tokens = new ArrayList<>();
        while (jp.hasCurrentToken()) {
            tokens.add(jit.toString());
            jit = jp.nextToken();
        }
        Files.writeString(Path.of("D:\\tokens.txt"), tokens.toString());
        */

        // Skip START_OBJECT
        JsonToken jt = jp.nextToken();

        // Skip FIELD_NAME (SmartPhones)
        jt = jp.nextToken();

        // Skip START_ARRAY (JSON) | START_OBJECT (XML)
        jt = jp.nextToken();

        // Skip XML FIELD_NAME (item)
        if (jp instanceof FromXmlParser) {
            jt = jp.nextToken();
        }

        XmlMapper mapper = new XmlMapper();

        while (jp.currentToken() == JsonToken.START_OBJECT) {
            // Skip START_OBJECT
            jt = jp.nextToken();

            // Skip FIELD_NAME (smartPhone)
            jt = jp.nextToken();

            SmartPhone smartPhone = mapper.readValue(jp, SmartPhone.class);

            // Skip smartPhone
            jt = jp.nextToken();

            // Skip FIELD_NAME (amount)
            jt = jp.nextToken();

            Integer amount = jp.getValueAsInt();

            // Skip VALUE_NUMBER_INT (Amount)
            jt = jp.nextToken();

            // Skip END_OBJECT
            jt = jp.nextToken();

            // XML Skip FIELD_NAME (item)
            if (jp instanceof FromXmlParser) {
                jt = jp.nextToken();
            }

            result.addGoodsItem(smartPhone, amount);
        }

        return result;
    }
}
