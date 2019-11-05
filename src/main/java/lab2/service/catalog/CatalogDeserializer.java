package lab2.service.catalog;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import lab2.model.Catalog;
import lab2.model.SmartPhone;
import java.io.IOException;
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
        jp.nextToken();

        // Skip FIELD_NAME (SmartPhones)
        jp.nextToken();

        // Skip START_ARRAY (JSON) | START_OBJECT (XML)
        jp.nextToken();

        // Skip XML FIELD_NAME (item)
        if (jp instanceof FromXmlParser) {
            jp.nextToken();
        }

        XmlMapper mapper = new XmlMapper();

        while (jp.currentToken() == JsonToken.START_OBJECT) {
            // Skip START_OBJECT
            jp.nextToken();

            // Skip FIELD_NAME (smartPhone)
            jp.nextToken();

            SmartPhone smartPhone = mapper.readValue(jp, SmartPhone.class);

            // Skip smartPhone
            jp.nextToken();

            // Skip FIELD_NAME (amount)
            jp.nextToken();

            Integer amount = jp.getValueAsInt();

            // Skip VALUE_NUMBER_INT (Amount)
            jp.nextToken();

            // Skip END_OBJECT
            jp.nextToken();

            // XML Skip FIELD_NAME (item)
            if (jp instanceof FromXmlParser) {
                jp.nextToken();
            }

            result.addGoodsItem(smartPhone, amount);
        }

        return result;
    }
}
