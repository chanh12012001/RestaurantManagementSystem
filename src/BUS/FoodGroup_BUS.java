
package BUS;

import DAO.FoodGroup_DAO;
import DAO.Interface.IFoodGroup_DAO;
import DTO.FoodGroup_DTO;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Phạm Văn Chánh
 */
public class FoodGroup_BUS {
    
    static IFoodGroup_DAO foodGroup_DAO = new FoodGroup_DAO() {};
    
    public void getAllFoodGroups(DefaultTableModel tableModel) {
        tableModel.setRowCount(0);
        ArrayList<FoodGroup_DTO> foodGroupList = foodGroup_DAO.getAll();
        for (FoodGroup_DTO foodGroup : foodGroupList) {
            tableModel.addRow(new Object[]{foodGroup.getId(), foodGroup.getName()});
        }
    }
    
    public void getAllFoodGroupNames(DefaultComboBoxModel cbModel) {
       
        ArrayList<FoodGroup_DTO> foodGroupList = foodGroup_DAO.getAll();
        cbModel.addElement("Tất cả");
        for (FoodGroup_DTO foodGroup : foodGroupList) {
            cbModel.addElement(foodGroup.getName());
        }
    }
    
    public ArrayList<FoodGroup_DTO> getAllFoodGroups() {
        return foodGroup_DAO.getAll();
    }
    
    public FoodGroup_DTO getFoodGroupByName(String foodGroupName) {
        return foodGroup_DAO.getFoodGroupByName(foodGroupName);
    }
    
    public FoodGroup_DTO getFoodGroupById(int foodGroupId) {
        return foodGroup_DAO.getFoodGroupById(foodGroupId);
    }
    
    public void addFoodGroup(FoodGroup_DTO foodGroup) {
        
        if (!"".equals(foodGroup.getName())) {
            FoodGroup_DTO foodGroupCheckDTO = getFoodGroupByName(foodGroup.getName());
            if (foodGroupCheckDTO == null) {
                if (foodGroup_DAO.add(foodGroup)) {
                    JOptionPane.showMessageDialog(null, "Thao tác thành công", "Thêm nhóm món ăn",
                        JOptionPane.PLAIN_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Thao tác thất bại", "Thêm nhóm món ăn",
                        JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Nhóm món ăn đã tồn tại", "Thêm nhóm món ăn",
                    JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập thông tin cần thêm", "Thêm nhóm món ăn",
                    JOptionPane.WARNING_MESSAGE);
        }
        
    }
    
    public void updateFoodGroup(FoodGroup_DTO foodGroup) {
        
        FoodGroup_DTO foodGroupCheckDTO = getFoodGroupByName(foodGroup.getName());

        if (foodGroupCheckDTO == null) {
            if (foodGroup_DAO.update(foodGroup)) {
                JOptionPane.showMessageDialog(null, "Thao tác thành công", "Cập nhật nhóm món ăn",
                        JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Thao tác thất bại", "Cập nhật nhóm món ăn",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nhóm món ăn đã tồn tại", "Cập nhật nhóm món ăn",
                    JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void deleteFoodGroup(int foodGroupId) {

        int result = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa không?", "Xóa nhóm món ăn", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            
            if (foodGroup_DAO.delete(String.valueOf(foodGroupId))) {
                JOptionPane.showMessageDialog(null, "Thao tác thành công", "Xóa nhóm món ăn",
                        JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Thao tác thất bại", "Xóa nhóm món ăn",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else if (result == JOptionPane.NO_OPTION) {
            System.err.println("No");
        }
    }
    
    public void findFoodGroups(DefaultTableModel tableModel, String name) {
        tableModel.setRowCount(0);
        ArrayList<FoodGroup_DTO> foodGroupList = foodGroup_DAO.findFoodGroups(name);
        for (FoodGroup_DTO foodGroup : foodGroupList) {
            tableModel.addRow(new Object[]{foodGroup.getId(), foodGroup.getName()});
        }
    }
}
