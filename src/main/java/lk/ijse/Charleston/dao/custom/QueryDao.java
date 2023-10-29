package lk.ijse.Charleston.dao.custom;

import lk.ijse.Charleston.dao.SuperDao;
import lk.ijse.Charleston.dto.OrderDetails;
import lk.ijse.Charleston.dto.Room;

import java.sql.SQLException;
import java.util.List;

public interface QueryDao extends SuperDao {
    boolean saveOrderDetails(List<OrderDetails> cartDTOList) throws SQLException, ClassNotFoundException;
    boolean updateQty(OrderDetails dto) throws SQLException, ClassNotFoundException;
    double getOrderAm(String resId) throws SQLException, ClassNotFoundException;
    String getNextId() throws SQLException, ClassNotFoundException;
    String splitId(String currentId);
    List<String> loadIds() throws SQLException, ClassNotFoundException;
    List<Room> getAllAvailableRooms() throws SQLException, ClassNotFoundException;
    void releaseRoom(String roomId, String release) throws SQLException, ClassNotFoundException;
    List<String> RloadIds() throws SQLException, ClassNotFoundException;
    List<String> TloadIds() throws SQLException, ClassNotFoundException;
}
