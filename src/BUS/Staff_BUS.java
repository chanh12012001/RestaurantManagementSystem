
package BUS;

import DAO.Account_DAO;
import DAO.Interface.IAccount_DAO;
import DAO.Interface.IStaff_DAO;
import DAO.Staff_DAO;
import DTO.Account_DTO;
import DTO.Staff_DTO;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author macbookpro
 */
public class Staff_BUS {
    static IStaff_DAO staff_DAO = new Staff_DAO();
    static IAccount_DAO account_DAO = new Account_DAO();
    
    public static void getAllStaff(DefaultTableModel tableModel) {
        tableModel.setRowCount(0);
        ArrayList<Staff_DTO> staffList = staff_DAO.getAll();
        for (Staff_DTO staff : staffList) {
            tableModel.addRow(new Object[]{staff.getStaffID(), staff.getStaffName(), staff.getStaffBirth(), staff.getStaffSex(), staff.getStaffPhone(), staff.getStaffPosition(), staff.getStaffSalary(), staff.getStaffAddress()});
        }
    }
    
    public static Staff_DTO getStaffById(String id) {
        return staff_DAO.getStaffById(id);
    }
    
    public static ArrayList<Integer> getAllStaffID() {
        ArrayList<Staff_DTO> staffList = staff_DAO.getAll();
        ArrayList<Integer> ids = new ArrayList<>();
        for (Staff_DTO staff : staffList) {
            ids.add(Integer.valueOf(staff.getStaffID().substring(2)));
        }
        return ids;
    }
    
    public static void findStaffsByName(DefaultTableModel tableModel, String name) {
        tableModel.setRowCount(0);
        ArrayList<Staff_DTO> staffList = staff_DAO.findStaffsByName(name);
        for (Staff_DTO staff : staffList) {
            tableModel.addRow(new Object[]{staff.getStaffID(), staff.getStaffName(), staff.getStaffBirth(), staff.getStaffSex(), staff.getStaffPhone(), staff.getStaffPosition(), staff.getStaffSalary(), staff.getStaffAddress()});
        }
    }
    
     public static void addStaff(Staff_DTO staff, String positionType) {
        
        if (!"".equals(staff.getStaffName()) && !"".equals(staff.getStaffBirth()) && !"".equals(staff.getStaffSex()) && !"".equals(staff.getStaffPhone()) && !"".equals(staff.getStaffPosition()) && !"".equals(staff.getStaffSalary()) && !"".equals(staff.getStaffAddress())) {
            
            Staff_DTO newStaff = new Staff_DTO(staff.getStaffID(), staff.getStaffName(), staff.getStaffBirth(), staff.getStaffSex(), staff.getStaffPhone(), staff.getStaffPosition(), staff.getStaffSalary(), staff.getStaffAddress());
            Account_DTO newAccount = new Account_DTO(staff.getStaffID(), staff.getStaffPhone(), "Nhân viên".equals(positionType) ? "NV" : "QL");

            if (staff_DAO.add(newStaff) && account_DAO.add(newAccount)) {
                    JOptionPane.showMessageDialog(null, "Thao tác thành công", "Thêm nhân viên",
                        JOptionPane.PLAIN_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Thao tác thất bại", "Thêm nhân viên",
                        JOptionPane.ERROR_MESSAGE);
                }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin", "Thêm nhân viên",
                    JOptionPane.WARNING_MESSAGE);
        }
        
    }
     
     public static void updateStaff(Staff_DTO staff) {
                
        if (!"".equals(staff.getStaffName()) && !"".equals(staff.getStaffBirth()) && !"".equals(staff.getStaffSex()) && !"".equals(staff.getStaffPhone()) && !"".equals(staff.getStaffPosition()) && !"".equals(staff.getStaffSalary()) && !"".equals(staff.getStaffAddress())) {
            
            Staff_DTO newStaff = new Staff_DTO(staff.getStaffID(), staff.getStaffName(), staff.getStaffBirth(), staff.getStaffSex(), staff.getStaffPhone(), staff.getStaffPosition(), staff.getStaffSalary(), staff.getStaffAddress());
            
            if (staff_DAO.update(newStaff)) {
                JOptionPane.showMessageDialog(null, "Thao tác thành công", "Cập nhật nhân viên",
                    JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Thao tác thất bại", "Cập nhật nhân viên",
                    JOptionPane.ERROR_MESSAGE);
            }    
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin", "Cập nhật nhân viên",
                    JOptionPane.WARNING_MESSAGE);
        }     
    }
     
     public static void deleteStaff(String staffID) {
        if (!"".equals(staffID)) {
            Staff_DTO staff = getStaffById(staffID);
            int result = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa không?", "Xóa nhân viên", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
            
                if (staff_DAO.delete(staffID) && account_DAO.delete(staff.getStaffID())) {
                    JOptionPane.showMessageDialog(null, "Thao tác thành công", "Xóa nhân viên",
                        JOptionPane.PLAIN_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Thao tác thất bại", "Xóa nhân viên",
                        JOptionPane.ERROR_MESSAGE);
                }
            } else if (result == JOptionPane.NO_OPTION) {
                System.err.println("No");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên cần xoá", "Xóa nhân viên",
                    JOptionPane.WARNING_MESSAGE);
        }
        
    }
}
