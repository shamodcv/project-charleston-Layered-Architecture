package lk.ijse.Charleston.bo.custom.impl;

import lk.ijse.Charleston.bo.custom.FoodBo;
import lk.ijse.Charleston.dao.DaoFactory;
import lk.ijse.Charleston.dao.custom.FoodDao;
import lk.ijse.Charleston.dao.custom.impl.util.SqlUtil;
import lk.ijse.Charleston.dto.Food;
import lk.ijse.Charleston.entity.food_menu;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class    FoodBoImpl implements FoodBo {

    FoodDao foodDao = DaoFactory.getDaoFactory().getDao(DaoFactory.DaoType.FOOD);

    @Override
    public ArrayList<food_menu> getAll() throws SQLException, ClassNotFoundException {
        return foodDao.getAll();
    }

    @Override
    public boolean save(Food dto) throws SQLException, ClassNotFoundException {
        return foodDao.save(new food_menu(dto.getMealType(),dto.getMealID(),dto.getMealName(),dto.getMealDescription(),dto.getMealPrice()));
    }

    @Override
    public boolean update(Food dto) throws SQLException, ClassNotFoundException {
        return foodDao.update(new food_menu(dto.getMealType(),dto.getMealID(),dto.getMealName(),dto.getMealDescription(),dto.getMealPrice()));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return foodDao.delete(id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public food_menu search(String id) throws SQLException, ClassNotFoundException {
        return foodDao.search(id);
    }
}
