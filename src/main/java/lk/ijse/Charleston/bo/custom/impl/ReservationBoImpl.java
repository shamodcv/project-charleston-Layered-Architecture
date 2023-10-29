package lk.ijse.Charleston.bo.custom.impl;

import lk.ijse.Charleston.bo.custom.ReservationBo;
import lk.ijse.Charleston.dao.DaoFactory;
import lk.ijse.Charleston.dao.custom.QueryDao;
import lk.ijse.Charleston.dao.custom.ReservationDao;
import lk.ijse.Charleston.dto.Reservation;
import lk.ijse.Charleston.entity.resavations;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationBoImpl implements ReservationBo {

    ReservationDao reservationDao = DaoFactory.getDaoFactory().getDao(DaoFactory.DaoType.RESERVATION);
    QueryDao queryDao = DaoFactory.getDaoFactory().getDao(DaoFactory.DaoType.QUERY);

    @Override
    public ArrayList<resavations> getAll() throws SQLException, ClassNotFoundException {
        return reservationDao.getAll();
    }

    @Override
    public boolean save(Reservation dto) throws SQLException, ClassNotFoundException {
        return reservationDao.save(new resavations(dto.getResId(),dto.getGusId(),dto.getRoomId(),dto.getRoomPrice(),dto.getCheckInDate(),dto.getCheckOutDate()));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return reservationDao.delete(id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return reservationDao.generateNewID();
    }

    @Override
    public List<String> loadIds() throws SQLException, ClassNotFoundException {
        return queryDao.loadIds();
    }
}
