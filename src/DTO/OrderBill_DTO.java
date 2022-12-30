/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author bao20
 */
public class OrderBill_DTO {

    
    private int id;
    private int idTable;
    private String nameTable;
    private String checkoutStatus;
    private String checkoutDate;
    private double total;

    /**
     *
     */
    public OrderBill_DTO() {
    }

    /**
     *
     * @param id
     * @param idTable
     * @param nameTable
     * @param checkoutStatus
     * @param checkoutDate
     * @param total
     */
    public OrderBill_DTO(int id, int idTable, String nameTable, String checkoutStatus, String checkoutDate, double total) {
        this.id = id;
        this.idTable = idTable;
        this.nameTable = nameTable;
        this.checkoutStatus = checkoutStatus;
        this.checkoutDate = checkoutDate;
        this.total = total;
    }

    /**
     *
     * @param id
     * @param nameTable
     * @param checkoutStatus
     * @param checkoutDate
     * @param total
     */
    public OrderBill_DTO(int id, String nameTable, String checkoutStatus, String checkoutDate, double total) {
        this.nameTable = nameTable;
        this.checkoutStatus = checkoutStatus;
        this.checkoutDate = checkoutDate;
        this.total = total;
    }

    /**
     *
     * @param idTable
     * @param checkoutDate
     * @param total
     */
    public OrderBill_DTO(int idTable, String checkoutDate, double total) {
        this.idTable = idTable;
        this.checkoutDate = checkoutDate;
        this.total = total;
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public int getIdTable() {
        return idTable;
    }

    /**
     *
     * @param idTable
     */
    public void setIdTable(int idTable) {
        this.idTable = idTable;
    }

    /**
     *
     * @return
     */
    public String getCheckoutStatus() {
        return checkoutStatus;
    }

    /**
     *
     * @param checkoutStatus
     */
    public void setCheckoutStatus(String checkoutStatus) {
        this.checkoutStatus = checkoutStatus;
    }

    /**
     *
     * @return
     */
    public String getCheckoutDate() {
        return checkoutDate;
    }

    /**
     *
     * @param checkoutDate
     */
    public void setCheckoutDate(String checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    /**
     *
     * @return
     */
    public double getTotal() {
        return total;
    }

    /**
     *
     * @param total
     */
    public void setTotal(double total) {
        this.total = total;
    }

    /**
     *
     * @return
     */
    public String getNameTable() {
        return nameTable;
    }

    /**
     *
     * @param nameTable
     */
    public void setNameTable(String nameTable) {
        this.nameTable = nameTable;
    }
}
