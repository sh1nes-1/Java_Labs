package lab5.dao.jdbc;

import lab5.dao.CatalogDao;
import lab5.model.Catalog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class CatalogDaoJdbc extends JdbcDao<Catalog> implements CatalogDao {

    // all from catalogs where catalogs_items
    private static final String GET_ALL = "SELECT id, name FROM catalogs";
    private static final String GET_BY_ID = "SELECT id, name FROM catalogs WHERE id = ?";
    private static final String ADD = "INSERT INTO catalogs (name, shop_id) VALUES (?, ?)";
    private static final String UPDATE = "UPDATE catalogs SET name=?,shop_id=? WHERE id = ?"; //міняти name і вказувати магазин
    private static final String DELETE = "DELETE FROM catalogs WHERE id = ?"; //видаляти і каталог ітеми

    public CatalogDaoJdbc(Connection connection) {
        super(connection);
    }

    @Override
    protected String getSelectAllQuery() { return GET_ALL; }

    @Override
    protected String getSelectOneQuery() { return GET_BY_ID; }

    @Override
    protected String getInsertQuery() { return ADD; }

    @Override
    protected String getUpdateQuery() { return UPDATE; }

    @Override
    protected String getDeleteQuery() { return DELETE; }

    @Override
    protected List<Catalog> fillFromResultSet(ResultSet rs) throws SQLException {
        List<Catalog> result = new LinkedList<>();

        while (rs.next()) {
            Catalog catalog = new Catalog();
            catalog.setId(rs.getLong("id"));
            catalog.setName(rs.getString("name"));
            result.add(catalog);
        }

        return result;
    }

    @Override
    protected void fillPreparedStatementForInsert(PreparedStatement ps, Catalog catalog) throws SQLException {
        ps.setString(1, catalog.getName());
        // TODO: if need check for null
        ps.setLong(2, catalog.getShop().getId());
    }

    @Override
    protected void fillPreparedStatementForUpdate(PreparedStatement ps, Catalog catalog) throws SQLException {
        ps.setString(1, catalog.getName());
        // TODO: if need check for null
        ps.setLong(2, catalog.getShop().getId());
        // TODO: if need check for null
        ps.setLong(3, catalog.getId());
    }

}
