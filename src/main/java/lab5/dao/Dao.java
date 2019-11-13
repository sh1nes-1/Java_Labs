package lab5.dao;

import lab5.exception.DaoException;

import java.util.List;
import java.util.Optional;

//TODO: smartPhone setters
//TODO: in service?
public interface Dao<T> {

    Optional<T> findById(int id) throws DaoException;

    List<T> findAll() throws DaoException;

    void insert(T t) throws DaoException;

    //TODO: think about update for immutable
    void update(T t) throws DaoException;

    void delete(int id) throws DaoException;

}
