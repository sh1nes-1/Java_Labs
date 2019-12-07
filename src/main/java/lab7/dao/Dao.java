package lab7.dao;

import lab7.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    Optional<T> findById(Long id) throws DaoException;

    // lazy
    List<T> findAll() throws DaoException;

    /**
     * @return generated id
     */
    Long insert(T t) throws DaoException;

    int update(T t) throws DaoException;

    int delete(Long id) throws DaoException;

}
