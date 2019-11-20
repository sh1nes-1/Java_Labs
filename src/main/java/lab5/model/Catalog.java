package lab5.model;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

// TODO:
// дозволяє змінити кількість
// ціна для магазину

/**
 * Catalog
 * Contains some collection of Catalog Items
 * Created to solve problem of searching by multiple criteria (with CatalogService)
 */
public class Catalog implements Serializable {

    //todo: name, звязок в іншу сторону
    private Long id;
    Shop shop;
    private Set<CatalogItem> catalogItems;

    public Catalog() {
        catalogItems = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    /**
     * @return Set of all catalog items
     */
    public Set<CatalogItem> getCatalogItems() {
        return catalogItems;
    }

    /**
     * @return Set of all SmartPhones
     */
    public Set<SmartPhone> getSmartPhones() {
        return catalogItems.stream()
                .map(CatalogItem::getSmartPhone)
                .collect(Collectors.toSet());
    }


    public boolean addGoodsItem(SmartPhone smartPhone, Integer price, Integer count) {
        if (getSmartPhones().contains(smartPhone))
            return false;

        CatalogItem catalogItem = new CatalogItem();
        catalogItem.setSmartPhone(smartPhone);
        catalogItem.setCount(count);
        catalogItem.setPrice(price);

        return catalogItems.add(catalogItem);
    }

    public Optional<CatalogItem> getGoodsItem(SmartPhone smartPhone) {
        return catalogItems.stream()
                .filter(e -> e.getSmartPhone().equals(smartPhone))
                .findAny();
    }

}
