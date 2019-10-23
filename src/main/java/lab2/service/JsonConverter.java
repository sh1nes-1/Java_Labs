package lab2.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

public class JsonConverter<T extends Serializable> implements Converter<T> {

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

    //TODO: Move model from lab1 to lab2
    //TODO: optimize writing (use another method to write)
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
