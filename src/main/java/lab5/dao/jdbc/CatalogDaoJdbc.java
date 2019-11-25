package lab5.dao.jdbc;

import lab5.dao.CatalogDao;
import lab5.exception.DaoException;
import lab5.model.Catalog;
import lab5.model.CatalogItem;
import lab5.model.SmartPhone;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class CatalogDaoJdbc extends JdbcDao<Catalog> implements CatalogDao {

    // all from catalogs where catalogs_items
    private static final String GET_ALL = "SELECT id, name FROM catalogs";
    private static final String GET_BY_ID = "SELECT id, name FROM catalogs WHERE id = ?";
    private static final String ADD = "INSERT INTO catalogs (name, shop_id) VALUES (?, ?)";
    private static final String UPDATE = "UPDATE catalogs SET name=?,shop_id=? WHERE id = ?"; //міняти name і вказувати магазин
    private static final String DELETE = "DELETE FROM catalogs WHERE id = ?"; //видаляти і каталог ітеми
    private static final String GET_EAGER = "SELECT c.id AS \"c_id\", c.name AS \"c_name\", ci.smartphone_price AS \"ci_smartphone_price\", ci.smartphone_count AS \"ci_smartphone_count\", s.id AS \"s_id\", s.name AS \"s_name\", s.color AS \"s_color\", s.price AS \"s_price\", s.diagonal AS \"s_diagonal\", s.ram AS \"s_ram\", s.releasedate AS \"s_releasedate\" FROM catalogs c LEFT JOIN catalog_items ci ON c.id = ci.catalog_id LEFT JOIN smartphones s ON ci.smartphone_id = s.id WHERE c.id = ?";
    private static final String ADD_SMARTPHONE = "INSERT INTO catalog_items(catalog_id, smartphone_id, smartphone_price, smartphone_count) VALUES (?, ?, ?, ?)";
    private static final String DELETE_SMARTPHONE = "DELETE FROM catalog_items WHERE catalog_id = ? AND smartphone_id = ?";
    private static final String GET_SMARTPHONE_PRICE = "SELECT smartphone_price FROM catalog_items WHERE catalog_id = ? AND smartphone_id = ?";
    private static final String GET_SMARTPHONE_COUNT = "SELECT smartphone_count FROM catalog_items WHERE catalog_id = ? AND smartphone_id = ?";
    private static final String CHANGE_SMARTPHONE_PRICE = "UPDATE catalog_items SET smartphone_price = ? WHERE catalog_id = ? AND smartphone_id = ?";
    private static final String CHANGE_SMARTPHONE_COUNT = "UPDATE catalog_items SET smartphone_count = ? WHERE catalog_id = ? AND smartphone_id = ?";

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
        ps.setLong(2, catalog.getShop().getId());
    }

    @Override
    protected void fillPreparedStatementForUpdate(PreparedStatement ps, Catalog catalog) throws SQLException {
        ps.setString(1, catalog.getName());
        ps.setLong(2, catalog.getShop().getId());
        ps.setLong(3, catalog.getId());
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
                    SmartPhone smartPhone = new SmartPhone.Builder()
                            .setId(rs.getLong("s_id"))
                            .setName(rs.getString("s_name"))
                            .setColor(SmartPhone.Color.valueOf(rs.getString("s_color")))
                            .setPrice(rs.getInt("s_price"))
                            .setDiagonal(rs.getDouble("s_diagonal"))
                            .setReleaseDate(rs.getDate("s_releasedate").toLocalDate())
                            .setRam(rs.getInt("s_ram"))
                            .build();

                    catalog.addSmartPhone(smartPhone, rs.getInt("ci_smartphone_price"), rs.getInt("ci_smartphone_count"));
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
            int result = ps.executeUpdate();
            //TODO: ask if need this
            if (result > 0) catalog.addSmartPhone(smartPhone, price, count);
            return result;
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
            int result = ps.executeUpdate();
            if (result > 0) catalog.removeSmartPhone(smartPhone);
            return result;
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
            int result = ps.executeUpdate();
            if (result > 0) catalog.getSmartPhoneInfo(smartPhone).ifPresent(catalogItem -> catalogItem.setPrice(newPrice));
            return result;
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
            int result = ps.executeUpdate();
            if (result > 0) catalog.getSmartPhoneInfo(smartPhone).ifPresent(catalogItem -> catalogItem.setCount(newCount));
            return result;
        }
        catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
    }


}
