package lk.ijse.Charleston.bo.custom;

import lk.ijse.Charleston.bo.SuperBo;
import lk.ijse.Charleston.dto.OrderDetails;

import java.sql.SQLException;
import java.util.List;

public interface PlaceOrderBo extends SuperBo {
    boolean placeOrder(List<OrderDetails> cartDTOList, String oId, String resId) throws SQLException, ClassNotFoundException;
    String getNextId() throws SQLException, ClassNotFoundException;
    double getOrderAm(String resId) throws SQLException, ClassNotFoundException;
}
