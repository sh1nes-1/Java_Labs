package lab7.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Shop {

    private Long id;
    private String name;
    private String imageUrl;
    private Set<Catalog> catalogs;

    public Shop(String name) {
        this.name = name;
        catalogs = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shop shop = (Shop) o;
        return name.equals(shop.name) &&
                Objects.equals(imageUrl, shop.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, imageUrl);
    }

    @Override
    public String toString() {
        return name + " " + imageUrl;
    }

    public boolean addCatalog(Catalog catalog) {
        return catalogs.add(catalog);
    }

    public boolean removeCatalog(Catalog catalog) {
        return catalogs.remove(catalog);
    }
}
