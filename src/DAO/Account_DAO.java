package DAO;

import static DAO.DinnerTable_DAO.conn;
import DAO.Interface.IAccount_DAO;
import DTO.Account_DTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author macbookpro
 */
public class Account_DAO implements IAccount_DAO {
    
    SQLiteDBExecutor dbExecutor = SQLiteDBExecutor.getInstance();


    /**
     * Get all account in database
     *
     * @return A list of account
     */
    @Override
    public ArrayList<Account_DTO> getAll() {
        ArrayList<Account_DTO> accounts = new ArrayList<>();

        String sqlStatement = "Select * From NGUOIDUNG";
        conn = dbExecutor.connect();
        ResultSet rs = SQLiteDBExecutor.executeQuery(sqlStatement, conn);

        try {
            while (rs.next()) {
                Account_DTO account = new Account_DTO(
                        rs.getString("TaiKhoan"),
                        rs.getString("MatKhau"),
                        rs.getString("LoaiTK")
                );
                accounts.add(account);
            }
            rs.close();
            rs.getStatement().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbExecutor.closeConnection();
        return accounts;
    }

    /**
     * Get user by username
     *
     * @param name username
     * @return Account contain username, pass, account type
     */
    @Override
    public Account_DTO get(String name) {
        String sqlStatement = "Select * From NGUOIDUNG where TaiKhoan = ? ";
        conn = dbExecutor.connect();
        ResultSet rs = SQLiteDBExecutor.executeQuery(sqlStatement, conn, name);

        Account_DTO account = null;

        try {
            while (rs.next()) {
                account = new Account_DTO(
                        rs.getString("TaiKhoan"),
                        rs.getString("MatKhau"),
                        rs.getString("LoaiTK")
                );
            }
            rs.close();
            rs.getStatement().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbExecutor.closeConnection();
        return account;
    }

    /**
     * Add account
     *
     * @param account account object
     * @return A Boolean representing success or fail
     */
    @Override
    public boolean add(Account_DTO account) {
        String sqlStatement = "insert into NGUOIDUNG(TaiKhoan,MatKhau,LoaiTK) values(?,?,?)";
        conn = dbExecutor.connect();

        boolean isSuccess = SQLiteDBExecutor.executeNonQuery(sqlStatement, conn, account.getUsername(), account.getPassword(), account.getAccountType());

        dbExecutor.closeConnection();

        return isSuccess;

    }

    /**
     * Update account
     *
     * @param account account object
     * @return A Boolean representing success or fail
     */
    @Override
    public boolean update(Account_DTO account) {
        String sqlStatement = "Update NGUOIDUNG Set MatKhau = ? Where TaiKhoan = ?";
        conn = dbExecutor.connect();

        boolean isSuccess = SQLiteDBExecutor.executeNonQuery(sqlStatement, conn, account.getPassword(), account.getUsername());

        dbExecutor.closeConnection();

        return isSuccess;
    }

    /**
     * Delete account
     *
     * @param username account username
     * @return A Boolean representing success or fail
     */
    @Override
    public boolean delete(String username) {
        String sqlStatement = "Delete from NGUOIDUNG Where Taikhoan = ?";
        conn = dbExecutor.connect();

        boolean isSuccess = SQLiteDBExecutor.executeNonQuery(sqlStatement, conn, username);

        dbExecutor.closeConnection();

        return isSuccess;
    }

}
