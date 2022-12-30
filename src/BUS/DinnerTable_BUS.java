package BUS;

import DTO.DinnerTable_DTO;
import DAO.DinnerTable_DAO;
import DAO.Interface.IDinnerTable_DAO;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author bao20
 */
public class DinnerTable_BUS {
    static IDinnerTable_DAO dinnerTable_DAO = new DinnerTable_DAO();
    
    public static void getAllTableInfo(DefaultTableModel tableModel) {
        tableModel.setRowCount(0);
        ArrayList<DinnerTable_DTO> dinnerTableList = dinnerTable_DAO.getAll();
        for (DinnerTable_DTO dinnerTable : dinnerTableList) {
            tableModel.addRow(new Object[]{dinnerTable.getId(),
                dinnerTable.getName(), dinnerTable.getStatus()});
        }
    }
    
    public static DinnerTable_DTO getTableInfoByTableName(String dinnerTableName) {
        DinnerTable_DTO dinnerTableCheckDTO = dinnerTable_DAO.getDinnerTableByTableName(dinnerTableName);
        return dinnerTableCheckDTO;
    }
    
    public static DinnerTable_DTO getTableInfoByTableId(int dinnerTableId) {
        DinnerTable_DTO dinnerTableCheckDTO = dinnerTable_DAO.getDinnerTableByTableId(dinnerTableId);
        return dinnerTableCheckDTO;
    }

    public static void addTableInfo(String dinnerTableName) {
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

    public static void updateTableInfo(int dinnerTableId, String dinnerTableName) {

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

    public static void deleteTableInfo(String dinnerTableId) {

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
    
    public static void findTableInfos(DefaultTableModel tableModel, String name) {
        tableModel.setRowCount(0);
        ArrayList<DinnerTable_DTO> dinnerTableList = dinnerTable_DAO.findTables(name);
        for (DinnerTable_DTO dinnerTable : dinnerTableList) {
            tableModel.addRow(new Object[]{dinnerTable.getId(),
                dinnerTable.getName(), dinnerTable.getStatus()});
        }
    }
    
    public static boolean setStatusOccupied(int id) {
        return dinnerTable_DAO.setStatusOccupied(id) && OrderBill_BUS.add(id);
    }
    
    public static boolean setStatusEmpty(int id) {
        return dinnerTable_DAO.setStatusEmpty(id);
    }
}
