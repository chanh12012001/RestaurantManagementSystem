
package DAO;

import DTO.FoodGroup_DTO;
import BUS.FoodGroup_BUS;
import DAO.Interface.IFood_DAO;
import DTO.Food_DTO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author macbookpro
 */
public class Food_DAO implements IFood_DAO{
    static Connection conn;

    /**
     * Get all food in database
     *
     * @return A list of food
     */
    @Override
    public ArrayList<Food_DTO> getAll() {
        ArrayList<Food_DTO> foods = new ArrayList<>();

        String sqlStatement = "Select ID, Image, TenMonAn, DonViTinh, Gia, TenNhom From MonAn, NhomMon where MaNhomMon = MaNhom order by ID ASC";
        conn = SQLiteDBExecutor.connect();
        ResultSet rs = SQLiteDBExecutor.executeQuery(sqlStatement, conn);

        try {
            while (rs.next()) {
               
                Food_DTO food = new Food_DTO(
                        rs.getInt("ID"),
                        rs.getBytes("Image"),
                        rs.getString("TenNhom"),
                        rs.getString("TenMonAn"),
                        rs.getString("DonViTinh"),
                        rs.getInt("Gia")                       
                );
                
                foods.add(food);
            }
            rs.close();
            rs.getStatement().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        SQLiteDBExecutor.closeConnection(conn);
        return foods;
    }
    
    
    /**
     * Get a food according to food name
     *
     * @param name food name
     * @return A int representing food 's id
     */
    @Override
    public Food_DTO getFoodByName(String name) {
        Food_DTO food = null;

        String sqlStatement = "Select * from MonAn where TenMonAn = ?";
        conn = SQLiteDBExecutor.connect();

        ResultSet rs = SQLiteDBExecutor.executeQuery(sqlStatement, conn, name);
        try {
            if (rs.next()) {
                food = new Food_DTO(
                        rs.getInt("ID"),
                        rs.getBytes("Image"),
                        FoodGroup_BUS.getFoodGroupById(rs.getInt("MaNhomMon")).getName(),
                        rs.getString("TenMonAn"),
                        rs.getString("DonViTinh"),
                        rs.getInt("Gia")                       
                );
            }
            rs.close();
            rs.getStatement().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        SQLiteDBExecutor.closeConnection(conn);
        return food;
    }
    
    /**
     * Get a food according to food id
     *
     * @param id food id
     * @return A food representing food 's id
     */
    @Override
    public Food_DTO getFoodById(int id) {
        Food_DTO food = null;

        String sqlStatement = "Select * from MonAn where ID = ?";
        conn = SQLiteDBExecutor.connect();

        ResultSet rs = SQLiteDBExecutor.executeQuery(sqlStatement, conn, id);
        try {
            if (rs.next()) {
                food = new Food_DTO(
                        rs.getInt("ID"),
                        rs.getBytes("Image"),
                        FoodGroup_BUS.getFoodGroupById(rs.getInt("MaNhomMon")).getName(),
                        rs.getString("TenMonAn"),
                        rs.getString("DonViTinh"),
                        rs.getInt("Gia")                       
                );
            }
            rs.close();
            rs.getStatement().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        SQLiteDBExecutor.closeConnection(conn);
        return food;
    }
    
    /**
     * Add food
     *
     * @param food  food object
     * @return A Boolean representing success or fail
     */
    @Override
    public boolean add(Food_DTO food) {
        String sqlStatement = "insert into MonAn(MaNhomMon,TenMonAn,DonViTinh,Gia,Image) values(?,?,?,?,?)";
        conn = SQLiteDBExecutor.connect();
        FoodGroup_DTO foodGroup = FoodGroup_BUS.getFoodGroupByName(food.getFoodGroupName());
        boolean isSuccess = SQLiteDBExecutor.executeNonQuery(sqlStatement, conn, foodGroup.getId(), food.getName(), food.getUnit(), food.getPrice(), food.getImage());
        SQLiteDBExecutor.closeConnection(conn);     
        return isSuccess;
    }
    
    /**
     * Update food
     * 
     * @param food  food object
     * @return A Boolean representing success or fail
     */
    @Override
    public boolean update(Food_DTO food) {
        String sqlStatement = "UPDATE MonAn SET MaNhomMon = ?, TenMonAn = ?, DonViTinh = ?, Gia = ?, Image = ? WHERE ID = ?";
        conn = SQLiteDBExecutor.connect();

        FoodGroup_DTO foodGroup = FoodGroup_BUS.getFoodGroupByName(food.getFoodGroupName());
        boolean isSuccess = SQLiteDBExecutor.executeNonQuery(sqlStatement, conn, foodGroup.getId(), food.getName(), food.getUnit(), food.getPrice(), food.getImage(), food.getId());

        SQLiteDBExecutor.closeConnection(conn);

        return isSuccess;
    }
    
    /**
     * Delete food
     *
     * @param foodId food id
     * @return A Boolean representing success or fail
     */
    @Override
    public boolean delete(String foodId) {
        String sqlStatement = "Delete from MonAn Where ID = ?";
        conn = SQLiteDBExecutor.connect();

        boolean isSuccess = SQLiteDBExecutor.executeNonQuery(sqlStatement, conn, Integer.valueOf(foodId));

        SQLiteDBExecutor.closeConnection(conn);

        return isSuccess;
    }

    
    /**
     * Find foods by food name
     *
     * @param name food name
     * @return A list of food found
     */
    @Override
    public ArrayList<Food_DTO> findFoodsByName(String name) {
        ArrayList<Food_DTO> foods = new ArrayList<>();

        String sqlStatement = "Select * From MonAn where TenMonAn like '%" +name+ "%'";

        conn = SQLiteDBExecutor.connect();
        ResultSet rs = SQLiteDBExecutor.executeQuery(sqlStatement, conn);

        try {
            while (rs.next()) {
                Food_DTO food = new Food_DTO(
                        rs.getInt("ID"),
                        rs.getBytes("Image"),
                        FoodGroup_BUS.getFoodGroupById(rs.getInt("MaNhomMon")).getName(),
                        rs.getString("TenMonAn"),
                        rs.getString("DonViTinh"),
                        rs.getInt("Gia")
                );
                foods.add(food);
            }
            rs.close();
            rs.getStatement().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        SQLiteDBExecutor.closeConnection(conn);
        return foods;
    }
    
    /**
     * Find foods by group name
     *
     * @param groupName food group Name
     * @return A list of food found
     */
    @Override
    public ArrayList<Food_DTO> findFoodsByGroupName(String groupName) {
        ArrayList<Food_DTO> foods = new ArrayList<>();

        String sqlStatement = "Select * From MonAn where MaNhomMon = ?";

        conn = SQLiteDBExecutor.connect();
        FoodGroup_DTO foodGroup = FoodGroup_BUS.getFoodGroupByName(groupName);
        ResultSet rs = SQLiteDBExecutor.executeQuery(sqlStatement, conn, foodGroup.getId());

        try {
            while (rs.next()) {
                Food_DTO food = new Food_DTO(
                        rs.getInt("ID"),
                        rs.getBytes("Image"),
                        FoodGroup_BUS.getFoodGroupById(rs.getInt("MaNhomMon")).getName(),
                        rs.getString("TenMonAn"),
                        rs.getString("DonViTinh"),
                        rs.getInt("Gia")
                );
                foods.add(food);
            }
            rs.close();
            rs.getStatement().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        SQLiteDBExecutor.closeConnection(conn);
        return foods;
    }
}
