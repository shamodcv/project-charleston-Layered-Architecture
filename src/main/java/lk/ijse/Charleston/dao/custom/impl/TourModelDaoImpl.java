package lk.ijse.Charleston.dao.custom.impl;

import lk.ijse.Charleston.dao.custom.TourModelDao;
import lk.ijse.Charleston.dao.custom.impl.util.SqlUtil;
import lk.ijse.Charleston.dto.Tour;
import lk.ijse.Charleston.entity.tour;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TourModelDaoImpl implements TourModelDao {
    @Override
    public ArrayList<tour> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<tour> data = new ArrayList<>();
        ResultSet rst = SqlUtil.execute("SELECT * FROM Tour");
        while (rst.next()) {
            data.add(new tour(rst.getString(1), rst.getString(2), rst.getString(3), rst.getDouble(4)
            ));
        }
        return data;
    }

    @Override
    public boolean save(tour dto) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("INSERT INTO Tour(Tour_ID, Tour_Name, Tour_Description, Price) VALUES(?, ?, ?, ?)",dto.getTour_ID(),dto.getTour_Name(),dto.getTour_Description(),dto.getPrice());
    }

    @Override
    public boolean update(tour dto) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("UPDATE Tour SET Tour_Name = ?, Tour_Description = ?, Price = ? WHERE Tour_ID = ?",dto.getTour_Name(),dto.getTour_Description(),dto.getPrice(),dto.getTour_ID());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("DELETE FROM Tour WHERE Tour_ID = ?",id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public tour search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SqlUtil.execute("SELECT * FROM Tour WHERE Tour_ID = ?",id);
        return rst.next() ? new tour(rst.getString(1), rst.getString(2), rst.getString(3), rst.getDouble(4)) : null;
    }
}
