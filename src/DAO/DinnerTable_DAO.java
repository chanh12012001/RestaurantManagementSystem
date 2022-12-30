package DAO;

import DTO.DinnerTable_DTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import DAO.Interface.IDinnerTable_DAO;

public class DinnerTable_DAO implements IDinnerTable_DAO {

    static Connection conn;

    /**
     * Get all dinner table in database
     *
     * @return A list of dinner table
     */
    @Override
    public ArrayList<DinnerTable_DTO> getAll() {

        ArrayList<DinnerTable_DTO> dinnerTables = new ArrayList<>();

        String sqlStatement = "Select * From BanAn";
        conn = SQLiteDBExecutor.connect();
        ResultSet rs = SQLiteDBExecutor.executeQuery(sqlStatement, conn);

        try {
            while (rs.next()) {
                DinnerTable_DTO dinnerTable = new DinnerTable_DTO(rs.getInt("ID"),
                        rs.getString("TenBan"),
                        rs.getString("TrangThai"));
                dinnerTables.add(dinnerTable);
            }
            rs.close();
            rs.getStatement().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        SQLiteDBExecutor.closeConnection(conn);
        return dinnerTables;
    }

    /**
     * Get a table according to table name
     *
     * @param name dinner table name
     * @return A int representing dinner table 's id
     */

    @Override
    public DinnerTable_DTO getDinnerTableByTableName(String name) {
        DinnerTable_DTO dinnerTable = null;

        String sqlStatement = "Select * from BanAn where TenBan = ?";
        conn = SQLiteDBExecutor.connect();

        ResultSet rs = SQLiteDBExecutor.executeQuery(sqlStatement, conn, name);
        try {
            if (rs.next()) {
                dinnerTable = new DinnerTable_DTO(rs.getInt("ID"),
                        rs.getString("TenBan"),
                        rs.getString("TrangThai"));
            }
            rs.close();
            rs.getStatement().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        SQLiteDBExecutor.closeConnection(conn);
        return dinnerTable;
    }
    
    /**
     * Get a table according to table id
     *
     * @param id
     * @return A object dinnerTable representing dinner table 's id
     */
    @Override
    public DinnerTable_DTO getDinnerTableByTableId(int id) {
        DinnerTable_DTO dinnerTable = null;

        String sqlStatement = "Select * from BanAn where ID = ?";
        conn = SQLiteDBExecutor.connect();

        ResultSet rs = SQLiteDBExecutor.executeQuery(sqlStatement, conn, id);
        try {
            if (rs.next()) {
                dinnerTable = new DinnerTable_DTO(rs.getInt("ID"),
                        rs.getString("TenBan"),
                        rs.getString("TrangThai"));
            }
            rs.close();
            rs.getStatement().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        SQLiteDBExecutor.closeConnection(conn);
        return dinnerTable;
    }

    /**
     * Find tables
     *
     * @param name dinner table name
     * @return A list of dinner table found
     */
    @Override
    public ArrayList<DinnerTable_DTO> findTables(String name) {
        ArrayList<DinnerTable_DTO> dinnerTables = new ArrayList<>();

        String sqlStatement = "Select * From BanAn where TenBan like '%" +name+ "%'";

        conn = SQLiteDBExecutor.connect();
        ResultSet rs = SQLiteDBExecutor.executeQuery(sqlStatement, conn);

        try {
            while (rs.next()) {
                DinnerTable_DTO dinnerTable = new DinnerTable_DTO(rs.getInt("ID"),
                        rs.getString("TenBan"),
                        rs.getString("TrangThai"));
                dinnerTables.add(dinnerTable);
            }
            rs.close();
            rs.getStatement().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        SQLiteDBExecutor.closeConnection(conn);
        return dinnerTables;
    }

    /**
     * Set status of table to occupied
     *
     * @param id dinner table id
     * @return A Boolean representing success or fail
     */
    @Override
    public boolean setStatusOccupied(int id) {
        String sqlStatement = "Update BanAn Set TrangThai = 'Có Người' Where ID = ?";
        conn = SQLiteDBExecutor.connect();

        boolean isSuccess = SQLiteDBExecutor.executeNonQuery(sqlStatement, conn, id);

        SQLiteDBExecutor.closeConnection(conn);

        return isSuccess;
    }

    /**
     * Set status of table to empty
     *
     * @param id dinner table id
     * @return A Boolean representing success or fail
     */
    @Override
    public boolean setStatusEmpty(int id) {
        String sqlStatement = "Update BanAn Set TrangThai = 'Trống' Where ID = ?";
        conn = SQLiteDBExecutor.connect();

        boolean isSuccess = SQLiteDBExecutor.executeNonQuery(sqlStatement, conn, id);

        SQLiteDBExecutor.closeConnection(conn);

        return isSuccess;
    }

    /**
     * Add table
     *
     * @param dinnerTable dinner table object
     * @return A Boolean representing success or fail
     */
    @Override
    public boolean add(DinnerTable_DTO dinnerTable) {
        String sqlStatement = "insert into BanAn(TenBan,TrangThai) values(?,'Trống')";
        conn = SQLiteDBExecutor.connect();

        boolean isSuccess = SQLiteDBExecutor.executeNonQuery(sqlStatement, conn,
                dinnerTable.getName());

        SQLiteDBExecutor.closeConnection(conn);

        return isSuccess;
    }

    /**
     * Update table
     *
     * @param dinnerTable dinner table object
     * @return A Boolean representing success or fail
     */
    @Override
    public boolean update(DinnerTable_DTO dinnerTable) {
        String sqlStatement = "Update BanAn Set TenBan = ? Where ID = ?";
        conn = SQLiteDBExecutor.connect();

        boolean isSuccess = SQLiteDBExecutor.executeNonQuery(sqlStatement, conn,
                dinnerTable.getName(), dinnerTable.getId());

        SQLiteDBExecutor.closeConnection(conn);

        return isSuccess;
    }

    /**
     * Delete table
     *
     * @param id dinner table id
     * @return A Boolean representing success or fail
     */
    @Override
    public boolean delete(String id) {
        String sqlStatement = "Delete from BanAn Where ID = ?";
        conn = SQLiteDBExecutor.connect();

        boolean isSuccess = SQLiteDBExecutor.executeNonQuery(sqlStatement, conn, id);

        SQLiteDBExecutor.closeConnection(conn);

        return isSuccess;
    }

}