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

public class CatalogDeserializer extends StdDeserializer<Catalog> {
    protected CatalogDeserializer() {
        super(Catalog.class);
    }

        /* OLD deserialize

        Catalog result = new Catalog();

        JsonNode rootNote = jp.getCodec().readTree(jp);

        if (jp instanceof FromXmlParser) {
            rootNote = rootNote.get("Catalog");
        }

        JsonNode catalogNode = rootNote.get("SmartPhones");

        //if (!catalogNode.isArray())
          //  throw new JsonParseException(jp, "Array expected");

        ObjectMapper mapper;
        if (jp instanceof FromXmlParser) {
             mapper = new XmlMapper();
        } else {
             mapper = new ObjectMapper();
        }

        for (JsonNode item : catalogNode) {
            if (jp instanceof FromXmlParser) {
                item = item.get("item");
            }

            SmartPhone smartPhone = mapper.readValue(item.get("smartPhone").toString(), SmartPhone.class);
            Integer amount = item.get("amount").asInt();
            result.addGoodsItem(smartPhone, amount);
        }

        return result;

     */

    @Override
    public Catalog deserialize(JsonParser jp, DeserializationContext context) throws IOException {

        Catalog result = new Catalog();

        // startObject
        JsonToken jt = jp.nextToken();

        // SmartPhones fieldName
        jt = jp.nextToken();

        // SmartPhones startObject
        if (jp instanceof FromXmlParser) {
            jt = jp.nextToken();
        }

        XmlMapper mapper = new XmlMapper();

        do {
            if (jp instanceof FromXmlParser) {
                // item fieldName start
                jt = jp.nextToken();
            }

            // item
            jt = jp.nextToken();

            // smartPhone fieldName
            jt = jp.nextToken();

            // smartPhone object
            jt = jp.nextToken();
            SmartPhone smartPhone = mapper.readValue(jp, SmartPhone.class);

            // amount fieldName
            jt = jp.nextToken();

            // amount object
            jt = jp.nextToken();
            Integer amount = jp.getValueAsInt();

            // add to result
            result.addGoodsItem(smartPhone, amount);

            if (jp instanceof FromXmlParser) {
                // item endObject
                jt = jp.nextToken();
            }
        }
        while (jp.nextToken() != JsonToken.END_OBJECT);

        return result;
    }
}
