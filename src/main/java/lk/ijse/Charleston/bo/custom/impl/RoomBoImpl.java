package lk.ijse.Charleston.bo.custom.impl;

import lk.ijse.Charleston.bo.custom.RoomBo;
import lk.ijse.Charleston.dao.DaoFactory;
import lk.ijse.Charleston.dao.custom.QueryDao;
import lk.ijse.Charleston.dao.custom.RoomDao;
import lk.ijse.Charleston.dto.Room;
import lk.ijse.Charleston.entity.room;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomBoImpl implements RoomBo {

    RoomDao roomDao = DaoFactory.getDaoFactory().getDao(DaoFactory.DaoType.ROOM);
    QueryDao queryDao = DaoFactory.getDaoFactory().getDao(DaoFactory.DaoType.QUERY);

    @Override
    public ArrayList<room> getAll() throws SQLException, ClassNotFoundException {
        return roomDao.getAll();
    }

    @Override
    public boolean save(Room dto) throws SQLException, ClassNotFoundException {
        return roomDao.save(new room(dto.getRoomId(),dto.getRoomType(),dto.getRoomDetails(),dto.getNumberofBeds(),dto.getPrice()));
    }

    @Override
    public boolean update(Room dto) throws SQLException, ClassNotFoundException {
        return roomDao.update(new room(dto.getRoomId(),dto.getRoomType(),dto.getRoomDetails(),dto.getNumberofBeds(),dto.getPrice()));
    }

    @Override
    public boolean delete(String rid) throws SQLException, ClassNotFoundException {
        return roomDao.delete(rid);
    }

    @Override
    public room search(String id) throws SQLException, ClassNotFoundException {
        return roomDao.search(id);
    }

    @Override
    public List<String> loadIds() throws SQLException, ClassNotFoundException {
        return queryDao.RloadIds();
    }

    @Override
    public void releaseRoom(String roomId, String release) throws SQLException, ClassNotFoundException {
        queryDao.releaseRoom(roomId, release);
    }

    @Override
    public List<Room> getAllAvailableRooms() throws SQLException, ClassNotFoundException {
        return queryDao.getAllAvailableRooms();
    }
}
