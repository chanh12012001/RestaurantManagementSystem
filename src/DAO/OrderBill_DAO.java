/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAO.Interface.IOrderBill_DAO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import DTO.OrderDetail_DTO;
import DTO.OrderBill_DTO;
import DTO.Statistic_DTO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bao20 Workflow: open table -> create order bill -> add food -> create
 * order bill detail -> check > change order bill to check and delete all order
 * bill detail
 */
public class OrderBill_DAO implements IOrderBill_DAO {

    static Connection conn;
    SQLiteDBExecutor dbExecutor = SQLiteDBExecutor.getInstance();

    /**
     * Get food order of table by table id
     *
     * @param id dinner table id
     * @return A representing order table 's id
     */
    @Override
    public ArrayList<OrderDetail_DTO> getBillDetailById(int id) {
        ArrayList<OrderDetail_DTO> orderDetailsList = new ArrayList<>();

        String sqlStatement
                = "select HoaDonInfo.ID,MonAn.TenMonAn,HoaDonInfo.SoLuong,MonAn.Gia "
                + "from HoaDon, MonAn, HoaDonInfo "
                + "where HoaDonInfo.IDMonAn = MonAn.ID AND HoaDonInfo.IDHoaDon = HoaDon.ID and HoaDon.IDBan = ?";
        conn = dbExecutor.connect();
        ResultSet rs = SQLiteDBExecutor.executeQuery(sqlStatement, conn, id);

        try {
            while (rs.next()) {
                OrderDetail_DTO orderDetail = new OrderDetail_DTO(
                        rs.getInt("ID"),
                        rs.getString("TenMonAn"),
                        rs.getInt("SoLuong"),
                        rs.getDouble("Gia"));
                orderDetailsList.add(orderDetail);
            }
            rs.close();
            rs.getStatement().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbExecutor.closeConnection();
        return orderDetailsList;
    }

    /**
     * Create order bill with table id
     *
     * @param id dinner table id
     * @return A Boolean true if success, otherwise false
     */
    @Override
    public boolean add(int id) {
        //Add order bill to HoaDon table
        String sqlStatement = "insert into HoaDon(IDBan,TinhTrang) values(?,0)";
        conn = dbExecutor.connect();

        boolean isSuccess = SQLiteDBExecutor.executeNonQuery(sqlStatement, conn,
                id);

        dbExecutor.closeConnection();

        return isSuccess;
    }

    /**
     * Get current id of unpaid bill in table by table id
     *
     * @param id table id
     * @return A int representing id of bill
     */
    @Override
    public int getCurrentBillId(int id) {
        // 0 - unpaid bill status representing id
        int idUnpaidBill = -1;
        String sqlStatement = "Select HoaDon.ID from BanAn,HoaDon "
                + "where HoaDon.IDBan = ? AND TinhTrang = 0";
        conn = dbExecutor.connect();

        ResultSet rs = SQLiteDBExecutor.executeQuery(sqlStatement, conn, id);
        try {
            if (rs.next()) {
                idUnpaidBill = rs.getInt("ID");
            }
            rs.close();
            rs.getStatement().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        dbExecutor.closeConnection();
        return idUnpaidBill;
    }

    /**
     * Delete all order bill detail in table
     *
     * @param id order bill id
     * @return A Boolean true if success, otherwise false
     */
    @Override
    public boolean deleteAllBillDetail(int id) {
        String sqlStatement = "Delete from HoaDonInfo where HoaDonInfo.IDHoaDon = ?";
        conn = dbExecutor.connect();

        boolean isSuccess = SQLiteDBExecutor.executeNonQuery(sqlStatement, conn, id);

        dbExecutor.closeConnection();

        return isSuccess;
    }

    /**
     * Check out table bill
     *
     * @param orderBill Order bill of table must contain checkoutDate,total,id
     * table
     * @return A Boolean true if success, otherwise false
     */
    @Override
    public boolean checkoutBill(OrderBill_DTO orderBill) {
        String sqlStatement = "Update HoaDon Set TinhTrang = 1, NgayThanhToan = ?, SoTien = ? "
                + "Where IDBan = ? AND TinhTrang = 0";
        conn = dbExecutor.connect();

        boolean isSuccess = SQLiteDBExecutor.executeNonQuery(sqlStatement, conn,
                orderBill.getCheckoutDate(), orderBill.getTotal(), orderBill.getIdTable());

        dbExecutor.closeConnection();

        return isSuccess;
    }

    /**
     * Add order food detail to database
     *
     * @param orderDetail Order food detail
     * @return A Boolean true if success, otherwise false
     */
    @Override
    public boolean insertFood(OrderDetail_DTO orderDetail) {
        String sqlStatement = "Insert into HoaDonInfo(IDHoaDon,IDMonAn,SoLuong) values(?,?,?)";
        conn = dbExecutor.connect();

        boolean isSuccess = SQLiteDBExecutor.executeNonQuery(sqlStatement, conn,
                orderDetail.getId(), orderDetail.getIdFood(), orderDetail.getAmount());

        dbExecutor.closeConnection();

        return isSuccess;
    }

    /**
     * Update amount of food in order detail
     *
     * @param orderDetail Order food detail must contain orderBill id, amount
     * and food id
     * @return A Boolean true if success, otherwise false
     */
    @Override
    public boolean updateAmountFood(OrderDetail_DTO orderDetail) {
        String sqlStatement = "Update HoaDonInfo Set SoLuong = ? Where IDHoaDon = ? AND IDMonAn = ?";
        conn = dbExecutor.connect();

        boolean isSuccess = SQLiteDBExecutor.executeNonQuery(sqlStatement, conn,
                orderDetail.getAmount(), orderDetail.getId(), orderDetail.getIdFood());

        dbExecutor.closeConnection();

        return isSuccess;
    }

    /**
     * Delete order detail
     *
     * @param orderDetail Order food detail must contain order bill id and food
     * id
     * @return A Boolean true if success, otherwise false
     */
    @Override
    public boolean deleteOrderDetail(OrderDetail_DTO orderDetail) {
        String sqlStatement = "Delete from HoaDonInfo where HoaDonInfo.IDHoaDon = ? AND HoaDonInfo.IDMonAn = ?";
        conn = dbExecutor.connect();

        boolean isSuccess = SQLiteDBExecutor.executeNonQuery(sqlStatement, conn,
                orderDetail.getId(), orderDetail.getIdFood());

        dbExecutor.closeConnection();

        return isSuccess;
    }

    /**
     * Update bill when customer change table
     *
     * @param idFromTable From table id
     * @param idToTable To table id
     * @return A Boolean true if success, otherwise false
     */
    @Override
    public boolean changeTable(int idFromTable, int idToTable) {
        String sqlStatement = "Update HoaDon Set IDBan = ? Where IDBan = ? AND TinhTrang = 0";
        conn = dbExecutor.connect();

        boolean isSuccess = SQLiteDBExecutor.executeNonQuery(sqlStatement, conn,
                idToTable, idFromTable);

        dbExecutor.closeConnection();

        return isSuccess;
    }

    /**
     * Get all bill in database
     *
     * @return A list of bill
     */
    @Override
    public ArrayList<OrderBill_DTO> getAll() {
        ArrayList<OrderBill_DTO> orderBillList = new ArrayList<>();

        String sqlStatement = "select HoaDon.ID,BanAn.TenBan,HoaDon.NgayThanhToan,HoaDon.SoTien from HoaDon,BanAn"
                + " where HoaDon.IDBan = BanAn.ID AND TinhTrang = 1 ";
        conn = dbExecutor.connect();
        ResultSet rs = SQLiteDBExecutor.executeQuery(sqlStatement, conn);

        try {
            while (rs.next()) {
                OrderBill_DTO orderBill = new OrderBill_DTO(
                        rs.getInt("ID"),
                        rs.getString("TenBan"),
                        rs.getString("1"),
                        rs.getString("NgayThanhToan"),
                        rs.getDouble("SoTien"));
                orderBillList.add(orderBill);
            }
            rs.close();
            rs.getStatement().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbExecutor.closeConnection();
        return orderBillList;
    }

    /**
     * Delete bill by id
     *
     * @param idBill
     * @return A Boolean true if success, otherwise false
     */
    @Override
    public boolean delete(String idBill) {
        String sqlStatement = "Delete from HoaDon where HoaDon.ID = ?";
        conn = dbExecutor.connect();

        boolean isSuccess = SQLiteDBExecutor.executeNonQuery(sqlStatement, conn,
                idBill);

        dbExecutor.closeConnection();

        return isSuccess;
    }

    /**
     * Delete all bill in database
     *
     * @return A Boolean true if success, otherwise false
     */
    @Override
    public boolean deleteAll() {
        String sqlStatement = "Delete from HoaDon";
        conn = dbExecutor.connect();

        boolean isSuccess = SQLiteDBExecutor.executeNonQuery(sqlStatement, conn);

        dbExecutor.closeConnection();

        return isSuccess;
    }


    @Override
    public boolean add(OrderBill_DTO obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(OrderBill_DTO obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
