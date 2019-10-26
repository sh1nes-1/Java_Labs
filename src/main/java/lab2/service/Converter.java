package lab2.service;

import lab2.exception.ConvertException;

import java.io.File;
import java.io.Serializable;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;

public interface Converter<T extends Serializable> {

    String serializeToString(T obj) throws ConvertException;

    T deserializeString(String str) throws ConvertException;

    default void serializeToFile(T obj, String fileName) throws ConvertException {
        try {
            File file = new File(fileName);
            Path filePath = Path.of(file.toURI());
            String serializedString = serializeToString(obj);
            Files.writeString(filePath, serializedString);
        }
        catch (Exception ex) {
            throw new ConvertException(ex.getMessage());
        }
    }

    default T deserializeFile(String fileName) throws ConvertException {
        try {
            File file = new File(fileName);
            Path filePath = Path.of(file.toURI());
            String fileContent = Files.readString(filePath);
            return deserializeString(fileContent);
        }
        catch (Exception ex) {
            throw new ConvertException(ex.getMessage());
        }
    }

}
