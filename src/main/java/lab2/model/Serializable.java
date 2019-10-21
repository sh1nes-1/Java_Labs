package lab2.model;

// Same as Serializable<T extends Object>
public interface Serializable<T> {

    public String serialize(T obj);

    public T deserialize(String str);

}
