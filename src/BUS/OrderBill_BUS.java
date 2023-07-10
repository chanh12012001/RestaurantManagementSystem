package BUS;

import DAO.Interface.IOrderBill_DAO;
import DAO.OrderBill_DAO;
import DAO.SQLiteDBExecutor;
import DTO.OrderBill_DTO;
import DTO.OrderDetail_DTO;
import DTO.Statistic_DTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Generator.PDFGenerator;

import Interface.OrderBillObserver;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author bao20
 */
public class OrderBill_BUS {

    static IOrderBill_DAO orderBill_DAO = new OrderBill_DAO();
    
    
    private static List<OrderBillObserver> observers = new ArrayList<>();

    public static void addObserver(OrderBillObserver observer) {
        observers.add(observer);
    }

    public static void removeObserver(OrderBillObserver observer) {
        observers.remove(observer);
    }

    private static void notifyObservers(OrderBill_DTO orderBill) {
        for (OrderBillObserver observer : observers) {
            observer.onPaymentSuccess(orderBill);
        }
    }

    /**
     * Get food order of table by table id
     *
     * @param id dinner table id
     * @return A representing order table 's id
     */
    public static ArrayList<OrderDetail_DTO> getBillDetailById(int id) {
        return orderBill_DAO.getBillDetailById(id);
    }

    /**
     * Create order bill with table id
     *
     * @param id dinner table id
     * @return A Boolean true if success, otherwise false
     */
    public static boolean add(int id) {
        return orderBill_DAO.add(id);
    }

    /**
     * Get current id of unpaid bill in table by table id
     *
     * @param id table id
     * @return A int representing id of bill
     */
    public static int getCurrentBillId(int id) {
        return orderBill_DAO.getCurrentBillId(id);
    }

    /**
     * Delete all order bill detail in table
     *
     * @param id table id
     * @return A Boolean true if success, otherwise false
     */
    public static boolean deleteAllBillDetail(int id) {
        return orderBill_DAO.deleteAllBillDetail(id);
    }

    /**
     * Check out table bill
     *
     * @param orderBill Order bill of table
     * @return A Boolean true if success, otherwise false
     */
    public static boolean checkoutBill(OrderBill_DTO orderBill) {
        boolean success = orderBill_DAO.checkoutBill(orderBill)
                && orderBill_DAO.deleteAllBillDetail(orderBill.getId())
                && DinnerTable_BUS.setStatusEmpty(orderBill.getIdTable());

        if (success) {
            notifyObservers(orderBill);
        }

        return success; 
    }

    /**
     * Add order food detail to database
     *
     * @param orderDetail Order food detail
     * @return A Boolean true if success, otherwise false
     */
    public static boolean insertFood(OrderDetail_DTO orderDetail) {
        return orderBill_DAO.insertFood(orderDetail);
    }

    /**
     * Update amount of food in order detail
     *
     * @param orderDetail Order food detail
     * @return A Boolean true if success, otherwise false
     */
    public static boolean updateAmountFood(OrderDetail_DTO orderDetail) {
        return orderBill_DAO.updateAmountFood(orderDetail);
    }

    /**
     * Delete order detail
     *
     * @param orderDetail Order food detail
     * @return A Boolean true if success, otherwise false
     */
    public static boolean deleteOrderDetail(OrderDetail_DTO orderDetail) {
        return orderBill_DAO.deleteOrderDetail(orderDetail);
    }

    /**
     * Update bill when customer change table
     *
     * @param idFromTable From table id
     * @param idToTable To table id
     * @return A Boolean true if success, otherwise false
     */
    public static boolean changeTable(int idFromTable, int idToTable) {
        return orderBill_DAO.changeTable(idFromTable, idToTable);
    }

    /**
     * Get all bill in database
     *
     * @return A list of bill
     */
    public static ArrayList<OrderBill_DTO> getAll() {
        return orderBill_DAO.getAll();
    }

    /**
     * Delete bill by id
     *
     * @param idBill
     * @return A Boolean true if success, otherwise false
     */
    public static boolean delete(String idBill) {
        return orderBill_DAO.delete(idBill);
    }

    /**
     * Delete all bill in database
     *
     * @return A Boolean true if success, otherwise false
     */
    public static boolean deleteAll() {
        return orderBill_DAO.deleteAll();
    }

    /**
     * Statistic income of month in year
     *
     * @param year year to statistic
     * @return A list of total income each month in year
     */
    public static ArrayList<Statistic_DTO> statisticIncomeByMonth(String year) {
        return orderBill_DAO.statisticIncomeByMonth(year);
    }

    /**
     * Statistic income of each year
     *
     * @return A list of total income each year
     */
    public static ArrayList<Statistic_DTO> statisticIncomeByYear() {
        return orderBill_DAO.statisticIncomeByYear();
    }

    /**
     * Statistic income by date
     *
     * @param fromDate from date format dd/MM/YYYY
     * @param toDate to date format dd/MM/YYYY
     * @return A list of income in date
     */
    public static ArrayList<Statistic_DTO> statisticIncomeByDate(String fromDate, String toDate) {
        return orderBill_DAO.statisticIncomeByDate(fromDate, toDate);
    }

    /**
     * Statistic income in today
     *
     * @param date date format dd/MM/YYYY
     * @return A list of income in date
     */
    public static Statistic_DTO statisticIncomeInDay(String date) {
        return orderBill_DAO.statisticIncomeInDay(date);
    }
}
