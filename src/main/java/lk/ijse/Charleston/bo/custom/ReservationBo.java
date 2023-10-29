package lk.ijse.Charleston.bo.custom;

import lk.ijse.Charleston.bo.SuperBo;
import lk.ijse.Charleston.dao.custom.impl.util.SqlUtil;
import lk.ijse.Charleston.dto.Reservation;
import lk.ijse.Charleston.entity.resavations;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ReservationBo extends SuperBo {
     ArrayList<resavations> getAll() throws SQLException, ClassNotFoundException;
     boolean save(Reservation dto) throws SQLException, ClassNotFoundException;
     boolean delete(String id) throws SQLException, ClassNotFoundException;
     String generateNewID() throws SQLException, ClassNotFoundException;
     List<String> loadIds() throws SQLException, ClassNotFoundException;
}
