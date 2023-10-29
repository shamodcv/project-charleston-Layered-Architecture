package lk.ijse.Charleston.bo.custom;

import lk.ijse.Charleston.bo.SuperBo;
import lk.ijse.Charleston.dao.custom.impl.util.SqlUtil;
import lk.ijse.Charleston.dto.Food;
import lk.ijse.Charleston.entity.food_menu;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface FoodBo extends SuperBo {
     ArrayList<food_menu> getAll() throws SQLException, ClassNotFoundException;
     boolean save(Food dto) throws SQLException, ClassNotFoundException;
     boolean update(Food dto) throws SQLException, ClassNotFoundException;
     boolean delete(String id) throws SQLException, ClassNotFoundException;
     String generateNewID() throws SQLException, ClassNotFoundException;
     food_menu search(String id) throws SQLException, ClassNotFoundException;
}
