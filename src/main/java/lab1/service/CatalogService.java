package lab1.service;

import lab1.model.Catalog;
import lab1.model.SmartPhone;

import java.util.Comparator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class CatalogService {

    /**
     *
     * @param catalog catalog which sort
     * @param ascending direction of sorting (true - from Lower price to Higher, false - from Higher price to Lower)
     * @return new Sorted by price Map of all SmartPhones and its Count
     */
    public static SortedMap<SmartPhone, Integer> getGoodsSortedByPrice(Catalog catalog, boolean ascending) {

        SortedMap<SmartPhone, Integer> result = new TreeMap<>(new Comparator<SmartPhone>() {
            @Override
            public int compare(SmartPhone a, SmartPhone b) {
                int result = a.getPrice().compareTo(b.getPrice());
                if (!ascending) result *= -1;
                return result;
            }
        });

        result.putAll(catalog.getGoods());
        return result;
    }

    /**
     *
     * @param catalog catalog which sort
     * @param ascending direction of sorting (true - from Earlier date to Later, false - from Later date to Earlier)
     * @return new Sorted by date Map of all SmartPhones and its Count
     */
    public static SortedMap<SmartPhone, Integer> getGoodsSortedByReleaseDate(Catalog catalog, boolean ascending) {

        SortedMap<SmartPhone, Integer> result = new TreeMap<>(new Comparator<SmartPhone>() {
            @Override
            public int compare(SmartPhone a, SmartPhone b) {
                int result = a.getReleaseDate().compareTo(b.getReleaseDate());
                if (!ascending) result *= -1;
                return result;
            }
        });

        result.putAll(catalog.getGoods());
        return result;
    }


    /**
     * Searches goods with given name or part of name
     * @param catalog catalog where search
     * @param name searched name
     * @param fullMatch true - name must be equals to smartphone name, false - smartphone name can start with given name
     * @return new Catalog with goods
     */
    public static Catalog searchGoodsByName(Catalog catalog, String name, boolean fullMatch) {
        Catalog result = new Catalog();

        for (Map.Entry<SmartPhone, Integer> x : catalog.getGoods().entrySet()) {
            if (fullMatch)
            {
                if (x.getKey().getName().equals(name))
                    result.addGoodsItem(x.getKey(), x.getValue());
            }
            else if (x.getKey().getName().contains(name)) {
                result.addGoodsItem(x.getKey(), x.getValue());
            }
        }

        return result;
    }

    /**
     * Searches goods with given color
     * @param catalog catalog where search
     * @param color color
     * @return new Catalog with elements only given color
     */
    public static Catalog searchGoodsWithColor(Catalog catalog, SmartPhone.Color color) {
        Catalog result = new Catalog();

        for (Map.Entry<SmartPhone, Integer> x : catalog.getGoods().entrySet()) {
            if (x.getKey().getColor().equals(color))
                result.addGoodsItem(x.getKey(), x.getValue());
        }

        return result;
    }

    /**
     * Searches goods where Ram is in range from min to max
     * @param catalog catalog where search
     * @param minRam min ram (including min)
     * @param maxRam max ram (including max)
     * @return Catalog with goods
     */
    public static Catalog searchGoodsWithRam(Catalog catalog, Integer minRam, Integer maxRam) {
        Catalog result = new Catalog();

        for (Map.Entry<SmartPhone, Integer> x : catalog.getGoods().entrySet()) {
            if (x.getKey().getRam() >= minRam && x.getKey().getRam() <= maxRam)
                result.addGoodsItem(x.getKey(), x.getValue());
        }

        return result;
    }

    /**
     * Searches goods where Diagonal is in range from min to max
     * @param catalog catalog where search
     * @param minDiagonal min Diagonal (including min)
     * @param maxDiagonal max Diagonal (including max)
     * @return Catalog with goods
     */
    public static Catalog searchGoodsWithDiagonal(Catalog catalog, Integer minDiagonal, Integer maxDiagonal) {
        Catalog result = new Catalog();

        for (Map.Entry<SmartPhone, Integer> x : catalog.getGoods().entrySet()) {
            if (x.getKey().getDiagonal() >= minDiagonal && x.getKey().getDiagonal() <= maxDiagonal)
                result.addGoodsItem(x.getKey(), x.getValue());
        }

        return result;
    }

}
