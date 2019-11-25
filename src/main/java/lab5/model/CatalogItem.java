package lab5.model;

import java.util.Objects;

/**
 * CatalogItem
 * Created to store smartphone, price and its count for each shop
 */
public class CatalogItem {

    private SmartPhone smartPhone;
    private Integer price;
    private Integer count;

    public SmartPhone getSmartPhone() {
        return smartPhone;
    }

    public void setSmartPhone(SmartPhone smartPhone) {
        this.smartPhone = smartPhone;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CatalogItem that = (CatalogItem) o;
        return Objects.equals(smartPhone, that.smartPhone) &&
                Objects.equals(price, that.price) &&
                Objects.equals(count, that.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(smartPhone, price, count);
    }
}
