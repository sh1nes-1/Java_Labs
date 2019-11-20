package lab5.model;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Catalog
 * Contains some collection of Catalog Items
 * Created to solve problem of searching by multiple criteria (with CatalogService)
 */
public class Catalog implements Serializable {

    //todo: name, звязок в іншу сторону
    private Long id;
    private String name;
    private Shop shop;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Catalog catalog = (Catalog) o;
        return Objects.equals(id, catalog.id) &&
                name.equals(catalog.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return name;
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
