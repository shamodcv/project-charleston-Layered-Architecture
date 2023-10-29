package lk.ijse.Charleston.dao.custom;

import lk.ijse.Charleston.dao.CrudDao;
import lk.ijse.Charleston.dao.SuperDao;
import lk.ijse.Charleston.entity.orders;

import java.sql.SQLException;
import java.time.LocalDate;

public interface PlaceOrderDao extends SuperDao {
    boolean save(String oId, LocalDate now, String resId) throws SQLException, ClassNotFoundException;
}
