package lab5.dao;

import lab5.model.Catalog;

public interface CatalogDao extends Dao<Catalog> {
    // отриимати всі каталоги разом з Set всередині за доп одного sql запиту
    // забрати id з таблиці catalog_items
    // додати айтем
    // видалити айтем
    // змінити кількість смартфонів в CatalogItem тут якщо заберу id
}
