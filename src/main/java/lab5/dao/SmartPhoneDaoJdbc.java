package lab5.dao;

import lab5.exception.DaoException;
import lab5.exception.DatabaseConnectionException;
import lab5.model.SmartPhone;
import lab5.connection.ConnectionBuilder;
import lab5.connection.ConnectionFactory;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class SmartPhoneDaoJdbc implements SmartPhoneDao {

    private static final String GET_ALL = "SELECT id, name, price, releaseDate, color, ram, diagonal FROM smartphones;";
    private static final String GET_BY_ID = "SELECT id, name, price, releaseDate, color, ram, diagonal FROM smartphones WHERE id = ?;";
    private static final String ADD = "INSERT INTO smartphones (name, price, releaseDate, color, ram, diagonal) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String UPDATE = "UPDATE smartphones SET name=?, price=?, releaseDate=?, color=?, ram=?, diagonal=? WHERE id=?;";
    private static final String DELETE = "DELETE FROM smartphones WHERE id=?;";

    private ConnectionBuilder connectionBuilder;

    private Connection getConnection() throws DatabaseConnectionException {
        if (connectionBuilder == null) connectionBuilder = ConnectionFactory.getConnectionBuilder();
        return connectionBuilder.getConnection();
    }

    @Override
    public Optional<SmartPhone> findById(Long id) throws DaoException {
        Optional<SmartPhone> result = Optional.empty();
        try (Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(GET_BY_ID)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                SmartPhone smartPhone = fillSmartPhone(rs);
                result = Optional.of(smartPhone);
            }

            rs.close();
        } catch (DatabaseConnectionException | SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
        return result;
    }

    @Override
    public List<SmartPhone> findAll() throws DaoException {
        List<SmartPhone> result = new LinkedList<>();
        try (Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(GET_ALL)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                SmartPhone smartPhone = fillSmartPhone(rs);
                result.add(smartPhone);
            }

            rs.close();
        } catch (DatabaseConnectionException | SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
        return result;
    }

    @Override
    public Long insert(SmartPhone smartPhone) throws DaoException {
        Long result = null;
        try (Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(ADD, new String[]{"id"})) {
            fillPreparedStatement(ps, 1, smartPhone);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                result = rs.getLong("id");
            }
            rs.close();
        } catch (DatabaseConnectionException | SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
        return result;
    }

    @Override
    public void update(SmartPhone smartPhone) throws DaoException {
        try (Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(UPDATE)) {
            int counter = fillPreparedStatement(ps, 1, smartPhone);
            ps.setLong(counter, smartPhone.getId());
            ps.executeUpdate();
        } catch (DatabaseConnectionException | SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
    }

    @Override
    public void delete(Long id) throws DaoException {
        try (Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(DELETE)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (DatabaseConnectionException | SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
    }

    /**
     * Gets smartPhone from ResultSet
     *
     * @param rs ResultSet
     * @return new created smartPhone from given resultSet
     * @throws SQLException if can't get some field
     */
    private SmartPhone fillSmartPhone(ResultSet rs) throws SQLException {
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
        ps.setDouble(counter++, smartPhone.getDiagonal());
        return counter;
    }

}
