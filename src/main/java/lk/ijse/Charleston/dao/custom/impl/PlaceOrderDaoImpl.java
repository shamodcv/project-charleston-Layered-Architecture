package lk.ijse.Charleston.dao.custom.impl;

import lk.ijse.Charleston.dao.custom.PlaceOrderDao;
import lk.ijse.Charleston.dao.custom.impl.util.SqlUtil;
import lk.ijse.Charleston.entity.orders;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class PlaceOrderDaoImpl implements PlaceOrderDao {

    @Override
    public boolean save(String oId, LocalDate now, String resId) throws SQLException, ClassNotFoundException {
        Date date = Date.valueOf(now);
        return SqlUtil.execute("INSERT INTO Orders(orderId, date, Res_ID) VALUES(?, ?, ?)",oId,date,resId);
    }
}
