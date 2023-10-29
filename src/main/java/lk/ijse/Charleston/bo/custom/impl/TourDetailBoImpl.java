package lk.ijse.Charleston.bo.custom.impl;

import lk.ijse.Charleston.bo.custom.TourDetailBo;
import lk.ijse.Charleston.dao.DaoFactory;
import lk.ijse.Charleston.dao.custom.TourDetailDao;
import lk.ijse.Charleston.dto.TourDetails;
import lk.ijse.Charleston.entity.tour_details;

import java.sql.SQLException;
import java.util.ArrayList;

public class TourDetailBoImpl implements TourDetailBo {

    TourDetailDao tourDetailDao = DaoFactory.getDaoFactory().getDao(DaoFactory.DaoType.TOURDETAIL);

    @Override
    public ArrayList<tour_details> getAll() throws SQLException, ClassNotFoundException {
        return tourDetailDao.getAll();
    }

    @Override
    public boolean save(TourDetails dto) throws SQLException, ClassNotFoundException {
        return tourDetailDao.save(new tour_details(dto.getBookID(),dto.getGuestID(),dto.getGuestName(),dto.getTourID(),dto.getTourName(),dto.getPrice()));
    }

    @Override
    public boolean update(TourDetails dto) throws SQLException, ClassNotFoundException {
        return tourDetailDao.update(new tour_details(dto.getBookID(),dto.getGuestID(),dto.getGuestName(),dto.getTourID(),dto.getTourName(),dto.getPrice()));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return tourDetailDao.delete(id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return tourDetailDao.generateNewID();
    }
}
