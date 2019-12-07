package lab7.dao;

import lab7.exception.DaoException;
import lab7.model.SmartPhone;

import java.util.List;


public interface SmartPhoneDao extends Dao<SmartPhone> {

    List<SmartPhone> findAllWithNameLike(String name) throws DaoException;

}
