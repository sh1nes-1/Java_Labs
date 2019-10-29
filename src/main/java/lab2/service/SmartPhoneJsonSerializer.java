package lab2.service;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import lab2.model.SmartPhone;

import java.io.IOException;

public class SmartPhoneJsonSerializer extends JsonSerializer<SmartPhone> {
    @Override
    public void serialize(SmartPhone smartPhone, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeFieldName(new ObjectMapper().writeValueAsString(smartPhone));
    }
}
