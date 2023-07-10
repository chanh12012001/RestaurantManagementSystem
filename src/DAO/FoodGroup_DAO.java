/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.FoodGroup_DTO;
import DAO.Interface.IFoodGroup_DAO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Phạm Văn Chánh
 */
public class FoodGroup_DAO implements IFoodGroup_DAO{
     static Connection conn;
     SQLiteDBExecutor dbExecutor = SQLiteDBExecutor.getInstance();

    /**
     * Get all food groups in database
     *
     * @return A list of food group
     */
    @Override
    public ArrayList<FoodGroup_DTO> getAll() {
        ArrayList<FoodGroup_DTO> foodGroups = new ArrayList<>();

        String sqlStatement = "Select * From NhomMon";
        conn = dbExecutor.connect();
        ResultSet rs = SQLiteDBExecutor.executeQuery(sqlStatement, conn);

        try {
            while (rs.next()) {
                FoodGroup_DTO foodGroup = new FoodGroup_DTO(rs.getInt("MaNhom"),
                        rs.getString("TenNhom"));
                foodGroups.add(foodGroup);
            }
            rs.close();
            rs.getStatement().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbExecutor.closeConnection();
        return foodGroups;
    }
    
    /**
     * Get a food group according to food group name
     *
     * @param name food group name
     * @return A int representing food group 's id
     */
    @Override
    public FoodGroup_DTO getFoodGroupByName(String name) {
        FoodGroup_DTO foodGroup = null;

        String sqlStatement = "Select * from NhomMon where TenNhom = ?";
        conn = dbExecutor.connect();

        ResultSet rs = SQLiteDBExecutor.executeQuery(sqlStatement, conn, name);
        try {
            if (rs.next()) {
                foodGroup = new FoodGroup_DTO(rs.getInt("MaNhom"),
                        rs.getString("TenNhom"));
            }
            rs.close();
            rs.getStatement().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        dbExecutor.closeConnection();
        return foodGroup;
    }
    
    /**
     * Get a food group according to food group id
     *
     * @param id food group id
     * @return A food record representing food group 's id
     */
    @Override
    public FoodGroup_DTO getFoodGroupById(int id) {
        FoodGroup_DTO foodGroup = null;

        String sqlStatement = "Select * from NhomMon where MaNhom = ?";
        conn = dbExecutor.connect();

        ResultSet rs = SQLiteDBExecutor.executeQuery(sqlStatement, conn, id);
        try {
            if (rs.next()) {
                foodGroup = new FoodGroup_DTO(rs.getInt("MaNhom"),
                        rs.getString("TenNhom"));
            }
            rs.close();
            rs.getStatement().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        dbExecutor.closeConnection();
        return foodGroup;
    }

    /**
     * Find food groups
     *
     * @param name food group name
     * @return A list of food group found
     */
    @Override
    public ArrayList<FoodGroup_DTO> findFoodGroups(String name) {
        ArrayList<FoodGroup_DTO> foodGroups = new ArrayList<>();

        String sqlStatement = "Select * From NhomMon where TenNhom like '%" +name+ "%'";

        conn = dbExecutor.connect();
        ResultSet rs = SQLiteDBExecutor.executeQuery(sqlStatement, conn);

        try {
            while (rs.next()) {
                FoodGroup_DTO foodGroup = new FoodGroup_DTO(rs.getInt("MaNhom"),
                        rs.getString("TenNhom"));
                foodGroups.add(foodGroup);
            }
            rs.close();
            rs.getStatement().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        dbExecutor.closeConnection();
        return foodGroups;
    }
    
     /**
     * Add food group
     *
     * @param foodGroup foodGroup object
     * @return A Boolean representing success or fail
     */
    @Override
    public boolean add(FoodGroup_DTO foodGroup) {
        String sqlStatement = "insert into NhomMon(TenNhom) values(?)";
        conn = dbExecutor.connect();

        boolean isSuccess = SQLiteDBExecutor.executeNonQuery(sqlStatement, conn, foodGroup.getName());

        dbExecutor.closeConnection();

        return isSuccess;
    }
    
    
    /**
     * Update food group
     *
     * @param foodGroup foodGroup object
     * @return A Boolean representing success or fail
     */
    @Override
    public boolean update(FoodGroup_DTO foodGroup) {
        String sqlStatement = "Update NhomMon Set TenNhom = ? Where MaNhom = ?";
        conn = dbExecutor.connect();

        boolean isSuccess = SQLiteDBExecutor.executeNonQuery(sqlStatement, conn, foodGroup.getName(), foodGroup.getId());

        dbExecutor.closeConnection();

        return isSuccess;
    }
    
     /**
     * Delete food group
     *
     * @param foodGroup 
     * @return A Boolean representing success or fail
     */
    @Override
    public boolean delete(String foodGroup) {
        String sqlStatement = "Delete from NhomMon Where MaNhom = ?";
        conn = dbExecutor.connect();

        boolean isSuccess = SQLiteDBExecutor.executeNonQuery(sqlStatement, conn, foodGroup);

        dbExecutor.closeConnection();

        return isSuccess;
    }
}
