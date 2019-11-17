package lab5.dao.jdbc;

import lab5.dao.Dao;
import lab5.exception.DaoException;
import lab5.model.SmartPhone;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public abstract class JdbcDao<T> implements Dao<T> {

    private Connection connection;

    public JdbcDao(Connection connection) {
        this.connection = connection;
    }

    protected Connection getConnection() {
        return connection;
    }

    protected abstract String getSelectAllQuery();
    protected abstract String getSelectOneQuery();
    protected abstract String getInsertQuery();
    protected abstract String getUpdateQuery();
    protected abstract String getDeleteQuery();

    protected abstract T fillFromResultSet(ResultSet rs) throws SQLException;

    protected abstract void fillPreparedStatementForInsert(PreparedStatement ps, T t) throws SQLException;
    protected abstract void fillPreparedStatementForUpdate(PreparedStatement ps, T t) throws SQLException;

    @Override
    public Optional<T> findById(Long id) throws DaoException {
        Optional<T> result = Optional.empty();
        Connection connection = getConnection();
        try (PreparedStatement ps = connection.prepareStatement(getSelectOneQuery())) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                T t = fillFromResultSet(rs);
                result = Optional.of(t);
            }

            rs.close();
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
        return result;
    }

    @Override
    public List<T> findAll() throws DaoException {
        List<T> result = new LinkedList<>();
        Connection connection = getConnection();
        try (PreparedStatement ps = connection.prepareStatement(getSelectAllQuery())) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                T t = fillFromResultSet(rs);
                result.add(t);
            }

            rs.close();
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
        return result;
    }

    @Override
    public Long insert(T t) throws DaoException {
        Long result = null;
        Connection connection = getConnection();
        try (PreparedStatement ps = connection.prepareStatement(getInsertQuery(), new String[]{"id"})) {
            fillPreparedStatementForInsert(ps, t);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                result = rs.getLong("id");
            }
            rs.close();
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
        return result;
    }

    @Override
    public void update(T t) throws DaoException {
        Connection connection = getConnection();
        try (PreparedStatement ps = connection.prepareStatement(getUpdateQuery())) {
            fillPreparedStatementForUpdate(ps, t);
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
    }

    @Override
    public void delete(Long id) throws DaoException {
        Connection connection = getConnection();
        try (PreparedStatement ps = connection.prepareStatement(getDeleteQuery())) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
    }



}
