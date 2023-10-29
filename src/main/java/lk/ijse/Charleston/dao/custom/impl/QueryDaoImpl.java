package lk.ijse.Charleston.dao.custom.impl;

import lk.ijse.Charleston.dao.custom.QueryDao;
import lk.ijse.Charleston.dao.custom.impl.util.SqlUtil;
import lk.ijse.Charleston.dto.OrderDetails;
import lk.ijse.Charleston.dto.Room;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QueryDaoImpl implements QueryDao {
    @Override
    public boolean saveOrderDetails(List<OrderDetails> cartDTOList) throws SQLException, ClassNotFoundException {
        for(OrderDetails orderDetails : cartDTOList) {
            if(!updateQty(orderDetails)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean updateQty(OrderDetails dto) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("INSERT INTO OrderDetail(orderId, Meal_No , qty ,Ammount ) VALUES(?, ?, ?, ?)",dto.getOrderID(),dto.getMealID(),dto.getQty(),dto.getTotal());
    }

    @Override
    public double getOrderAm(String resId) throws SQLException, ClassNotFoundException {
        ArrayList<String> orderIds = new ArrayList<>();
        ResultSet rst = SqlUtil.execute("SELECT orderId FROM Orders WHERE Res_ID = ?",resId);
        while (rst.next()) {
            String orderId = rst.getString("orderId");
            orderIds.add(orderId);
        }
        double totalAmount = 0.0;
        for (String orderId : orderIds) {
            ResultSet rs = SqlUtil.execute("SELECT Ammount FROM OrderDetail WHERE orderId = ?",orderId);
            double orderAmount = 0.0;
            while (rs.next()) {
                orderAmount += rst.getDouble("Ammount");
            }

            totalAmount += orderAmount;
        }
        return totalAmount;
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SqlUtil.execute("SELECT orderId FROM OrderDetail ORDER BY orderId DESC LIMIT 1");
        return rst.next() ? splitId(rst.getString(1)) : splitId(null);
    }

    @Override
    public String splitId(String currentId) {
        if(currentId != null) {
            String[] strings = currentId.split("00");
            int id = Integer.parseInt(strings[1]);
            id++;
            return "OR00" + "" + id;
        }
        return "OR001";
    }

    @Override
    public List<String> loadIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = SqlUtil.execute("SELECT Res_ID FROM Resavations");
        List<String> data = new ArrayList<>();
        while (rst.next()) {
            data.add(rst.getString(1));
        }
        return data;
    }

    @Override
    public List<Room> getAllAvailableRooms() throws SQLException, ClassNotFoundException {
        List<Room> availableRooms = new ArrayList<>();
        ResultSet rst = SqlUtil.execute("SELECT * FROM Room WHERE Room_Details = 'Available'");
        while (rst.next()) {
            availableRooms.add(new Room(
                    rst.getString("Room_ID"), rst.getString("Room_Type"), rst.getString("Room_Details"), rst.getString("Number_OF_Beds"), rst.getDouble("Room_price")
            ));
        }return availableRooms;
    }

    @Override
    public void releaseRoom(String roomId, String release) throws SQLException, ClassNotFoundException {
        SqlUtil.execute("UPDATE Room SET Room_Details = ? WHERE Room_ID = ?",release,roomId);
    }

    @Override
    public List<String> RloadIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = SqlUtil.execute("SELECT Room_ID FROM Room WHERE Room_Details = 'Available'");
        List<String> data = new ArrayList<>();
        while (rst.next()) {
            data.add(rst.getString(1));
            System.out.println(rst.getString(1));
        }
        return data;
    }

    @Override
    public List<String> TloadIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = SqlUtil.execute("SELECT Tour_ID FROM Tour");
        List<String> data = new ArrayList<>();
        while (rst.next()) {
            data.add(rst.getString(1));
        }
        return data;
    }
}
