package lab3.service;

import lab3.model.Catalog;
import lab3.model.SmartPhone;

import java.util.SortedMap;
import java.util.TreeMap;

public class CatalogService {

    private Catalog catalog;

    public CatalogService(Catalog catalog) {
        this.catalog = catalog;
    }

    /**
     * @param ascending direction of sorting (true - from Lower price to Higher, false - from Higher price to Lower)
     * @return new Sorted by price Map of all SmartPhones and its Count
     */
    public SortedMap<SmartPhone, Integer> getGoodsSortedByPrice(boolean ascending) {

        SortedMap<SmartPhone, Integer> result = new TreeMap<>((a, b) ->
                ascending ? a.getPrice().compareTo(b.getPrice()) : b.getPrice().compareTo(a.getPrice())
        );

        result.putAll(catalog.getGoods());
        return result;
    }

    /**
     * @param ascending direction of sorting (true - from Earlier date to Later, false - from Later date to Earlier)
     * @return new Sorted by date Map of all SmartPhones and its Count
     */
    public SortedMap<SmartPhone, Integer> getGoodsSortedByReleaseDate(boolean ascending) {

        SortedMap<SmartPhone, Integer> result = new TreeMap<>((a, b) ->
                ascending ? a.getReleaseDate().compareTo(b.getReleaseDate()) : b.getReleaseDate().compareTo(a.getReleaseDate())
        );

        result.putAll(catalog.getGoods());
        return result;
    }


    /**
     * Searches goods with given name or part of name
     *
     * @param name      searched name
     * @param fullMatch true - name must be equals to smartphone name, false - smartphone name can start with given name
     * @return new Catalog with goods
     */
    public Catalog searchGoodsByName(String name, boolean fullMatch) {
        Catalog result = new Catalog();

        catalog.getGoods().entrySet().stream()
                .filter(e -> fullMatch ? e.getKey().getName().equals(name) : e.getKey().getName().contains(name))
                .forEach(e -> result.addGoodsItem(e.getKey(), e.getValue()));

        return result;
    }

    /**
     * Searches goods with given color
     *
     * @param color color
     * @return new Catalog with elements only given color
     */
    public Catalog searchGoodsWithColor(SmartPhone.Color color) {
        Catalog result = new Catalog();

        catalog.getGoods().entrySet().stream()
                .filter(e -> e.getKey().getColor().equals(color))
                .forEach(e -> result.addGoodsItem(e.getKey(), e.getValue()));

        return result;
    }

    /**
     * Searches goods where Ram is in range from min to max
     *
     * @param minRam min ram (including min)
     * @param maxRam max ram (including max)
     * @return Catalog with goods
     */
    public Catalog searchGoodsWithRam(Integer minRam, Integer maxRam) {
        Catalog result = new Catalog();

        catalog.getGoods().entrySet().stream()
                .filter(e -> e.getKey().getRam() >= minRam && e.getKey().getRam() <= maxRam)
                .forEach(e -> result.addGoodsItem(e.getKey(), e.getValue()));

        return result;
    }

    /**
     * Searches goods where Diagonal is in range from min to max
     *
     * @param minDiagonal min Diagonal (including min)
     * @param maxDiagonal max Diagonal (including max)
     * @return Catalog with goods
     */
    public Catalog searchGoodsWithDiagonal(Integer minDiagonal, Integer maxDiagonal) {
        Catalog result = new Catalog();

        catalog.getGoods().entrySet().stream()
                .filter(e -> e.getKey().getDiagonal() >= minDiagonal && e.getKey().getDiagonal() <= maxDiagonal)
                .forEach(e -> result.addGoodsItem(e.getKey(), e.getValue()));

        return result;
    }

}
