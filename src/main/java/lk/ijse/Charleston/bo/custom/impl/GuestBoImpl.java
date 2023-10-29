package lk.ijse.Charleston.bo.custom.impl;

import lk.ijse.Charleston.bo.custom.GuestBo;
import lk.ijse.Charleston.dao.DaoFactory;
import lk.ijse.Charleston.dao.custom.GuestDao;
import lk.ijse.Charleston.dto.Guest;
import lk.ijse.Charleston.entity.guest;

import java.sql.SQLException;
import java.util.ArrayList;

public class GuestBoImpl implements GuestBo {

    GuestDao guestDao = DaoFactory.getDaoFactory().getDao(DaoFactory.DaoType.GUEST);

    @Override
    public ArrayList<guest> getAll() throws SQLException, ClassNotFoundException {
        return guestDao.getAll();
    }

    @Override
    public boolean save(Guest dto) throws SQLException, ClassNotFoundException {
        return guestDao.save(new guest(dto.getIdType(),dto.getId(),dto.getName(),dto.getContactNo(),dto.getCountry(),dto.getEmail()));
    }

    @Override
    public boolean update(Guest dto) throws SQLException, ClassNotFoundException {
        return guestDao.update(new guest(dto.getIdType(),dto.getId(),dto.getName(),dto.getContactNo(),dto.getCountry(),dto.getEmail()));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return guestDao.delete(id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public guest search(String id) throws SQLException, ClassNotFoundException {
        return guestDao.search(id);
    }
}
