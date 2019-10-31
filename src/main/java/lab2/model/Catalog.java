package lab2.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lab2.service.catalog.CatalogDeserializer;
import lab2.service.catalog.CatalogSerializer;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@JsonSerialize(using = CatalogSerializer.class)
@JsonDeserialize(using = CatalogDeserializer.class)
public class Catalog implements Serializable {

    private Map<SmartPhone, Integer> availableGoods;

    public Catalog() {
        availableGoods = new HashMap<>();
    }

    /**
     *
     * @return Map of all SmartPhones and its Count
     */
    public Map<SmartPhone, Integer> getGoods() {
        return availableGoods;
    }

    /**
     *
     * @return Set of all SmartPhones
     */
    public Set<SmartPhone> getSmartPhones() { return availableGoods.keySet(); }

    /**
     * Adds NEW item to Catalog
     * @param smartPhone SmartPhone
     * @param count Integer
     * @return true if success, false if key already exists
     */
    public boolean addGoodsItem(SmartPhone smartPhone, Integer count) {
       if (count <= 0)
           throw new IllegalArgumentException("Count must be > 0");

       if (availableGoods.containsKey(smartPhone))
           return false;

       availableGoods.put(smartPhone, count);
       return true;
    }

    /**
     * Get count of some SmartPhone
     * @param smartPhone some SmartPhone that exists in Catalog
     * @return Integer count of such SmartPhones or null if not exists
     */
    public Integer getItemCount(SmartPhone smartPhone) {
        return availableGoods.get(smartPhone);
    }

    /**
     *
     * @param smartPhone SmartPhone
     * @param count value that will be added to count
     * @return true if success, false if such key not found
     */
    public boolean addItemCount(SmartPhone smartPhone, Integer count) {
        if (!availableGoods.containsKey(smartPhone))
            return false;

        availableGoods.put(smartPhone, availableGoods.get(smartPhone) + count);
        return true;
    }

    /**
     *
     * @param smartPhone SmartPhone
     * @param count value by which count will be reduced
     * @return true if success, false if such key not found
     */
    public boolean subItemCount(SmartPhone smartPhone, Integer count) {
        if (!availableGoods.containsKey(smartPhone))
            return false;

        if (availableGoods.get(smartPhone) < count)
            return false;

        availableGoods.put(smartPhone, availableGoods.get(smartPhone) - count);
        return true;
    }

    @Override
    public String toString() {
        return availableGoods.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Catalog catalog = (Catalog) o;
        return Objects.equals(availableGoods, catalog.availableGoods);
    }

    @Override
    public int hashCode() {
        return availableGoods != null ? availableGoods.hashCode() : 0;
    }
}
