package lab7.service;

import lab7.connection.ConnectionFactory;
import lab7.dao.SmartPhoneDao;
import lab7.dao.jdbc.SmartPhoneDaoJdbc;
import lab7.dto.SmartPhoneDTO;
import lab7.exception.DaoException;
import lab7.exception.DatabaseConnectionException;
import lab7.exception.ServiceException;
import lab7.mapper.SmartPhoneMapper;
import lab7.model.SmartPhone;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class SmartPhoneService {

    private SmartPhoneDao smartPhoneDao;

    public SmartPhoneService() throws ServiceException {
        try {
            Connection connection = ConnectionFactory.getConnectionBuilder().getConnection();
            smartPhoneDao = new SmartPhoneDaoJdbc(connection);
        } catch (DatabaseConnectionException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public Optional<SmartPhoneDTO> findById(Long id) throws ServiceException {
        try {
            Optional<SmartPhoneDTO> result = Optional.empty();
            Optional<SmartPhone> optionalSmartPhone = smartPhoneDao.findById(id);
            if (optionalSmartPhone.isPresent()) {
                SmartPhoneDTO smartPhoneDTO = SmartPhoneMapper.INSTANCE.getDto(optionalSmartPhone.get());
                result = Optional.of(smartPhoneDTO);
            }
            return result;
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public List<SmartPhoneDTO> findAll() throws ServiceException {
        try {
            return SmartPhoneMapper.INSTANCE.getDtoList(smartPhoneDao.findAll());
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public Long insert(SmartPhoneDTO smartPhoneDto) throws ServiceException {
        try {
            SmartPhone smartPhone = SmartPhoneMapper.INSTANCE.getSmartPhone(smartPhoneDto);
            return smartPhoneDao.insert(smartPhone);
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public int update(SmartPhoneDTO smartPhoneDto) throws ServiceException {
        try {
            SmartPhone smartPhone = SmartPhoneMapper.INSTANCE.getSmartPhone(smartPhoneDto);
            return smartPhoneDao.update(smartPhone);
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public int delete(Long id) throws ServiceException {
        try {
            return smartPhoneDao.delete(id);
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }

    public List<SmartPhoneDTO> findAllWithNameLike(String name) throws ServiceException {
        try {
            return SmartPhoneMapper.INSTANCE.getDtoList(smartPhoneDao.findAllWithNameLike(name));
        } catch (DaoException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }
}
