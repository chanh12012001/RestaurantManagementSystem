package BUS;

import DTO.Account_DTO;
import DTO.BillDetail_DTO;
import DTO.DinnerTable_DTO;
import DTO.FoodGroup_DTO;
import DTO.Food_DTO;
import DTO.OrderBill_DTO;
import DTO.OrderDetail_DTO;
import DTO.Staff_DTO;
import DTO.Statistic_DTO;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author macbookpro
 */
public class RestaurantManagementFacade {
    private static RestaurantManagementFacade instance;
    private final Account_BUS accountBUS;
    private final BillDetail_BUS billDetailBUS;
    private final Bill_BUS billBUS;
    private final DinnerTable_BUS dinnerTableBUS;
    private final FoodGroup_BUS foodGroupBUS;
    private final Food_BUS foodBUS;
    private final OrderBill_BUS orderBillBUS;
    private final Staff_BUS staffBUS;
    private final Statistic_BUS statisticBUS;

    private RestaurantManagementFacade() {
        accountBUS = new Account_BUS();
        billDetailBUS = new BillDetail_BUS();
        billBUS = new Bill_BUS();
        dinnerTableBUS = new DinnerTable_BUS();
        foodGroupBUS = new FoodGroup_BUS();
        foodBUS = new Food_BUS();
        orderBillBUS = new OrderBill_BUS();
        staffBUS = new Staff_BUS();
        statisticBUS = new Statistic_BUS();
    }

    public static RestaurantManagementFacade getInstance() {
        if (instance == null) {
            instance = new RestaurantManagementFacade();
        }
        return instance;
    }

    //
    // Facade methods for Staff
    //
    public void getAllStaff(DefaultTableModel tableModel) {
        staffBUS.getAllStaff(tableModel);
    }
    
    public Staff_DTO getStaffById(String id) {
        return staffBUS.getStaffById(id);
    }
    
    public ArrayList<Integer> getAllStaffID() {
        return staffBUS.getAllStaffID();
    }
    
    public void findStaffsByName(DefaultTableModel tableModel, String name) {
        staffBUS.findStaffsByName(tableModel, name);
    }
    
    public void addStaff(Staff_DTO staff, String positionType) {
        staffBUS.addStaff(staff, positionType);
    }
    
    public void updateStaff(Staff_DTO staff) {
        staffBUS.updateStaff(staff);
    }
    
    public void deleteStaff(String staffID) {
        staffBUS.deleteStaff(staffID);
    }
    
    
    //
    // Facade methods for Account
    //
    public Account_DTO Login(String username, String password) {
        return accountBUS.Login(username, password);
    }
    
    public void getAllAccount(DefaultTableModel tableModel) {
        accountBUS.getAllAccount(tableModel);
    }
    
    public boolean update(Account_DTO account) {
        return accountBUS.update(account);
    }
    
    
    //
    // Facade methods for GroupFood
    //
    
    public void getAllFoodGroups(DefaultTableModel tableModel) {
        foodGroupBUS.getAllFoodGroups(tableModel);
    }
    
    public void getAllFoodGroupNames(DefaultComboBoxModel cbModel) {
        foodGroupBUS.getAllFoodGroupNames(cbModel);
    }
    
    public ArrayList<FoodGroup_DTO> getAllFoodGroups() {
        return foodGroupBUS.getAllFoodGroups();
    }
    
    public FoodGroup_DTO getFoodGroupByName(String foodGroupName) {
        return foodGroupBUS.getFoodGroupByName(foodGroupName);
    }
    
    public FoodGroup_DTO getFoodGroupById(int foodGroupId) {
        return foodGroupBUS.getFoodGroupById(foodGroupId);
    }
    
    public void addFoodGroup(FoodGroup_DTO foodGroup) {
        foodGroupBUS.addFoodGroup(foodGroup);
    }
    
    public void updateFoodGroup(FoodGroup_DTO foodGroup) {
        foodGroupBUS.updateFoodGroup(foodGroup);
    }
    
    public void deleteFoodGroup(int foodGroupId) {
        foodGroupBUS.deleteFoodGroup(foodGroupId);
    }
    
    public void findFoodGroups(DefaultTableModel tableModel, String name) {
        foodGroupBUS.findFoodGroups(tableModel, name);
    }
    
    
    //
    // Facade methods for Food
    //
    public void getAllFoods(DefaultTableModel tableModel) {
        foodBUS.getAllFoods(tableModel);
    }
    
    public ArrayList<Food_DTO> getAllFoods() {
        return foodBUS.getAllFoods();
    }
    
    public Food_DTO getFoodByName(String foodName) {
        return foodBUS.getFoodByName(foodName);
    }
    
    public Food_DTO getFoodById(int id) {
        return foodBUS.getFoodById(id);
    }
    
    public void addFood(Food_DTO food) {
        foodBUS.addFood(food);
    }
    
    public void updateFood(Food_DTO food) {
        foodBUS.updateFood(food);
    }
    
    public void deleteFood(String foodId) {
        foodBUS.deleteFood(foodId);
    }
    
    public void findFoodsByName(DefaultTableModel tableModel, String name) {
        foodBUS.findFoodsByGroupName(tableModel, name);
    }
    
    public void findFoodsByGroupName(DefaultTableModel tableModel, String groupName) {
        foodBUS.findFoodsByGroupName(tableModel, groupName);
    }
    
    public ArrayList<Food_DTO> findFoodsByGroupNames(String groupName) {
        return foodBUS.findFoodsByGroupNames(groupName);
    }
    
    
    
    //
    // Facade methods for Bill
    //
    public void getAllBills(DefaultTableModel tableModel) {
        billBUS.getAllBills(tableModel);
    }
    
    public void getAllBillsBetweenFromDayAndToDay(DefaultTableModel tableModel, String fromDay, String toDay) {
        billBUS.getAllBillsBetweenFromDayAndToDay(tableModel, fromDay, toDay);
    }
    
    public ArrayList<BillDetail_DTO> loadBillDetailByTableId(DefaultTableModel tableModel, int tableId) {
        return billDetailBUS.loadBillDetailByTableId(tableModel, tableId);
    }
    
    //
    // Facade methods for Order Bill
    //
    public ArrayList<OrderDetail_DTO> getBillDetailById(int id) {
        return orderBillBUS.getBillDetailById(id);
    }
    
    public boolean addBill(int id) {
        return orderBillBUS.add(id);
    }
    
    public int getCurrentBillId(int id) {
        return orderBillBUS.getCurrentBillId(id);
    }
    
    public boolean deleteAllBillDetail(int id) {
        return orderBillBUS.deleteAllBillDetail(id);
    }
    
    public boolean checkoutBill(OrderBill_DTO orderBill) {
        return orderBillBUS.checkoutBill(orderBill);
    }
    
    public boolean insertFoodToBill(OrderDetail_DTO orderDetail) {
        return orderBillBUS.insertFood(orderDetail);
    }
    
    public boolean updateAmountFood(OrderDetail_DTO orderDetail) {
        return orderBillBUS.updateAmountFood(orderDetail);
    }
    
    public boolean deleteOrderDetail(OrderDetail_DTO orderDetail) {
        return orderBillBUS.deleteOrderDetail(orderDetail);
    }
    
    public boolean changeTable(int idFromTable, int idToTable) {
        return orderBillBUS.changeTable(idFromTable, idToTable);
    }
    
    public ArrayList<OrderBill_DTO> getAllBills() {
        return orderBillBUS.getAll();
    }
    
    public boolean deleteBill(String idBill) {
        return orderBillBUS.delete(idBill);
    }
    
    public boolean deleteAllBill() {
        return orderBillBUS.deleteAll();
    }
    
    
    //
    // Facade methods for Dinner Table
    //
    public void getAllTableInfo(DefaultTableModel tableModel) {
        dinnerTableBUS.getAllTableInfo(tableModel);
    }
    
    public DinnerTable_DTO getTableInfoByTableName(String dinnerTableName) {
        return dinnerTableBUS.getTableInfoByTableName(dinnerTableName);
    }
    
    public DinnerTable_DTO getTableInfoByTableId(int dinnerTableId) {
        return dinnerTableBUS.getTableInfoByTableId(dinnerTableId);
    }
    
    public void addTableInfo(String dinnerTableName) {
        dinnerTableBUS.addTableInfo(dinnerTableName);
    }
    
    public void updateTableInfo(int dinnerTableId, String dinnerTableName) {
        dinnerTableBUS.updateTableInfo(dinnerTableId, dinnerTableName);
    }
    
    public void deleteTableInfo(String dinnerTableId) {
        dinnerTableBUS.deleteTableInfo(dinnerTableId);
    }
    
    public void findTableInfos(DefaultTableModel tableModel, String name) {
        dinnerTableBUS.findTableInfos(tableModel, name);
    }
    
    public boolean setStatusOccupied(int id) {
        return dinnerTableBUS.setStatusOccupied(id);
    }
    
    public boolean setStatusEmpty(int id) {
        return dinnerTableBUS.setStatusEmpty(id);
    }
    
    //
    // Facade methods for Statistic
    //
    public ArrayList<Statistic_DTO> calculateIncomeByMonth(String year) {
        return statisticBUS.calculateIncomeByMonth(year);
    }
    
    public ArrayList<Statistic_DTO> calculateIncomeByYear() {
        return statisticBUS.calculateIncomeByYear();
    }
    
    public ArrayList<Statistic_DTO> calculateIncomeByDate(String fromDate, String toDate) {
        return statisticBUS.calculateIncomeByDate(fromDate, toDate);
    }
    
    public Statistic_DTO calculateIncomeByDay(String date) {
        return statisticBUS.calculateIncomeByDay(date);
    }
}
