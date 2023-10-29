package lk.ijse.Charleston.dao.custom.impl;

import lk.ijse.Charleston.dao.custom.PaymentDao;
import lk.ijse.Charleston.dao.custom.impl.util.SqlUtil;
import lk.ijse.Charleston.dto.Payment;
import lk.ijse.Charleston.entity.payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentDaoImpl implements PaymentDao {
    @Override
    public ArrayList<payment> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<payment> data = new ArrayList<>();
        ResultSet rst = SqlUtil.execute("SELECT * FROM Payment");
        while (rst.next()) {
            data.add(new payment(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5),rst.getString(6),rst.getString(7),rst.getDouble(8),rst.getDouble(9) ));
        }
        return data;
    }

    @Override
    public boolean save(payment dto) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("INSERT INTO Payment(PayID , Guest_ID , Guest_Name , Res_ID , Room_ID ,Check_in_Date ,Check_out_Date ,Orders_Ammount , Total_Price ) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)",
        dto.getPayID(),dto.getGuest_ID(),dto.getGuest_Name(),dto.getRes_ID(),dto.getRoom_ID(),dto.getCheck_in_Date(),dto.getCheck_out_Date(),dto.getOrders_Ammount(),dto.getTotal_Price());
    }

    @Override
    public boolean update(payment dto) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("UPDATE Payment SET Guest_ID = ?, Guest_Name = ?, Res_ID = ?, Room_ID = ?, Check_in_Date = ?, Check_out_Date = ?, Orders_Ammount = ?, Total_Price = ? WHERE PayID = ?",
                dto.getGuest_ID(),dto.getGuest_Name(),dto.getRes_ID(),dto.getRoom_ID(),dto.getCheck_in_Date(),dto.getCheck_out_Date(),dto.getOrders_Ammount(),dto.getTotal_Price(),dto.getPayID());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("DELETE FROM Payment WHERE PayID = ?",id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SqlUtil.execute("SELECT PayID FROM Payment ORDER BY PayID DESC LIMIT 1");
        if (rst.next()) {
            String currentId = rst.getString(1);
            String[] strings = currentId.split("00");
            int id = Integer.parseInt(strings[1]);
            id++;
            return "PA00" + "" + id;
        }return "PA001";
    }

    @Override
    public payment search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }
}
