package lab7.dao.jdbc;

import lab7.dao.CatalogDao;
import lab7.exception.DaoException;
import lab7.model.Catalog;
import lab7.model.Shop;
import lab7.model.SmartPhone;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class CatalogDaoJdbc extends JdbcDao<Catalog> implements CatalogDao {

    private static final String GET_ALL = "SELECT id, name FROM catalogs";
    private static final String GET_BY_ID = "SELECT id, name FROM catalogs WHERE id = ?";
    private static final String ADD = "INSERT INTO catalogs (name) VALUES (?)";
    private static final String UPDATE = "UPDATE catalogs SET name=? WHERE id = ?";
    private static final String DELETE = "DELETE FROM catalogs WHERE id = ?";
    private static final String GET_EAGER = "SELECT c.id AS \"c_id\", c.name AS \"c_name\", ci.smartphone_price AS \"ci_smartphone_price\", ci.smartphone_count AS \"ci_smartphone_count\", sm.id AS \"sm_id\", sm.name AS \"sm_name\", sm.color AS \"sm_color\", sm.price AS \"sm_price\", sm.diagonal AS \"sm_diagonal\", sm.ram AS \"sm_ram\", sm.releasedate AS \"sm_releasedate\" FROM catalogs c LEFT JOIN catalog_items ci ON c.id = ci.catalog_id LEFT JOIN smartphones sm ON ci.smartphone_id = sm.id WHERE c.id = ?";
    private static final String ADD_SMARTPHONE = "INSERT INTO catalog_items(catalog_id, smartphone_id, smartphone_price, smartphone_count) VALUES (?, ?, ?, ?)";
    private static final String DELETE_SMARTPHONE = "DELETE FROM catalog_items WHERE catalog_id = ? AND smartphone_id = ?";
    private static final String GET_SMARTPHONE_PRICE = "SELECT smartphone_price FROM catalog_items WHERE catalog_id = ? AND smartphone_id = ?";
    private static final String GET_SMARTPHONE_COUNT = "SELECT smartphone_count FROM catalog_items WHERE catalog_id = ? AND smartphone_id = ?";
    private static final String CHANGE_SMARTPHONE_PRICE = "UPDATE catalog_items SET smartphone_price = ? WHERE catalog_id = ? AND smartphone_id = ?";
    private static final String CHANGE_SMARTPHONE_COUNT = "UPDATE catalog_items SET smartphone_count = ? WHERE catalog_id = ? AND smartphone_id = ?";
    private static final String GET_SHOP = "SELECT s.id, s.name, s.image FROM catalogs c JOIN shops s ON c.shop_id = s.id WHERE c.id = ?";

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
    }

    @Override
    protected void fillPreparedStatementForUpdate(PreparedStatement ps, Catalog catalog) throws SQLException {
        ps.setString(1, catalog.getName());
        ps.setLong(2, catalog.getId());
    }

    @Override
    public Optional<Catalog> findByIdEager(Long id) throws DaoException {
        Optional<Catalog> result = Optional.empty();
        Connection connection = getConnection();
        try (PreparedStatement ps = connection.prepareStatement(GET_EAGER)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Catalog catalog = new Catalog();
                catalog.setId(rs.getLong("c_id"));
                catalog.setName(rs.getString("c_name"));

                do {
                    if (rs.getObject("sm_id") != null) {
                        SmartPhone smartPhone = new SmartPhone.Builder()
                                .setId(rs.getLong("sm_id"))
                                .setName(rs.getString("sm_name"))
                                .setColor(SmartPhone.Color.valueOf(rs.getString("sm_color")))
                                .setPrice(rs.getInt("sm_price"))
                                .setDiagonal(rs.getDouble("sm_diagonal"))
                                .setReleaseDate(rs.getDate("sm_releasedate").toLocalDate())
                                .setRam(rs.getInt("sm_ram"))
                                .build();

                        catalog.addSmartPhone(smartPhone, rs.getInt("ci_smartphone_price"), rs.getInt("ci_smartphone_count"));
                    }
                }
                while (rs.next());

                result = Optional.of(catalog);
            }

            rs.close();
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
        return result;
    }

    @Override
    public Optional<Shop> getShop(Catalog catalog) throws DaoException {
        Connection connection = getConnection();
        try (PreparedStatement ps = connection.prepareStatement(GET_SHOP)) {
            ps.setLong(1, catalog.getId());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Shop shop = new Shop(rs.getString("name"));
                shop.setId(rs.getLong("id"));
                shop.setImageUrl(rs.getString("image"));
                return Optional.of(shop);
            }
        }
        catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public Integer getSmartPhonePrice(Catalog catalog, SmartPhone smartPhone) throws DaoException {
        Connection connection = getConnection();
        try (PreparedStatement ps = connection.prepareStatement(GET_SMARTPHONE_PRICE)) {
            ps.setLong(1, catalog.getId());
            ps.setLong(2, smartPhone.getId());
            ResultSet rs = ps.executeQuery();
            Integer result = null;
            if (rs.next()) {
                result = rs.getInt("smartphone_price");
            }
            return result;
        }
        catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
    }

    @Override
    public Integer getSmartPhoneCount(Catalog catalog, SmartPhone smartPhone) throws DaoException {
        Connection connection = getConnection();
        try (PreparedStatement ps = connection.prepareStatement(GET_SMARTPHONE_COUNT)) {
            ps.setLong(1, catalog.getId());
            ps.setLong(2, smartPhone.getId());
            ResultSet rs = ps.executeQuery();
            Integer result = null;
            if (rs.next()) {
                result = rs.getInt("smartphone_count");
            }
            return result;
        }
        catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
    }

    @Override
    public int addSmartPhone(Catalog catalog, SmartPhone smartPhone, Integer price, Integer count) throws DaoException {
        Connection connection = getConnection();
        try (PreparedStatement ps = connection.prepareStatement(ADD_SMARTPHONE)) {
            ps.setLong(1, catalog.getId());
            ps.setLong(2, smartPhone.getId());
            ps.setInt(3, price);
            ps.setInt(4, count);
            return ps.executeUpdate();
        }
        catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
    }

    @Override
    public int removeSmartPhone(Catalog catalog, SmartPhone smartPhone) throws DaoException {
        Connection connection = getConnection();
        try (PreparedStatement ps = connection.prepareStatement(DELETE_SMARTPHONE)) {
            ps.setLong(1, catalog.getId());
            ps.setLong(2, smartPhone.getId());
            return ps.executeUpdate();
        }
        catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
    }

    @Override
    public int changeSmartPhonePrice(Catalog catalog, SmartPhone smartPhone, Integer newPrice) throws DaoException {
        Connection connection = getConnection();
        try (PreparedStatement ps = connection.prepareStatement(CHANGE_SMARTPHONE_PRICE)) {
            ps.setInt(1, newPrice);
            ps.setLong(2, catalog.getId());
            ps.setLong(3, smartPhone.getId());
            return ps.executeUpdate();
        }
        catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
    }

    @Override
    public int changeSmartPhoneCount(Catalog catalog, SmartPhone smartPhone, Integer newCount) throws DaoException {
        Connection connection = getConnection();
        try (PreparedStatement ps = connection.prepareStatement(CHANGE_SMARTPHONE_COUNT)) {
            ps.setInt(1, newCount);
            ps.setLong(2, catalog.getId());
            ps.setLong(3, smartPhone.getId());
            return ps.executeUpdate();
        }
        catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
    }


}
