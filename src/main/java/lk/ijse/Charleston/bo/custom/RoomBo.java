package lk.ijse.Charleston.bo.custom;

import lk.ijse.Charleston.bo.SuperBo;
import lk.ijse.Charleston.dao.custom.impl.util.SqlUtil;
import lk.ijse.Charleston.db.DBConnection;
import lk.ijse.Charleston.dto.Room;
import lk.ijse.Charleston.entity.room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface RoomBo extends SuperBo {
     ArrayList<room> getAll() throws SQLException, ClassNotFoundException;
     boolean save(Room dto) throws SQLException, ClassNotFoundException;
     boolean update(Room dto) throws SQLException, ClassNotFoundException;
     boolean delete(String rid) throws SQLException, ClassNotFoundException;
     room search(String id) throws SQLException, ClassNotFoundException;
     List<String> loadIds() throws SQLException, ClassNotFoundException;
     void releaseRoom(String roomId, String release) throws SQLException, ClassNotFoundException;
     List<Room> getAllAvailableRooms() throws SQLException, ClassNotFoundException;
}
