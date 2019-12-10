package lab7.dto;

import lab7.model.Catalog;

import java.util.Set;

public class ShopDTO {

    private Long id;
    private String name;
    private String imageUrl;
    private Set<Catalog> catalogs;

    public ShopDTO() {
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Set<Catalog> getCatalogs() {
        return catalogs;
    }

    public void setCatalogs(Set<Catalog> catalogs) {
        this.catalogs = catalogs;
    }

    @Override
    public String toString() {
        return name + " " + imageUrl;
    }
}
