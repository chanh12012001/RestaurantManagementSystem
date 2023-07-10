/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.WarehouseReceipt_DTO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author bao20
 */
public class WarehouseReceipt_DAO implements DAO.Interface.IWarehouseReceipt_DAO {
    SQLiteDBExecutor dbExecutor = SQLiteDBExecutor.getInstance();
    static Connection conn;

    /**
     * Get all warehouse receipt
     *
     * @return A list of warehouse receipt
     */
    @Override
    public ArrayList<WarehouseReceipt_DTO> getAll() {
        ArrayList<WarehouseReceipt_DTO> warehouseReceipts = new ArrayList<>();

        String sqlStatement = "Select * from PhieuNhap";

        conn = dbExecutor.connect();
        ResultSet rs = SQLiteDBExecutor.executeQuery(sqlStatement, conn);

        try {
            while (rs.next()) {
                WarehouseReceipt_DTO warehouseReceipt = new WarehouseReceipt_DTO(
                        rs.getInt("MaPN"),
                        rs.getInt("MaNL"),
                        rs.getString("TenNL"),
                        rs.getString("DonVi"),
                        rs.getInt("SoLuong"),
                        rs.getInt("DonGia"),
                        rs.getString("NgayNhap"),
                        rs.getString("CungCap"),
                        rs.getString("ThuKho")
                );
                warehouseReceipts.add(warehouseReceipt);
            }
            rs.close();
            rs.getStatement().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        dbExecutor.closeConnection();
        return warehouseReceipts;
    }

    /**
     * Add warehouse receipt
     *
     * @param warehouseReceipt warehouse receipt data
     * @return true if add success, otherwise false
     */
    @Override
    public boolean add(WarehouseReceipt_DTO warehouseReceipt) {
        String sqlStatement = "Insert into PhieuNhap(MaNL,TenNL,DonVi,SoLuong,DonGia,NgayNhap,CungCap,ThuKho) values(?,?,?,?,?,?,?,)";
        conn = dbExecutor.connect();

        boolean isSuccess = SQLiteDBExecutor.executeNonQuery(sqlStatement, conn,
                warehouseReceipt.getIdIngredient(),
                warehouseReceipt.getNameIngredient(),
                warehouseReceipt.getCalUnit(),
                warehouseReceipt.getAmount(),
                warehouseReceipt.getUnitPrice(),
                warehouseReceipt.getImportDate(),
                warehouseReceipt.getSupplier(),
                warehouseReceipt.getStorekeeper()
        );

        dbExecutor.closeConnection();

        return isSuccess;
    }

    /**
     * Delete warehouse receipt
     *
     * @param id warehouse receipt 's id
     * @return true if execute success, otherwise fail
     */
    @Override
    public boolean delete(String id) {
        String sqlStatement = "Delete from PhieuNhap Where MaPN = ?";
        conn = dbExecutor.connect();

        boolean isSuccess = SQLiteDBExecutor.executeNonQuery(sqlStatement, conn, id);
        dbExecutor.closeConnection();

        return isSuccess;
    }

    @Override
    public boolean update(WarehouseReceipt_DTO obj) {
        throw new UnsupportedOperationException("Not supported yet.");
// Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
