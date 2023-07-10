package BUS;

import DAO.Account_DAO;
import DAO.Interface.IAccount_DAO;
import DTO.Account_DTO;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author macbookpro
 */
public class Account_BUS {

    static IAccount_DAO account_DAO = new Account_DAO();

    public void getAllAccount(DefaultTableModel tableModel) {
        tableModel.setRowCount(0);
        ArrayList<Account_DTO> accountList = account_DAO.getAll();
        for (Account_DTO account : accountList) {
            tableModel.addRow(new Object[]{account.getUsername(), account.getPassword(), account.getAccountType()});
        }
    }

    /**
     * Login
     *
     * @param username account username
     * @param password account password
     * @return A account contain username and password, otherwise null
     */
    public Account_DTO Login(String username, String password) {
        Account_DTO account = account_DAO.get(username);
        if (account != null) {
            if (account.getPassword().equals(password)) {
                return new Account_DTO(username, "", account.getAccountType());
            }
        } 
        return null;
    }
    
    /**
     * Update account
     * @param account
     * @return A Boolean representing true if success, otherwise false
     */
    public  boolean update(Account_DTO account) {
        return account_DAO.update(account);
    }
}
