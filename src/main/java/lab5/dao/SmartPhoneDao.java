package lab5.dao;

import lab5.exception.DaoException;
import lab5.model.SmartPhone;

import java.util.List;


public interface SmartPhoneDao extends Dao<SmartPhone> {

    List<SmartPhone> findAllWithNameLike(String name) throws DaoException;

}
