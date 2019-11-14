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

    private Set<CatalogItem> catalogItems;

    public Catalog() {
        catalogItems = new HashSet<>();
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

    public boolean addGoodsItem(CatalogItem catalogItem) {
        return true;
    }

}
