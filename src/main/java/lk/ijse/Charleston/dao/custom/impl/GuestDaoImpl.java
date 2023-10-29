package lk.ijse.Charleston.dao.custom.impl;

import lk.ijse.Charleston.dao.custom.GuestDao;
import lk.ijse.Charleston.dao.custom.impl.util.SqlUtil;
import lk.ijse.Charleston.entity.food_menu;
import lk.ijse.Charleston.entity.guest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GuestDaoImpl implements GuestDao {
    @Override
    public ArrayList<guest> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<guest> Guest = new ArrayList<>();
        ResultSet rst = SqlUtil.execute("SELECT * FROM Guest");
        while (rst.next()) {
            Guest.add(new guest(rst.getString(1), rst.getString(2), rst.getString(3),rst.getString(4),rst.getString(5),rst.getString(6)));
        }
        return Guest;
    }

    @Override
    public boolean save(guest dto) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("INSERT INTO Guest(Guest_ID_Type, Guest_ID, Guest_Name, Guest_Contact_No, Guest_Country, Guest_Email) VALUES(?, ?, ?, ?, ?, ?)",dto.getGuest_ID_type(),dto.getGuest_ID(),dto.getGuest_Name(),dto.getGuest_Contact_No(),dto.getGuest_Country(),dto.getGuest_Email());
    }

    @Override
    public boolean update(guest dto) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("UPDATE Guest SET Guest_ID_type = ?, Guest_Name = ?, Guest_Contact_No = ?, Guest_Country = ?, Guest_Email = ? WHERE Guest_ID = ?",dto.getGuest_ID_type(),dto.getGuest_Name(),dto.getGuest_Contact_No(),dto.getGuest_Country(),dto.getGuest_Email(),dto.getGuest_ID());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("DELETE FROM guest WHERE Guest_ID = ?",id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public guest search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SqlUtil.execute("SELECT * FROM Guest WHERE Guest_ID = ?",id);
        return rst.next() ? new guest(rst.getString(1), rst.getString(2), rst.getString(3),rst.getString(4),rst.getString(5),rst.getString(6)) : null;
    }
}
