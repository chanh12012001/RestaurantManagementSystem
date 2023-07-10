package BUS;

import DTO.DinnerTable_DTO;
import DAO.DinnerTable_DAO;
import DAO.Interface.IDinnerTable_DAO;
import DAO.Interface.IOrderBill_DAO;
import DAO.OrderBill_DAO;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author bao20
 */
public class DinnerTable_BUS {
    static IDinnerTable_DAO dinnerTable_DAO = new DinnerTable_DAO();
    static IOrderBill_DAO orderBill_DAO = new OrderBill_DAO();
    
    public void getAllTableInfo(DefaultTableModel tableModel) {
        tableModel.setRowCount(0);
        ArrayList<DinnerTable_DTO> dinnerTableList = dinnerTable_DAO.getAll();
        for (DinnerTable_DTO dinnerTable : dinnerTableList) {
            tableModel.addRow(new Object[]{dinnerTable.getId(),
                dinnerTable.getName(), dinnerTable.getStatus()});
        }
    }
    
    public DinnerTable_DTO getTableInfoByTableName(String dinnerTableName) {
        DinnerTable_DTO dinnerTableCheckDTO = dinnerTable_DAO.getDinnerTableByTableName(dinnerTableName);
        return dinnerTableCheckDTO;
    }
    
    public DinnerTable_DTO getTableInfoByTableId(int dinnerTableId) {
        DinnerTable_DTO dinnerTableCheckDTO = dinnerTable_DAO.getDinnerTableByTableId(dinnerTableId);
        return dinnerTableCheckDTO;
    }

    public void addTableInfo(String dinnerTableName) {
        DinnerTable_DTO dinnerTableCheckDTO = dinnerTable_DAO.getDinnerTableByTableName(dinnerTableName);
        if (dinnerTableCheckDTO == null) {
            if (dinnerTable_DAO.add(new DinnerTable_DTO(dinnerTableName))) {
                JOptionPane.showMessageDialog(null, "Thao tác thành công", "Thêm bàn ăn",
                        JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Thao tác thất bại", "Thêm bàn ăn",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Bàn ăn đã tồn tại", "Thêm bàn ăn",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    public void updateTableInfo(int dinnerTableId, String dinnerTableName) {

        DinnerTable_DTO dinnerTableCheckDTO = dinnerTable_DAO.getDinnerTableByTableName(dinnerTableName);

        if (dinnerTableCheckDTO == null) {
            if (dinnerTable_DAO.update(new DinnerTable_DTO(dinnerTableId, dinnerTableName,""))) {
                JOptionPane.showMessageDialog(null, "Thao tác thành công", "Cập nhật bàn ăn",
                        JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Thao tác thất bại", "Cập nhật bàn ăn",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Bàn ăn đã tồn tại", "Cập nhật bàn ăn",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    public void deleteTableInfo(String dinnerTableId) {

        int result = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa không?", "Xóa bàn ăn", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            
            if (dinnerTable_DAO.delete(dinnerTableId)) {
                JOptionPane.showMessageDialog(null, "Thao tác thành công", "Xóa bàn ăn",
                        JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Thao tác thất bại", "Xóa bàn ăn",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else if (result == JOptionPane.NO_OPTION) {
            System.err.println("No");
        }
    }
    
    public void findTableInfos(DefaultTableModel tableModel, String name) {
        tableModel.setRowCount(0);
        ArrayList<DinnerTable_DTO> dinnerTableList = dinnerTable_DAO.findTables(name);
        for (DinnerTable_DTO dinnerTable : dinnerTableList) {
            tableModel.addRow(new Object[]{dinnerTable.getId(),
                dinnerTable.getName(), dinnerTable.getStatus()});
        }
    }
    
    public boolean setStatusOccupied(int id) {
        return dinnerTable_DAO.setStatusOccupied(id) && orderBill_DAO.add(id);
    }
    
    public boolean setStatusEmpty(int id) {
        return dinnerTable_DAO.setStatusEmpty(id);
    }
}
