
package DAO;

import DTO.FoodGroup_DTO;
import BUS.FoodGroup_BUS;
import BUS.RestaurantManagementFacade;
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
    SQLiteDBExecutor dbExecutor = SQLiteDBExecutor.getInstance();
    RestaurantManagementFacade restaurantManagementFacade = RestaurantManagementFacade.getInstance();

    /**
     * Get all food in database
     *
     * @return A list of food
     */
    @Override
    public ArrayList<Food_DTO> getAll() {
        ArrayList<Food_DTO> foods = new ArrayList<>();

        String sqlStatement = "Select ID, Image, TenMonAn, DonViTinh, Gia, TenNhom From MonAn, NhomMon where MaNhomMon = MaNhom order by ID ASC";
        conn = dbExecutor.connect();
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
        dbExecutor.closeConnection();
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
        conn = dbExecutor.connect();

        ResultSet rs = SQLiteDBExecutor.executeQuery(sqlStatement, conn, name);
        try {
            if (rs.next()) {
                food = new Food_DTO(
                        rs.getInt("ID"),
                        rs.getBytes("Image"),
                        restaurantManagementFacade.getFoodGroupById(rs.getInt("MaNhomMon")).getName(),
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

        dbExecutor.closeConnection();
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
        conn = dbExecutor.connect();

        ResultSet rs = SQLiteDBExecutor.executeQuery(sqlStatement, conn, id);
        try {
            if (rs.next()) {
                food = new Food_DTO(
                        rs.getInt("ID"),
                        rs.getBytes("Image"),
                        restaurantManagementFacade.getFoodGroupById(rs.getInt("MaNhomMon")).getName(),
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

        dbExecutor.closeConnection();
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
        conn = dbExecutor.connect();
        FoodGroup_DTO foodGroup = restaurantManagementFacade.getFoodGroupByName(food.getFoodGroupName());
        boolean isSuccess = SQLiteDBExecutor.executeNonQuery(sqlStatement, conn, foodGroup.getId(), food.getName(), food.getUnit(), food.getPrice(), food.getImage());
        dbExecutor.closeConnection();     
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
        conn = dbExecutor.connect();

        FoodGroup_DTO foodGroup = restaurantManagementFacade.getFoodGroupByName(food.getFoodGroupName());
        boolean isSuccess = SQLiteDBExecutor.executeNonQuery(sqlStatement, conn, foodGroup.getId(), food.getName(), food.getUnit(), food.getPrice(), food.getImage(), food.getId());

        dbExecutor.closeConnection();

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
        conn = dbExecutor.connect();

        boolean isSuccess = SQLiteDBExecutor.executeNonQuery(sqlStatement, conn, Integer.valueOf(foodId));

        dbExecutor.closeConnection();

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

        conn = dbExecutor.connect();
        ResultSet rs = SQLiteDBExecutor.executeQuery(sqlStatement, conn);

        try {
            while (rs.next()) {
                Food_DTO food = new Food_DTO(
                        rs.getInt("ID"),
                        rs.getBytes("Image"),
                        restaurantManagementFacade.getFoodGroupById(rs.getInt("MaNhomMon")).getName(),
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

        dbExecutor.closeConnection();
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

        conn = dbExecutor.connect();
        FoodGroup_DTO foodGroup = restaurantManagementFacade.getFoodGroupByName(groupName);
        ResultSet rs = SQLiteDBExecutor.executeQuery(sqlStatement, conn, foodGroup.getId());

        try {
            while (rs.next()) {
                Food_DTO food = new Food_DTO(
                        rs.getInt("ID"),
                        rs.getBytes("Image"),
                        restaurantManagementFacade.getFoodGroupById(rs.getInt("MaNhomMon")).getName(),
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

        dbExecutor.closeConnection();
        return foods;
    }
}
