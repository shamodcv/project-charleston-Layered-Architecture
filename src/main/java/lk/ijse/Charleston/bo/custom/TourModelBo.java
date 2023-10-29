package lk.ijse.Charleston.bo.custom;

import lk.ijse.Charleston.bo.SuperBo;
import lk.ijse.Charleston.dao.CrudDao;
import lk.ijse.Charleston.dao.custom.impl.util.SqlUtil;
import lk.ijse.Charleston.dto.Tour;
import lk.ijse.Charleston.entity.tour;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface TourModelBo extends SuperBo {
     ArrayList<tour> getAll() throws SQLException, ClassNotFoundException;
     boolean save(Tour dto) throws SQLException, ClassNotFoundException;
     boolean update(Tour dto) throws SQLException, ClassNotFoundException;
     boolean delete(String id) throws SQLException, ClassNotFoundException;
     List<String> TloadIds() throws SQLException, ClassNotFoundException;
     tour search(String id) throws SQLException, ClassNotFoundException;
}
