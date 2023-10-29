package lk.ijse.Charleston.dao.custom.impl;

import lk.ijse.Charleston.dao.custom.RoomDao;
import lk.ijse.Charleston.dao.custom.impl.util.SqlUtil;
import lk.ijse.Charleston.dto.Room;
import lk.ijse.Charleston.entity.room;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDaoImpl implements RoomDao {
    @Override
    public ArrayList<room> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<room> data = new ArrayList<>();
        ResultSet rst = SqlUtil.execute("SELECT * FROM room");
        while (rst.next()) {
            data.add(new room(
                    rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getDouble(5)
            ));
        }
        return data;
    }

    @Override
    public boolean save(room dto) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("INSERT INTO room(Room_ID, Room_Type, Room_Details, Number_Of_Beds, Room_price) VALUES(?, ?, ?, ?, ?)", dto.getRoom_ID(),dto.getRoom_Type(),dto.getRoom_Details(),dto.getNumber_OF_Beds(),dto.getRoom_price());
    }

    @Override
    public boolean update(room dto) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("UPDATE room SET Room_Type = ?, Room_Details = ?, Number_Of_Beds = ?, Room_price = ? WHERE Room_ID = ?",dto.getRoom_Type(),dto.getRoom_Details(),dto.getNumber_OF_Beds(),dto.getRoom_price(),dto.getRoom_ID());
    }

    @Override
    public boolean delete(String rid) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("DELETE FROM room WHERE Room_ID = ?",rid);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public room search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SqlUtil.execute("SELECT * FROM Room WHERE Room_ID = ?",id);
        return rst.next() ? new room(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getDouble(5)): null;
    }
}
