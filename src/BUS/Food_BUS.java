
package BUS;

import DAO.Food_DAO;
import DAO.Interface.IFood_DAO;
import Utils.ImageUtils;
import DTO.Food_DTO;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author macbookpro
 */
public class Food_BUS {
    static IFood_DAO food_DAO = new Food_DAO();

    public void getAllFoods(DefaultTableModel tableModel) {
        tableModel.setRowCount(0);
        ArrayList<Food_DTO> foodList = food_DAO.getAll();
        for (Food_DTO food : foodList) {
            ImageIcon icon = ImageUtils.convertByteArrayToImageIcon(food.getImage());
            tableModel.addRow(new Object[]{food.getId(), food.getFoodGroupName(),food.getName(), food.getUnit(), food.getPrice(), icon});
        }
    }
    
    public ArrayList<Food_DTO> getAllFoods() {
        return food_DAO.getAll();
    }
    
    public Food_DTO getFoodByName(String foodName) {
        return food_DAO.getFoodByName(foodName);
    }
    
    public Food_DTO getFoodById(int id) {
        return food_DAO.getFoodById(id);
    }
    
    public void addFood(Food_DTO food) {
        
        if (!"".equals(food.getFoodGroupName()) && !"".equals(food.getName()) && !"".equals(food.getUnit()) && food.getPrice() != -1 && food.getImage() != null) {
            Food_DTO foodCheckDTO = getFoodByName(food.getName());  
            
            if (foodCheckDTO == null) {
                
                if (food_DAO.add(new Food_DTO(food.getImage(), food.getFoodGroupName(), food.getName(), food.getUnit(), food.getPrice()))) {
                    JOptionPane.showMessageDialog(null, "Thao tác thành công", "Thêm món ăn",
                        JOptionPane.PLAIN_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Thao tác thất bại", "Thêm món ăn",
                        JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Món ăn đã tồn tại", "Thêm món ăn",
                    JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin", "Thêm món ăn",
                    JOptionPane.WARNING_MESSAGE);
        }
        
    }
    
    public void updateFood(Food_DTO food) {
                
        if (!"".equals(food.getFoodGroupName()) && !"".equals(food.getName()) && !"".equals(food.getUnit()) && food.getPrice() != -1 && food.getImage() != null) {
            Food_DTO foodCheckDTO = getFoodByName(food.getFoodGroupName());

            if (foodCheckDTO == null) {
                if (food_DAO.update( new Food_DTO(food.getId(), food.getImage(), food.getFoodGroupName(), food.getName(), food.getUnit(), food.getPrice()))) {
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
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin", "Cập nhật nhóm món ăn",
                    JOptionPane.WARNING_MESSAGE);
        }     
    }
    
    public void deleteFood(String foodId) {
        int id = Integer.valueOf(foodId);
        if (id != -1) {
            int result = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa không?", "Xóa món ăn", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
            
                if (food_DAO.delete(foodId)) {
                    JOptionPane.showMessageDialog(null, "Thao tác thành công", "Xóa món ăn",
                        JOptionPane.PLAIN_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Thao tác thất bại", "Xóa món ăn",
                        JOptionPane.ERROR_MESSAGE);
                }
            } else if (result == JOptionPane.NO_OPTION) {
                System.err.println("No");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn món ăn cần xoá", "Xóa món ăn",
                    JOptionPane.WARNING_MESSAGE);
        }
        
    }
    
    public void findFoodsByName(DefaultTableModel tableModel, String name) {
        tableModel.setRowCount(0);
        ArrayList<Food_DTO> foodList = food_DAO.findFoodsByName(name);
        for (Food_DTO food : foodList) {
            ImageIcon icon = ImageUtils.convertByteArrayToImageIcon(food.getImage());
            tableModel.addRow(new Object[]{food.getId(), food.getFoodGroupName(),food.getName(), food.getUnit(), food.getPrice(), icon});
        }
    }
    
    public void findFoodsByGroupName(DefaultTableModel tableModel, String groupName) {
        tableModel.setRowCount(0);
        ArrayList<Food_DTO> foodList = food_DAO.findFoodsByGroupName(groupName);
        for (Food_DTO food : foodList) {
            ImageIcon icon = ImageUtils.convertByteArrayToImageIcon(food.getImage());
            tableModel.addRow(new Object[]{food.getId(), food.getFoodGroupName(),food.getName(), food.getUnit(), food.getPrice(), icon});
        }
    }
    
    public ArrayList<Food_DTO> findFoodsByGroupNames(String groupName) {
        return food_DAO.findFoodsByGroupName(groupName);
    }
}
