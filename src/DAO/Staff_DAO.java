
package DAO;

import DAO.Interface.IStaff_DAO;
import DTO.Staff_DTO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author macbookpro
 */
public class Staff_DAO implements IStaff_DAO{

    static Connection conn;
    
    /**
     * Get all staff in database
     *
     * @return A list of staff
     */
    @Override
    public ArrayList<Staff_DTO> getAll() {
        ArrayList<Staff_DTO> staffs = new ArrayList<>();

        String sqlStatement = "Select * From NhanVien";
        conn = SQLiteDBExecutor.connect();
        ResultSet rs = SQLiteDBExecutor.executeQuery(sqlStatement, conn);

        try {
            while (rs.next()) {
                Staff_DTO staff = new Staff_DTO(rs.getString("MaNV"),
                        rs.getString("HoTen"),
                        rs.getString("NgaySinh"),
                        rs.getString("GioiTinh"),
                        rs.getString("SDT"),
                        rs.getString("ChucVu"),
                        rs.getString("Luong"),
                        rs.getString("DiaChi")
                );
                staffs.add(staff);
            }
            rs.close();
            rs.getStatement().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        SQLiteDBExecutor.closeConnection(conn);
        return staffs;
    }
    
    /**
     * Get a staff according to staff id
     *
     * @param id staff id
     * @return A staff representing staff 's id
     */
    @Override
    public Staff_DTO getStaffById(String id) {
        Staff_DTO staff = null;

        String sqlStatement = "Select * from NhanVien where MaNV = ?";
        conn = SQLiteDBExecutor.connect();

        ResultSet rs = SQLiteDBExecutor.executeQuery(sqlStatement, conn, id);
        try {
            if (rs.next()) {
                staff = new Staff_DTO(rs.getString("MaNV"),
                        rs.getString("HoTen"),
                        rs.getString("NgaySinh"),
                        rs.getString("GioiTinh"),
                        rs.getString("SDT"),
                        rs.getString("ChucVu"),
                        rs.getString("Luong"),
                        rs.getString("DiaChi")
                );
            }
            rs.close();
            rs.getStatement().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        SQLiteDBExecutor.closeConnection(conn);
        return staff;
    }

    /**
     * Add staff
     *
     * @param staff     staff object
     * @return A Boolean representing success or fail
     */
    @Override
    public boolean add(Staff_DTO staff) {
        String sqlStatement = "insert into NhanVien(MaNV, HoTen, NgaySinh, GioiTinh, SDT, ChucVu, Luong, DiaChi) values(?,?,?,?,?,?,?,?)";
        conn = SQLiteDBExecutor.connect();

        boolean isSuccess = SQLiteDBExecutor.executeNonQuery(sqlStatement, conn,
                staff.getStaffID(), staff.getStaffName(), staff.getStaffBirth(), staff.getStaffSex(), staff.getStaffPhone(), staff.getStaffPosition(), staff.getStaffSalary(), staff.getStaffAddress());

        SQLiteDBExecutor.closeConnection(conn);

        return isSuccess;
    }

     /**
     * Update staff
     * 
     * @param staff  staff object
     * @return A Boolean representing success or fail
     */
    @Override
    public boolean update(Staff_DTO staff) {
        String sqlStatement = "UPDATE NhanVien SET HoTen = ?, NgaySinh = ?, GioiTinh = ?, SDT = ?, ChucVu = ?, Luong = ?, DiaChi = ? WHERE MaNV = ?";
        conn = SQLiteDBExecutor.connect();

        boolean isSuccess = SQLiteDBExecutor.executeNonQuery(sqlStatement, conn,  staff.getStaffName(), staff.getStaffBirth(), staff.getStaffSex(), staff.getStaffPhone(), staff.getStaffPosition(), staff.getStaffSalary(), staff.getStaffAddress(), staff.getStaffID());

        SQLiteDBExecutor.closeConnection(conn);

        return isSuccess;
    }
    
     /**
     * Delete staff
     *
     * @param staffID    staff id
     * @return A Boolean representing success or fail
     */
    @Override
    public boolean delete(String staffID) {
        String sqlStatement = "DELETE FROM NhanVien where MaNV = ?";
        conn = SQLiteDBExecutor.connect();

        boolean isSuccess = SQLiteDBExecutor.executeNonQuery(sqlStatement, conn, staffID);

        SQLiteDBExecutor.closeConnection(conn);

        return isSuccess;
    }
    
    
    @Override
    public ArrayList<Staff_DTO> findStaffsByName(String name) {
        ArrayList<Staff_DTO> staffs = new ArrayList<>();

        String sqlStatement = "select * from NhanVien where HoTen like '%" + name + "%'";

        conn = SQLiteDBExecutor.connect();
        ResultSet rs = SQLiteDBExecutor.executeQuery(sqlStatement, conn);

        try {
            while (rs.next()) {
                Staff_DTO staff = new Staff_DTO(rs.getString("MaNV"),
                        rs.getString("HoTen"),
                        rs.getString("NgaySinh"),
                        rs.getString("GioiTinh"),
                        rs.getString("SDT"),
                        rs.getString("ChucVu"),
                        rs.getString("Luong"),
                        rs.getString("DiaChi")
                );
                staffs.add(staff);
            }
            rs.close();
            rs.getStatement().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        SQLiteDBExecutor.closeConnection(conn);
        return staffs;
    }

    
}
