package lk.ijse.Charleston.dao.custom.impl;

import lk.ijse.Charleston.dao.custom.FoodDao;
import lk.ijse.Charleston.dao.custom.impl.util.SqlUtil;
import lk.ijse.Charleston.entity.food_menu;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FoodDaoImpl implements FoodDao {
    @Override
    public ArrayList<food_menu> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<food_menu> Food = new ArrayList<>();
        ResultSet rst = SqlUtil.execute("SELECT * FROM Food_Menu");
        while (rst.next()) {
            Food.add(new food_menu(rst.getString(1), rst.getString(2), rst.getString(3),rst.getString(4),rst.getDouble(5)));
        }
        return Food;
    }

    @Override
    public boolean save(food_menu dto) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("INSERT INTO Food_Menu(Meal_Type, Meal_No, Meal_Name, Meal_Discription, Meal_Price) VALUES(?, ?, ?, ?, ?)",dto.getMeal_Type(),dto.getMeal_No(),dto.getMeal_Name(),dto.getMeal_Discription(),dto.getMeal_Price());
    }

    @Override
    public boolean update(food_menu dto) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("UPDATE Food_Menu SET Meal_Type = ?, Meal_Name = ?, Meal_Discription = ?, Meal_Price = ? WHERE Meal_No = ?",dto.getMeal_Type(),dto.getMeal_Name(),dto.getMeal_Discription(),dto.getMeal_Price(),dto.getMeal_No());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("DELETE FROM food_menu WHERE Meal_No=?",id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public food_menu search(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SqlUtil.execute("SELECT * FROM Food_Menu WHERE Meal_No = ?",id);
        return rst.next() ? new food_menu(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getDouble(5)) : null;
    }
}
