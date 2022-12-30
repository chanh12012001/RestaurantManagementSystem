/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO.Interface;

import DTO.OrderBill_DTO;
import DTO.OrderDetail_DTO;
import DTO.Statistic_DTO;
import java.util.ArrayList;

/**
 *
 * @author bao20
 */
public interface IOrderBill_DAO extends DAO<OrderBill_DTO> {

    /**
     * Get food order of table by table id
     *
     * @param id dinner table id
     * @return A representing order table 's id
     */
    public ArrayList<OrderDetail_DTO> getBillDetailById(int id);

    /**
     * Create order bill with table id
     *
     * @param id dinner table id
     * @return A Boolean true if success, otherwise false
     */
    public boolean add(int id);

    /**
     * Get current id of unpaid bill in table by table id
     *
     * @param id table id
     * @return A int representing id of bill
     */
    public int getCurrentBillId(int id);

    /**
     * Delete all order bill detail in table
     *
     * @param id table id
     * @return A Boolean true if success, otherwise false
     */
    public boolean deleteAllBillDetail(int id);

    /**
     * Check out table bill
     *
     * @param orderBill Order bill of table
     * @return A Boolean true if success, otherwise false
     */
    public boolean checkoutBill(OrderBill_DTO orderBill);

    /**
     * Add order food detail to database
     *
     * @param orderDetail Order food detail
     * @return A Boolean true if success, otherwise false
     */
    public boolean insertFood(OrderDetail_DTO orderDetail);

    /**
     * Update amount of food in order detail
     *
     * @param orderDetail Order food detail
     * @return A Boolean true if success, otherwise false
     */
    public boolean updateAmountFood(OrderDetail_DTO orderDetail);

    /**
     * Delete order detail
     *
     * @param orderDetail Order food detail
     * @return A Boolean true if success, otherwise false
     */
    public boolean deleteOrderDetail(OrderDetail_DTO orderDetail);

    /**
     * Update bill when customer change table
     *
     * @param idFromTable From table id
     * @param idToTable To table id
     * @return A Boolean true if success, otherwise false
     */
    public boolean changeTable(int idFromTable, int idToTable);

    /**
     * Delete all bill in database
     *
     * @return A Boolean true if success, otherwise false
     */
    public boolean deleteAll();

    /**
     * Statistic income of month in year
     *
     * @param year year to statistic
     * @return A list of total income each month in year
     */
    public ArrayList<Statistic_DTO> statisticIncomeByMonth(String year);

    /**
     * Statistic income of each year
     *
     * @return A list of total income each year
     */
    public ArrayList<Statistic_DTO> statisticIncomeByYear();

    /**
     * Statistic income in today
     *
     * @param date date format dd/MM/YYYY
     * @return A list of income in date
     */
    public Statistic_DTO statisticIncomeInDay(String date);

    /**
     * Statistic income by date
     *
     * @param fromDate from date format dd/MM/YYYY
     * @param toDate to date format dd/MM/YYYY
     * @return A list of income in date
     */
    public ArrayList<Statistic_DTO> statisticIncomeByDate(String fromDate, String toDate);
}
