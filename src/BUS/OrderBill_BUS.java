package BUS;

import DAO.DinnerTable_DAO;
import DAO.Interface.IDinnerTable_DAO;
import DAO.Interface.IOrderBill_DAO;
import DAO.OrderBill_DAO;
import DAO.observer.Observer;
import DTO.OrderBill_DTO;
import DTO.OrderDetail_DTO;
import java.util.ArrayList;

/**
 *
 * @author bao20
 */
public class OrderBill_BUS {

    static IOrderBill_DAO orderBill_DAO = new OrderBill_DAO();
    static IDinnerTable_DAO dinnerTable_DAO = new DinnerTable_DAO();
    Observer observer = new Observer();
    

    /**
     * Get food order of table by table id
     *
     * @param id dinner table id
     * @return A representing order table 's id
     */
    public ArrayList<OrderDetail_DTO> getBillDetailById(int id) {
        return orderBill_DAO.getBillDetailById(id);
    }

    /**
     * Create order bill with table id
     *
     * @param id dinner table id
     * @return A Boolean true if success, otherwise false
     */
    public boolean add(int id) {
        return orderBill_DAO.add(id);
    }

    /**
     * Get current id of unpaid bill in table by table id
     *
     * @param id table id
     * @return A int representing id of bill
     */
    public int getCurrentBillId(int id) {
        return orderBill_DAO.getCurrentBillId(id);
    }

    /**
     * Delete all order bill detail in table
     *
     * @param id table id
     * @return A Boolean true if success, otherwise false
     */
    public boolean deleteAllBillDetail(int id) {
        return orderBill_DAO.deleteAllBillDetail(id);
    }

    /**
     * Check out table bill
     *
     * @param orderBill Order bill of table
     * @return A Boolean true if success, otherwise false
     */
    public boolean checkoutBill(OrderBill_DTO orderBill) {
        boolean success = orderBill_DAO.checkoutBill(orderBill)
                && orderBill_DAO.deleteAllBillDetail(orderBill.getId())
                && dinnerTable_DAO.setStatusEmpty(orderBill.getIdTable());
 
        if (success) {
            Observer.notifyObservers(orderBill);
        }
 
        return success;
    }

    /**
     * Add order food detail to database
     *
     * @param orderDetail Order food detail
     * @return A Boolean true if success, otherwise false
     */
    public boolean insertFood(OrderDetail_DTO orderDetail) {
        return orderBill_DAO.insertFood(orderDetail);
    }

    /**
     * Update amount of food in order detail
     *
     * @param orderDetail Order food detail
     * @return A Boolean true if success, otherwise false
     */
    public boolean updateAmountFood(OrderDetail_DTO orderDetail) {
        return orderBill_DAO.updateAmountFood(orderDetail);
    }

    /**
     * Delete order detail
     *
     * @param orderDetail Order food detail
     * @return A Boolean true if success, otherwise false
     */
    public boolean deleteOrderDetail(OrderDetail_DTO orderDetail) {
        return orderBill_DAO.deleteOrderDetail(orderDetail);
    }

    /**
     * Update bill when customer change table
     *
     * @param idFromTable From table id
     * @param idToTable To table id
     * @return A Boolean true if success, otherwise false
     */
    public boolean changeTable(int idFromTable, int idToTable) {
        return orderBill_DAO.changeTable(idFromTable, idToTable);
    }

    /**
     * Get all bill in database
     *
     * @return A list of bill
     */
    public ArrayList<OrderBill_DTO> getAll() {
        return orderBill_DAO.getAll();
    }

    /**
     * Delete bill by id
     *
     * @param idBill
     * @return A Boolean true if success, otherwise false
     */
    public boolean delete(String idBill) {
        return orderBill_DAO.delete(idBill);
    }

    /**
     * Delete all bill in database
     *
     * @return A Boolean true if success, otherwise false
     */
    public boolean deleteAll() {
        return orderBill_DAO.deleteAll();
    }
}
