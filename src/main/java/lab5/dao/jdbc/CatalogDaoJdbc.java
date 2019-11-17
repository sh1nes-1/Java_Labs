package lab5.dao.jdbc;

import lab5.dao.CatalogDao;
import lab5.exception.DaoException;
import lab5.model.Catalog;

import java.util.List;
import java.util.Optional;

public class CatalogDaoJdbc implements CatalogDao {
    @Override
    public Optional<Catalog> findById(Long id) throws DaoException {
        return Optional.empty();
    }

    @Override
    public List<Catalog> findAll() throws DaoException {
        return null;
    }

    @Override
    public Long insert(Catalog catalog) throws DaoException {
        return null;
    }

    @Override
    public void update(Catalog catalog) throws DaoException {

    }

    @Override
    public void delete(Long id) throws DaoException {

    }
}
