package lab2.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class JsonConverter<T> implements Converter<T> {

    private Class<T> typeOfClass;
    private ObjectMapper mapper;

    {
        mapper = new ObjectMapper();
    }

    public JsonConverter(Class<T> typeOfClass) {
        this.typeOfClass = typeOfClass;
    }

    @Override
    public String serializeToString(T obj) throws JsonProcessingException {
        return mapper.writeValueAsString(obj);
    }

    @Override
    public T deserializeString(String str) throws IOException {
        return mapper.readValue(str, typeOfClass);
    }

    @Override
    public void serializeToFile(T obj, String fileName) throws IOException {
        String jsonString = serializeToString(obj);
        FileWriter fileWriter = new FileWriter(fileName);
        fileWriter.write(jsonString);
        fileWriter.close();
    }

    @Override
    public T deserializeFile(String fileName) throws Exception {
        Scanner scanner = new Scanner(new File(fileName));
        String fileContent = scanner.next();
        scanner.close();
        return deserializeString(fileContent);
    }
}
