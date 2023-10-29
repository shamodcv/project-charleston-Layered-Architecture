package lk.ijse.Charleston.bo.custom;

import lk.ijse.Charleston.bo.SuperBo;
import lk.ijse.Charleston.dao.custom.impl.util.SqlUtil;
import lk.ijse.Charleston.dto.Payment;
import lk.ijse.Charleston.entity.payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentBo extends SuperBo {
     ArrayList<payment> getAll() throws SQLException, ClassNotFoundException;
     boolean save(Payment dto) throws SQLException, ClassNotFoundException;
     boolean update(Payment dto) throws SQLException, ClassNotFoundException;
     boolean delete(String id) throws SQLException, ClassNotFoundException;
     String generateNewID() throws SQLException, ClassNotFoundException;
}
