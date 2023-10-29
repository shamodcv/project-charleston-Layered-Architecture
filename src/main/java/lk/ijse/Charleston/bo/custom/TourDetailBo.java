package lk.ijse.Charleston.bo.custom;

import lk.ijse.Charleston.bo.SuperBo;
import lk.ijse.Charleston.dao.custom.impl.util.SqlUtil;
import lk.ijse.Charleston.dto.TourDetails;
import lk.ijse.Charleston.entity.tour_details;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface TourDetailBo extends SuperBo {
     ArrayList<tour_details> getAll() throws SQLException, ClassNotFoundException;
     boolean save(TourDetails dto) throws SQLException, ClassNotFoundException;
     boolean update(TourDetails dto) throws SQLException, ClassNotFoundException;
     boolean delete(String id) throws SQLException, ClassNotFoundException;
     String generateNewID() throws SQLException, ClassNotFoundException;
}
