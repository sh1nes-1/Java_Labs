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

// interface SmartPhoneDao
public class SmartPhoneDaoJdbc implements Dao<SmartPhone> {

    private static final String SQL_SELECT_ALL = "SELECT id, name, price, releaseDate, color, ram, diagonal FROM smartphones;";
    private static final String SQL_SELECT_BY_ID = "SELECT id, name, price, releaseDate, color, ram, diagonal FROM smartphones WHERE id = ?;";
    private static final String SQL_INSERT = "INSERT INTO smartphones (id, name, price, releaseDate, color, ram, diagonal) VALUES (?, ?, ?, ?, ?, ?, ?);";
    private static final String SQL_UPDATE = "UPDATE smartphones SET id=?, name=?, price=?, releaseDate=?, color=?, ram=?, diagonal=? WHERE id=?;";
    private static final String SQL_DELETE = "DELETE FROM smartphones WHERE id=?;";

    private ConnectionBuilder connectionBuilder;

    private Connection getConnection() throws DatabaseConnectionException {
        if (connectionBuilder == null) connectionBuilder = ConnectionFactory.getConnectionBuilder();
        return connectionBuilder.getConnection();
    }

    @Override
    public Optional<SmartPhone> findById(int id) throws DaoException {
        Optional<SmartPhone> result = Optional.empty();
        try (Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            ps.setInt(1, id);
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
        try (Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement(SQL_SELECT_ALL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                SmartPhone smartPhone = fillSmartPhone(rs);
                result.add(smartPhone);
            }

            rs.close();
            ps.close();
        } catch (DatabaseConnectionException | SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
        return result;
    }

    @Override
    public void insert(SmartPhone smartPhone) throws DaoException {
        try (Connection connection = getConnection()) { // here move to try
            PreparedStatement ps = connection.prepareStatement(SQL_INSERT);
            //ps.setInt(1, smartPhone.getId());
            ps.setString(2, smartPhone.getName());
            ps.setInt(3, smartPhone.getPrice());
            ps.setDate(4, Date.valueOf(smartPhone.getReleaseDate()));
            ps.setString(5, smartPhone.getColor().toString());
            ps.setInt(6, smartPhone.getRam());
            ps.setDouble(7, smartPhone.getDiagonal());
            ps.executeUpdate();
            ps.close();
        } catch (DatabaseConnectionException | SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
    }

    @Override
    public void update(SmartPhone smartPhone) throws DaoException {
        try (Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement(SQL_UPDATE);
            //ps.setInt(1, smartPhone.getId());
            ps.setString(2, smartPhone.getName());
            ps.setInt(3, smartPhone.getPrice());
            ps.setDate(4, Date.valueOf(smartPhone.getReleaseDate()));
            ps.setString(5, smartPhone.getColor().toString());
            ps.setInt(6, smartPhone.getRam());
            ps.setDouble(7, smartPhone.getDiagonal());

            ps.setInt(8, smartPhone.getId());

            ps.executeUpdate();
            ps.close();
        } catch (DatabaseConnectionException | SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
    }

    @Override
    public void delete(int id) throws DaoException {
        try (Connection connection = getConnection()) {
            PreparedStatement ps = connection.prepareStatement(SQL_DELETE);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (DatabaseConnectionException | SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
    }

    private SmartPhone fillSmartPhone(ResultSet rs) throws SQLException {
        return new SmartPhone.Builder()
                .setId(rs.getInt("id"))
                .setName(rs.getString("name"))
                .setPrice(rs.getInt("price"))
                .setReleaseDate(rs.getDate("releaseDate").toLocalDate())
                .setColor(SmartPhone.Color.valueOf(rs.getString("color")))
                .setRam(rs.getInt("ram"))
                .setDiagonal(rs.getDouble("diagonal"))
                .build();
    }

}
