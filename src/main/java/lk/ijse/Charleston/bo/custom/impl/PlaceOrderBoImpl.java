package lk.ijse.Charleston.bo.custom.impl;

import lk.ijse.Charleston.bo.custom.PlaceOrderBo;
import lk.ijse.Charleston.dao.DaoFactory;
import lk.ijse.Charleston.dao.custom.PlaceOrderDao;
import lk.ijse.Charleston.dao.custom.QueryDao;
import lk.ijse.Charleston.db.DBConnection;
import lk.ijse.Charleston.dto.OrderDetails;
import lk.ijse.Charleston.model.PlaceOrderModel;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class PlaceOrderBoImpl implements PlaceOrderBo {

    PlaceOrderDao placeOrderDao = DaoFactory.getDaoFactory().getDao(DaoFactory.DaoType.PLACEORDER);
    QueryDao queryDao = DaoFactory.getDaoFactory().getDao(DaoFactory.DaoType.QUERY);

    @Override
    public boolean placeOrder(List<OrderDetails> cartDTOList, String oId, String resId) throws SQLException, ClassNotFoundException {
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            boolean isSaved = placeOrderDao.save(oId, LocalDate.now(),resId);
            if(isSaved) {
                boolean isUpdated = queryDao.saveOrderDetails(cartDTOList);
                if(isUpdated) {
                    con.commit();
                    return true;
                }
            }
            return false;
        } catch (SQLException er) {
            con.rollback();
            return false;
        } finally {
            System.out.println("finally");
            con.setAutoCommit(true);
        }
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return queryDao.getNextId();
    }

    @Override
    public double getOrderAm(String resId) throws SQLException, ClassNotFoundException {
        return queryDao.getOrderAm(resId);
    }
}
