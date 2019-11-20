package lab5.dao.jdbc;

import lab5.dao.ShopDao;
import lab5.model.Shop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ShopDaoJdbc extends JdbcDao<Shop> implements ShopDao {

    private static final String GET_ALL = "SELECT id, name, image FROM shops";
    private static final String GET_BY_ID = "SELECT id, name, image FROM shops WHERE id = ?";
    private static final String ADD = "INSERT INTO shops(name, image) VALUES (?,?)";
    private static final String UPDATE = "UPDATE shops SET name=?,image=? WHERE id=?";
    private static final String DELETE = "DELETE FROM shops WHERE id=?";

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
}
