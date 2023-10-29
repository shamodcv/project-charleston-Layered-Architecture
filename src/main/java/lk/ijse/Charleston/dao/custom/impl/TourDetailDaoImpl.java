package lk.ijse.Charleston.dao.custom.impl;

import lk.ijse.Charleston.dao.custom.TourDetailDao;
import lk.ijse.Charleston.dao.custom.impl.util.SqlUtil;
import lk.ijse.Charleston.dto.TourDetails;
import lk.ijse.Charleston.entity.tour_details;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TourDetailDaoImpl implements TourDetailDao {
    @Override
    public ArrayList<tour_details> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<tour_details> data = new ArrayList<>();
        ResultSet rst = SqlUtil.execute("SELECT * FROM Tour_Details");
        while (rst.next()) {
            data.add(new tour_details(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getDouble(6)
            ));
        }
        return data;
    }

    @Override
    public boolean save(tour_details dto) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("INSERT INTO Tour_Details(Book_ID,Guest_ID,Guest_Name,Tour_ID,Tour_Name,Price) VALUES(?, ?, ?, ?, ?, ?)",dto.getBook_ID(),dto.getGuest_ID(),dto.getGuest_Name(),dto.getTour_ID(),dto.getTour_Name(),dto.getPrice());
    }

    @Override
    public boolean update(tour_details dto) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("UPDATE Tour_Details SET Guest_ID = ?, Guest_Name = ?, Tour_ID = ?, Tour_Name = ?, Price = ? WHERE Book_ID = ?",dto.getGuest_ID(),dto.getGuest_Name(),dto.getTour_ID(),dto.getTour_Name(),dto.getPrice(),dto.getBook_ID());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("DELETE FROM Tour_Details WHERE Book_ID = ?",id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SqlUtil.execute("SELECT Book_ID FROM Tour_Details ORDER BY Book_ID DESC LIMIT 1");
        if (rst.next()){
            String Id = rst.getString(1);
            String[] strings = Id.split("00");
            int id = Integer.parseInt(strings[1]);
            id++;
            return "B00" + "" + id;
        }
        return "B001";
    }

    @Override
    public tour_details search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }
}
