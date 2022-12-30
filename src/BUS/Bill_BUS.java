
package BUS;

import DAO.Bill_DAO;
import DAO.Interface.IBill_DAO;
import DTO.Bill_DTO;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author macbookpro
 */
public class Bill_BUS {
    
    static IBill_DAO bill_DAO = new Bill_DAO();

    public static void getAllBills(DefaultTableModel tableModel) {
        tableModel.setRowCount(0);
        ArrayList<Bill_DTO> billList = bill_DAO.getAll();
        for (Bill_DTO bill : billList) {
            tableModel.addRow(new Object[]{bill.getID(), bill.getTableName(),bill.getDateOfPayment(), bill.getPrice()});
        }
    }
    
    public static void getAllBillsBetweenFromDayAndToDay(DefaultTableModel tableModel, String fromDay, String toDay) {
        tableModel.setRowCount(0);
        ArrayList<Bill_DTO> billList = bill_DAO.getAllBillsBetweenFromDayAndToDay(fromDay, toDay);
        for (Bill_DTO bill : billList) {
            tableModel.addRow(new Object[]{bill.getID(), bill.getTableName(), bill.getDateOfPayment(), bill.getPrice()});
        }
    }
}
