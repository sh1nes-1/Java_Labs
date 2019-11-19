package lab5.dao.jdbc;

import lab5.dao.CatalogDao;
import lab5.exception.DaoException;
import lab5.model.Catalog;
import lab5.model.CatalogItem;
import lab5.model.SmartPhone;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class CatalogDaoJdbc extends JdbcDao<Catalog> implements CatalogDao {

    private static final String GET_ALL = "SELECT ci.catalog_id, ci.smartphone_id, ci.smartphone_price, ci.smartphone_count FROM catalog_items ci";
    private static final String GET_BY_ID = "SELECT ci.catalog_id, ci.smartphone_id, ci.smartphone_price, ci.smartphone_count FROM catalog_items ci WHERE ci.catalog_id = ?";
    private static final String ADD_CATALOG = "INSERT INTO catalogs VALUES (default)";
    private static final String ADD_ITEM = "INSERT INTO catalog_items (catalog_id, smartphone_id, smartphone_price, smartphone_count) VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "";
    private static final String DELETE = "";

    public CatalogDaoJdbc(Connection connection) {
        super(connection);
    }

    @Override
    protected String getSelectAllQuery() { return GET_ALL; }

    @Override
    protected String getSelectOneQuery() { return GET_BY_ID; }

    @Override
    protected String getInsertQuery() { return ADD_CATALOG; }

    @Override
    protected String getUpdateQuery() { return UPDATE; }

    @Override
    protected String getDeleteQuery() { return DELETE; }

    @Override
    public Long insert(Catalog catalog) throws DaoException {
        Long id = super.insert(catalog);

        for (CatalogItem catalogItem : catalog.getCatalogItems()) {

        }

        return id;
    }

    @Override
    protected List<Catalog> fillFromResultSet(ResultSet rs) throws SQLException {
        List<Catalog> result = new LinkedList<>();

        while (rs.next()) {
            Long id = rs.getLong("catalog_id");

            Catalog catalog = result.stream()
                    .filter((c) -> c.getId().equals(id))
                    .findAny()
                    .orElseGet(() -> {
                        Catalog newCatalog = new Catalog();
                        newCatalog.setId(id);
                        return newCatalog;
                    });

            SmartPhoneDaoJdbc smartPhoneDaoJdbc = new SmartPhoneDaoJdbc(getConnection());
            try {
                Optional<SmartPhone> searchResult = smartPhoneDaoJdbc.findById(rs.getLong("smartphone_id"));
                if (searchResult.isEmpty())
                    throw new Exception("Can not find smartPhone with such id!");
                catalog.addGoodsItem(searchResult.get(), rs.getInt("smartphone_price"), rs.getInt("smartphone_count"));
            } catch (Exception ex) {
                throw new SQLException(ex.getMessage());
            }

            result.add(catalog);
        }

        return result;
    }


    @Override
    protected void fillPreparedStatementForInsert(PreparedStatement ps, Catalog catalog) throws SQLException {

    }

    @Override
    protected void fillPreparedStatementForUpdate(PreparedStatement ps, Catalog catalog) throws SQLException {

    }

}
