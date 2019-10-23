package lab2.service;

import java.io.Serializable;

public interface Converter<T extends Serializable> {

    public String serializeToString(T obj) throws Exception;

    public T deserializeString(String str) throws Exception;

    public void serializeToFile(T obj, String fileName) throws Exception;

    public T deserializeFile(String fileName) throws Exception;

}
