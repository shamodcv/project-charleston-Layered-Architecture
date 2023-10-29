package lk.ijse.Charleston.bo.custom;

import lk.ijse.Charleston.bo.SuperBo;
import lk.ijse.Charleston.dao.custom.impl.util.SqlUtil;
import lk.ijse.Charleston.dto.Guest;
import lk.ijse.Charleston.entity.guest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface GuestBo extends SuperBo {
     ArrayList<guest> getAll() throws SQLException, ClassNotFoundException;
     boolean save(Guest dto) throws SQLException, ClassNotFoundException;
     boolean update(Guest dto) throws SQLException, ClassNotFoundException;
     boolean delete(String id) throws SQLException, ClassNotFoundException;
     String generateNewID() throws SQLException, ClassNotFoundException;
     guest search(String id) throws SQLException, ClassNotFoundException;
}
