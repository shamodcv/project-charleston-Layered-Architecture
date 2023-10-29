package lk.ijse.Charleston.dao.custom.impl;

import lk.ijse.Charleston.dao.custom.ReservationDao;
import lk.ijse.Charleston.dao.custom.impl.util.SqlUtil;
import lk.ijse.Charleston.dto.Reservation;
import lk.ijse.Charleston.entity.resavations;
import lk.ijse.Charleston.entity.resavations_detail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationDaoImpl implements ReservationDao {
    @Override
    public ArrayList<resavations> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<resavations> data = new ArrayList<>();
        ResultSet rst = SqlUtil.execute("SELECT * FROM Resavations");
        while (rst.next()) {
            data.add(new resavations(
                    rst.getString("Res_ID"), rst.getString("Guest_ID"), rst.getString("Room_ID"), rst.getDouble("Room_price"), rst.getDate("Check_in_Date"), rst.getDate("Check_out_Date")
            ));
        }
        return data;
    }

    @Override
    public boolean save(resavations dto) throws SQLException, ClassNotFoundException {
        boolean bool = SqlUtil.execute("INSERT INTO Resavations(Res_ID, Guest_ID, Room_ID, Room_price,Check_in_Date,Check_out_Date) VALUES(?, ?, ?, ?, ?, ? )",dto.getRes_ID(),dto.getGuest_ID(),dto.getRoom_ID(),dto.getRoom_price(),dto.getCheck_in_Date(),dto.getCheck_out_Date());
        if(bool){
            ResultSet rst = SqlUtil.execute("SELECT Guest_Name FROM Guest WHERE Guest_ID = ?",dto.getGuest_ID());
            String guestName = rst.getString("Guest_Name");
            String price = String.valueOf(dto.getRoom_price());
            SqlUtil.execute("INSERT INTO Resavations_detail(Res_ID, Room_ID, Room_price, Guest_ID, Guest_Name, Check_In_Date, Check_Out_Date) VALUES(?, ?, ?, ?, ?, ?, ? )",dto.getRes_ID(),dto.getRoom_ID(),price,dto.getGuest_ID(),guestName,dto.getCheck_in_Date(),dto.getCheck_out_Date());
            SqlUtil.execute("UPDATE Room SET Room_Details = ? WHERE Room_ID = ?","Booked",dto.getRoom_ID());
        }

        return bool;
    }

    @Override
    public boolean update(resavations dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("DELETE FROM Resavations WHERE Res_ID = ?",id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SqlUtil.execute("SELECT Res_ID FROM Resavations ORDER BY Res_ID DESC LIMIT 1");
        if (rst.next()) {
            String Id = rst.getString(1);
            String[] strings = Id.split("00");
            int id = Integer.parseInt(strings[1]);
            id++;
            return "Re00" + "" + id;
        }
        return "Re001";
    }

    @Override
    public resavations search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }
}
