package lab2.service.converter;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lab2.exception.ConvertException;
import lab2.service.Converter;

import java.io.Serializable;

public class XmlConverter<T extends Serializable> implements Converter<T> {

    private Class<T> typeOfClass;
    private XmlMapper mapper;

    {
        mapper = new XmlMapper();
    }

    public XmlConverter(Class<T> typeOfClass) {
        this.typeOfClass = typeOfClass;
    }

    @Override
    public String serializeToString(Serializable obj) throws ConvertException {
        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception ex) {
            throw new ConvertException(ex.getMessage());
        }
    }

    @Override
    public T deserializeString(String str) throws ConvertException {
        try {
            return mapper.readValue(str, typeOfClass);
        } catch (Exception ex) {
            throw new ConvertException(ex.getMessage());
        }
    }
}
