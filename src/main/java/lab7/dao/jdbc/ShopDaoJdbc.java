package lab7.dao.jdbc;

import lab7.dao.ShopDao;
import lab7.exception.DaoException;
import lab7.model.Catalog;
import lab7.model.Shop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class ShopDaoJdbc extends JdbcDao<Shop> implements ShopDao {

    private static final String GET_ALL = "SELECT id, name, image FROM shops";
    private static final String GET_BY_ID = "SELECT id, name, image FROM shops WHERE id = ?";
    private static final String ADD = "INSERT INTO shops(name, image) VALUES (?,?)";
    private static final String UPDATE = "UPDATE shops SET name=?,image=? WHERE id=?";
    private static final String DELETE = "DELETE FROM shops WHERE id=?";
    private static final String ADD_CATALOG = "UPDATE catalogs SET shop_id = ? WHERE id = ?";
    private static final String REMOVE_CATALOG = "UPDATE catalogs SET shop_id = NULL WHERE shop_id = ? AND id = ?";
    private static final String GET_CATALOGS = "SELECT id, name FROM catalogs WHERE shop_id = ?";

    public ShopDaoJdbc(Connection connection) {
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
    protected List<Shop> fillFromResultSet(ResultSet rs) throws SQLException {
        List<Shop> result = new LinkedList<>();

        while (rs.next()) {
            Shop shop = new Shop(rs.getString("name"));
            shop.setId(rs.getLong("id"));
            shop.setImageUrl(rs.getString("image"));
            result.add(shop);
        }

        return result;
    }

    @Override
    protected void fillPreparedStatementForInsert(PreparedStatement ps, Shop shop) throws SQLException {
        ps.setString(1, shop.getName());
        ps.setString(2, shop.getImageUrl());
    }

    @Override
    protected void fillPreparedStatementForUpdate(PreparedStatement ps, Shop shop) throws SQLException {
        ps.setString(1, shop.getName());
        ps.setString(2, shop.getImageUrl());
        ps.setLong(3, shop.getId());
    }

    @Override
    public int addCatalog(Shop shop, Catalog catalog) throws DaoException {
        Connection connection = getConnection();
        try (PreparedStatement ps = connection.prepareStatement(ADD_CATALOG)) {
            ps.setLong(1, shop.getId());
            ps.setLong(2, catalog.getId());
            return ps.executeUpdate();
        }
        catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
    }

    @Override
    public int removeCatalog(Shop shop, Catalog catalog) throws DaoException {
        Connection connection = getConnection();
        try (PreparedStatement ps = connection.prepareStatement(REMOVE_CATALOG)) {
            ps.setLong(1, shop.getId());
            ps.setLong(2, catalog.getId());
            return ps.executeUpdate();
        }
        catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
    }

    @Override
    public Set<Catalog> getCatalogs(Shop shop) throws DaoException {
        Set<Catalog> result = new HashSet<>();
        Connection connection = getConnection();
        try (PreparedStatement ps = connection.prepareStatement(GET_CATALOGS)) {
            ps.setLong(1, shop.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Catalog catalog = new Catalog();
                catalog.setId(rs.getLong("id"));
                catalog.setName(rs.getString("name"));
                result.add(catalog);
            }
            rs.close();
        }
        catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
        return result;
    }


}
