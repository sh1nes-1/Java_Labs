package lab5.model;

/**
 * CatalogItem
 * Created to store smartphone, price and its count for each shop
 */
public class CatalogItem {

    // TODO: Dao тому що якщо ми захочемо поміняти ціну чи кількість

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

}
