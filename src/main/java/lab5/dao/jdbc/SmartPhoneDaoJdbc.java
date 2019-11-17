package lab5.dao.jdbc;

import lab5.dao.SmartPhoneDao;
import lab5.model.SmartPhone;

import java.sql.*;
public class SmartPhoneDaoJdbc extends JdbcDao<SmartPhone> implements SmartPhoneDao {

    private static final String GET_ALL = "SELECT id, name, price, releaseDate, color, ram, diagonal FROM smartphones;";
    private static final String GET_BY_ID = "SELECT id, name, price, releaseDate, color, ram, diagonal FROM smartphones WHERE id = ?;";
    private static final String ADD = "INSERT INTO smartphones (name, price, releaseDate, color, ram, diagonal) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String UPDATE = "UPDATE smartphones SET name=?, price=?, releaseDate=?, color=?, ram=?, diagonal=? WHERE id=?;";
    private static final String DELETE = "DELETE FROM smartphones WHERE id=?;";

    public SmartPhoneDaoJdbc(Connection connection) {
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

    /**
     * Gets smartPhone from ResultSet
     *
     * @param rs ResultSet
     * @return new created smartPhone from given resultSet
     * @throws SQLException if can't get some field
     */
    @Override
    protected SmartPhone fillFromResultSet(ResultSet rs) throws SQLException {
        return new SmartPhone.Builder()
                .setId(rs.getLong("id"))
                .setName(rs.getString("name"))
                .setPrice(rs.getInt("price"))
                .setReleaseDate(rs.getDate("releaseDate").toLocalDate())
                .setColor(SmartPhone.Color.valueOf(rs.getString("color")))
                .setRam(rs.getInt("ram"))
                .setDiagonal(rs.getDouble("diagonal"))
                .build();
    }

    @Override
    protected void fillPreparedStatementForInsert(PreparedStatement ps, SmartPhone smartPhone) throws SQLException {
        fillPreparedStatement(ps, 1, smartPhone);
    }

    @Override
    protected void fillPreparedStatementForUpdate(PreparedStatement ps, SmartPhone smartPhone) throws SQLException {
        int counter = fillPreparedStatement(ps, 1, smartPhone);
        ps.setLong(++counter, smartPhone.getId());
    }

    /**
     * Puts given smartPhone into PreparedStament
     *
     * @param ps preparedStatement
     * @param counter counter from what starts
     * @param smartPhone value that must be filled
     * @return new counter
     * @throws SQLException if something went wrong
     */
    private int fillPreparedStatement(PreparedStatement ps, int counter, SmartPhone smartPhone) throws SQLException {
        ps.setString(counter++, smartPhone.getName());
        ps.setInt(counter++, smartPhone.getPrice());
        ps.setDate(counter++, Date.valueOf(smartPhone.getReleaseDate()));
        ps.setString(counter++, smartPhone.getColor().toString());
        ps.setInt(counter++, smartPhone.getRam());
        ps.setDouble(counter, smartPhone.getDiagonal());
        return counter;
    }

}
