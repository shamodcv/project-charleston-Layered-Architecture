package lk.ijse.Charleston.bo.custom.impl;

import lk.ijse.Charleston.bo.custom.TourModelBo;
import lk.ijse.Charleston.dao.DaoFactory;
import lk.ijse.Charleston.dao.custom.QueryDao;
import lk.ijse.Charleston.dao.custom.TourModelDao;
import lk.ijse.Charleston.dto.Tour;
import lk.ijse.Charleston.entity.tour;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TourModelBoImpl implements TourModelBo {

    TourModelDao tourModelDao = DaoFactory.getDaoFactory().getDao(DaoFactory.DaoType.TOURMODEL);
    QueryDao queryDao = DaoFactory.getDaoFactory().getDao(DaoFactory.DaoType.QUERY);

    @Override
    public ArrayList<tour> getAll() throws SQLException, ClassNotFoundException {
        return tourModelDao.getAll();
    }

    @Override
    public boolean save(Tour dto) throws SQLException, ClassNotFoundException {
        return tourModelDao.save(new tour(dto.getTourId(),dto.getTourName(),dto.getTourDescription(),dto.getTourPrice()));
    }

    @Override
    public boolean update(Tour dto) throws SQLException, ClassNotFoundException {
        return tourModelDao.update(new tour(dto.getTourId(),dto.getTourName(),dto.getTourDescription(),dto.getTourPrice()));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return tourModelDao.delete(id);
    }

    @Override
    public List<String> TloadIds() throws SQLException, ClassNotFoundException {
        return queryDao.TloadIds();
    }

    @Override
    public tour search(String id) throws SQLException, ClassNotFoundException {
        return tourModelDao.search(id);
    }
}
