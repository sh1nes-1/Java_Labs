package lab7.dto;

import lab7.model.CatalogItem;

import java.util.Set;

public class CatalogDTO {

    private Long id;
    private String name;
    private Set<CatalogItem> catalogItems;

    public CatalogDTO() {
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

    public Set<CatalogItem> getCatalogItems() {
        return catalogItems;
    }

    public void setCatalogItems(Set<CatalogItem> catalogItems) {
        this.catalogItems = catalogItems;
    }
}
